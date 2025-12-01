package io.channelapi.sms_service.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import io.channelapi.sms_service.enums.CountryCode;
import io.channelapi.sms_service.enums.TenantStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class TenantCreateResponse {

    @NotNull
    private Long id;
    
    @NotNull
    private String name;

    @NotNull
    @JsonProperty("phone_number")
    private String phoneNumber;

    @NotNull
    @JsonProperty("country_code")
    private CountryCode countryCode;

    @NotNull
    private String email;

    @NotNull
    private TenantStatus status;

    @JsonProperty("phone_number_verified")
    private boolean phoneNumberVerified;

    @JsonProperty("email_verified")
    private boolean emailVerified;
}