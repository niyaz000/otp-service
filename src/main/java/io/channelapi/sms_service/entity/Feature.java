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

import java.util.Map;
import java.util.UUID;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "api_keys")
@NoArgsConstructor
@Data
@EntityListeners(AuditingEntityListener.class)
@AllArgsConstructor
@DynamicUpdate
@SuperBuilder
public class Feature extends BaseEntity {

    @NotNull
    @Column(name = "name", nullable = false, updatable = false)
    private String name;

    @NotNull
    @Column(name = "enabled", nullable = false)
    private boolean enabled;

    @Column(name = "description", nullable = true)
    private String description;
    
    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "tags", nullable = true, columnDefinition = "jsonb")
    private Map<String, String> tags;

    @Column(name = "launched_for_all", nullable = false)
    private boolean launchedForAll;

    @Column(name = "disabled_for_all", nullable = false)
    private boolean disabledForAll;

}
