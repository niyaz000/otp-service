package io.channelapi.sms_service.service;

import java.time.Duration;
import java.time.OffsetDateTime;
import java.util.UUID;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

import io.channelapi.sms_service.client.RedisClient;
import io.channelapi.sms_service.entity.Tenant;
import io.channelapi.sms_service.enums.SmsChannel;
import io.channelapi.sms_service.exception.ConcurrentVerificationRequestException;
import io.channelapi.sms_service.exception.DuplicateVerificationRequestException;
import io.channelapi.sms_service.exception.OtpExpiredException;
import io.channelapi.sms_service.response.OtpGenerateResponse;
import io.channelapi.sms_service.utils.LoggerUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class TenantVerificationService {

    public static final Duration OTP_EXPIRY_SECONDS = Duration.ofSeconds(60);

    public static final Duration OTP_MUTEX_DURATION_SECONDS = Duration.ofSeconds(30);

    public static final String OTP_SENT_KEY_FROMAT = "tenant:verification:otp:%s:%d";

    public static final String OTP_MUTEX_KEY_FORMAT = "tenant:verification:otp:mutex:%s:%d";

    private final RedisClient redisClient;

    private final VerificationSmsService verificationSmsService;

    public OtpGenerateResponse sendSmsOtp(Tenant tenant, UUID idompotencyKey) {
        Integer id = tenant.getId();
        var phoneNumber = tenant.getPhoneNumber();
        log.info("Sending SMS OTP to phone number: ****{} for tenant {}", phoneNumber.substring(6), tenant.getName());
        var mutexKey = getMutexKey(SmsChannel.SMS, id);
        var mutexValue = new JSONObject().put("idompotency_key", idompotencyKey).toString();
        var mutexAcquired = redisClient.setIfNotExists(mutexKey, mutexValue, OTP_MUTEX_DURATION_SECONDS.toSeconds());
        if (!mutexAcquired) {
            throw new ConcurrentVerificationRequestException(
                String.format("Another SMS OTP request is already in progress for tenant %s", tenant.getName()));
        }
        try {
            var key = getOtpKey(SmsChannel.SMS, id);
            var value = redisClient.get(key);
            if (value != null) {
                var existingKeyValue = new JSONObject(value);
                var otpValue = UUID.fromString(existingKeyValue.getString("idompotency_key"));
                if (idompotencyKey.equals(otpValue)) {
                    log.info("Idompotency key {} matched for tenant {} sent at {}. Skipping duplicate OTP send.", 
                    idompotencyKey, tenant.getName(), existingKeyValue.getString("sent_at"));
                    return OtpGenerateResponse.builder()
                        .transactionId(existingKeyValue.getString("transaction_id"))
                        .exipiresAt(OffsetDateTime.parse(existingKeyValue.getString("sent_at")).plus(OTP_EXPIRY_SECONDS))
                        .channel(SmsChannel.SMS)
                        .build();
                }
                var ttl = redisClient.ttl(key);
                throw new DuplicateVerificationRequestException(
                    String.format("An OTP has already been sent to tenant %s. Please wait %d seconds before requesting a new one.", 
                        tenant.getName(), ttl));
            }
            var otp = String.format("%06d", (int)(Math.random() * 1_000_000));
            var sentAt = OffsetDateTime.now();
            var transactionId = verificationSmsService.sendSmsOtp(phoneNumber, otp);

            var otpSentValue = new JSONObject()
                .put("idompotency_key", idompotencyKey)
                .put("sent_at", sentAt)
                .put("otp", otp)
                .put("transaction_id", transactionId.toString())
                .put("request_id", LoggerUtil.currentOrNewRequestId())
                .toString();
            redisClient.setWithExpiry(key, otpSentValue, OTP_EXPIRY_SECONDS.toSeconds());
            log.info("OTP sent successfully to tenant {} via SMS. Transaction ID: {}", tenant.getName(), transactionId);
            return OtpGenerateResponse.builder()
                .transactionId(transactionId.toString())
                .exipiresAt(sentAt.plus(OTP_EXPIRY_SECONDS))
                .channel(SmsChannel.SMS)
                .build();
        } finally {
            redisClient.delete(mutexKey);
        }
    }

    public void verifyOtp(Tenant tenant, SmsChannel channel, String otp) {
        Integer id = tenant.getId();
        var key = getOtpKey(channel, id);
        var value = redisClient.get(key);
        if (value == null) {
            throw new OtpExpiredException(
                String.format("No OTP request found or OTP has expired for tenant %s", tenant.getName()));
        }
        var existingKeyValue = new JSONObject(value);
        var storedOtp = existingKeyValue.getString("otp");
        if (!storedOtp.equals(otp)) {
            throw new IllegalArgumentException(
                String.format("Invalid OTP provided for tenant %s", tenant.getName()));
        }
        redisClient.delete(key);
        log.info("OTP verified successfully for tenant {} via {}", tenant.getName(), channel.name());
    }

    private static String getOtpKey(SmsChannel channel, Integer id) {
        return String.format(OTP_SENT_KEY_FROMAT, channel.name().toLowerCase(), id);
    }

    private static String getMutexKey(SmsChannel channel, Integer id) {
        return String.format(OTP_MUTEX_KEY_FORMAT, channel.name().toLowerCase(), id);
    }
}
