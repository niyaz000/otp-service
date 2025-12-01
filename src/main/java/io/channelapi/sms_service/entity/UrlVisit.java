package io.channelapi.sms_service.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Map;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "web_hooks")
@NoArgsConstructor
@Data
@EntityListeners(AuditingEntityListener.class)
@AllArgsConstructor
@DynamicUpdate
@SuperBuilder
public class UrlVisit extends ScopedEntity {
    
    @NotNull
    @Column(name = "short_url_id", nullable = false, updatable = false)
    private Integer shortUrlId;

    @NotNull
    @Column(name = "url", nullable = false, updatable = false)
    private String url;

    @NotNull
    @Column(name = "visited_at", nullable = false)
    private String visitedAt;

    @NotNull
    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "tags", nullable = false, columnDefinition = "jsonb")
    private Map<String, String> tags;
}
