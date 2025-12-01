package io.channelapi.sms_service.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.channelapi.sms_service.enums.TemplateStatus;
import io.channelapi.sms_service.enums.TemplateType;
import io.channelapi.sms_service.request.TemplateCreateRequest.TemplateVariable;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TemplateCreateResponse {

    @NotNull
    private Long id;

    @JsonProperty("tenant_id")
    @NotNull
    private Long tenantId;

    @NotNull
    private String name;

    @NotNull
    private String content;

    @NotNull
    private TemplateType type;

    @NotNull
    private List<TemplateVariable> placeholders;

    @NotNull
    private TemplateStatus status;
}
