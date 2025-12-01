package io.channelapi.sms_service.request;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import io.channelapi.sms_service.enums.TemplateStatus;
import io.channelapi.sms_service.enums.TemplateType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@JsonNaming(com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy.class)
public class TemplateCreateRequest {

    @Size(min = 1, max = 64)
    private String name;

    @Size(min = 5, max = 256)
    private String content;

    @NotNull
    private TemplateType type;

    private List<TemplateVariable> placeholders;
    
    @NotNull
    private TemplateStatus status;

    @Data
    public static class TemplateVariable {

        @Size(min = 1, max = 64)
        private String name;

        private boolean required;

        @JsonProperty("default_value")
        @Size(max = 128)
        private String defaultValue;
    }
}
