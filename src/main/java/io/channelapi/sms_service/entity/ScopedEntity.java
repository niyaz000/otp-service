package io.channelapi.sms_service.entity;
import java.util.Map;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@MappedSuperclass
@NoArgsConstructor
@SuperBuilder
public class ScopedEntity extends BaseEntity {

    @NotNull
    @Column(name = "account_id", nullable = false, updatable = false)
    private Integer accountId;

    @NotNull
    @Column(name = "tenant_id", nullable = true, updatable = false)
    private Integer tenantId;
    
    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "tags", nullable = false, columnDefinition = "jsonb")
    private Map<String, String> tags;
}
