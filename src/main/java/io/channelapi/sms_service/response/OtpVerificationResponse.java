package io.channelapi.sms_service.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.channelapi.sms_service.enums.SmsChannel;
import io.channelapi.sms_service.enums.TenantStatus;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OtpVerificationResponse {

    @JsonProperty("status")
    private TenantStatus status;

    @JsonProperty("channel")
    private SmsChannel channel;

}
