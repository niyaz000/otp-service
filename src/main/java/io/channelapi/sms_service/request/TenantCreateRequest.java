package io.channelapi.sms_service.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import io.channelapi.sms_service.enums.CountryCode;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class TenantCreateRequest {
    
    @NotBlank
    @Size(min = 1, max = 64)
    private String name;

    @JsonProperty("phone_number")
    @NotBlank
    @Size(min = 10, max = 10)
    private String phoneNumber;

    @JsonProperty("country_code")
    @NotNull
    private CountryCode countryCode;

    @NotBlank
    @Size(min = 5, max = 64)
    private String email;
}
