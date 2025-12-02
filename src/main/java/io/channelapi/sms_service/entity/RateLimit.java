package io.channelapi.sms_service.entity;

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

import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import io.swagger.v3.oas.models.PathItem.HttpMethod;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "rate_limits")
@NoArgsConstructor
@Data
@EntityListeners(AuditingEntityListener.class)
@AllArgsConstructor
@DynamicUpdate
@SuperBuilder
public class RateLimit extends ScopedEntity {
    
    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Column(name = "max_requests", nullable = false)
    private Integer maxRequests;

    @NotNull
    @Column(name = "duration_seconds", nullable = false)
    private Integer durationSeconds;

    @NotNull
    @Column(name = "http_method", nullable = false)
    @Enumerated(jakarta.persistence.EnumType.STRING)
    private HttpMethod httpMethod;

    @NotNull
    @Column(name = "path_pattern", nullable = false)
    private String pathPattern;
}
