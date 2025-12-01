package io.channelapi.sms_service.service;

import java.util.Objects;
import java.util.UUID;

import org.springframework.stereotype.Service;

import io.channelapi.sms_service.enums.SmsChannel;
import io.channelapi.sms_service.enums.TenantStatus;
import io.channelapi.sms_service.exception.DuplicateVerificationException;
import io.channelapi.sms_service.mapper.TenantMapper;
import io.channelapi.sms_service.repository.TenantRepository;
import io.channelapi.sms_service.request.TenantCreateRequest;
import io.channelapi.sms_service.response.OtpGenerateResponse;
import io.channelapi.sms_service.response.OtpVerificationResponse;
import io.channelapi.sms_service.response.TenantCreateResponse;
import io.channelapi.sms_service.utils.LoggerUtil;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TenantService {

    private final TenantRepository tenantRepository;

    private final TenantVerificationService tenantVerificationService;

    public TenantCreateResponse create(@NotNull TenantCreateRequest request) {
        var entity = TenantMapper.INSTANCE.toEntity(request);
        entity = tenantRepository.save(entity);
        return TenantMapper.INSTANCE.toDto(entity);
    }

    public TenantCreateResponse getById(@NotNull Long id) {
        var entity = tenantRepository.findById(id).orElseThrow();
        return TenantMapper.INSTANCE.toDto(entity);
    }

    public OtpGenerateResponse sendVerificationOtp(@NotNull Long id, 
            @NotNull SmsChannel channel,
            UUID idompotencyKey) {
        idompotencyKey = Objects.requireNonNullElseGet(idompotencyKey, () -> LoggerUtil.currentRequestUuId());
        var entity = tenantRepository.findById(id).orElseThrow();
        if (channel == SmsChannel.SMS) {
            return tenantVerificationService.sendSmsOtp(entity, idompotencyKey);
        } else if (channel == SmsChannel.EMAIL) {
            // Logic to send OTP via Email
        }
        return OtpGenerateResponse.builder()
            .channel(channel)
            .build();
    }

    public OtpVerificationResponse verifyOtp(@NotNull Long id, 
            @NotNull SmsChannel channel,
            @NotNull String otp) {
        var entity = tenantRepository.findById(id).orElseThrow();
        if (channel == SmsChannel.SMS) {
            if (entity.isPhoneNumberVerified()) {
                throw new DuplicateVerificationException("Phone number already verified for tenant: " + entity.getName());
            }
            tenantVerificationService.verifyOtp(entity, channel, otp);
            entity.setPhoneNumberVerified(true);
            entity.setPhoneNumberVerifiedAt(java.time.OffsetDateTime.now());
            if (entity.isEmailVerified() && entity.isPhoneNumberVerified()) {
                entity.setStatus(TenantStatus.VERIFIED);
            }
            tenantRepository.save(entity);
        }
        return OtpVerificationResponse.builder()
            .channel(channel)
            .status(TenantStatus.VERIFIED)
            .build();
    }

}
