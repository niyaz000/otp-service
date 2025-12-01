package io.channelapi.sms_service.response.accounts;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AccountCreateResponse {

    @NotNull
    private Long id;

    @JsonProperty("tenant_id")
    @NotNull
    private Integer tenantId;

    @NotNull
    private String name;

}
