package io.channelapi.sms_service.response;

import java.time.OffsetDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.channelapi.sms_service.enums.SmsChannel;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OtpGenerateResponse {

    @JsonProperty("transaction_id")
    private String transactionId;

    @JsonProperty("exipires_at")
    private OffsetDateTime exipiresAt;

    @JsonProperty("channel")
    private SmsChannel channel;
}
