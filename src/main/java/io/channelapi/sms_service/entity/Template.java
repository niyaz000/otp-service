package io.channelapi.sms_service.entity;

import java.util.List;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import io.channelapi.sms_service.enums.TemplateStatus;
import io.channelapi.sms_service.enums.TemplateType;
import io.channelapi.sms_service.request.TemplateCreateRequest.TemplateVariable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "templates")
@NoArgsConstructor
@Data
@EntityListeners(AuditingEntityListener.class)
@AllArgsConstructor
@DynamicUpdate
@SuperBuilder
public class Template extends ScopedEntity {

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Column(name = "content", nullable = false)
    private String content;

    @NotNull
    @Column(name = "type", nullable = false)
    @Enumerated(jakarta.persistence.EnumType.STRING)
    private TemplateType type;

    @NotNull
    @Column(name = "placeholders", nullable = false, columnDefinition = "jsonb")
    @JdbcTypeCode(SqlTypes.JSON)
    private List<TemplateVariable> placeholders;

    @NotNull
    @Column(name = "status", nullable = false)
    @Enumerated(jakarta.persistence.EnumType.STRING)
    private TemplateStatus status;
}
