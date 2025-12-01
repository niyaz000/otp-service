package io.channelapi.sms_service.request.accounts;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AccountCreateRequest {
    
    @NotNull
    @JsonProperty("name")
    @NotBlank
    @Size(min = 3, max = 64)
    private String name;

}
