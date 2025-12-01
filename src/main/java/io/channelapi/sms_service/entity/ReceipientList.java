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

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "recipient_lists")
@NoArgsConstructor
@Data
@EntityListeners(AuditingEntityListener.class)
@AllArgsConstructor
@DynamicUpdate
@SuperBuilder
public class ReceipientList extends ScopedEntity {

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;
    
    @Column(name = "description", nullable = true)
    @NotNull
    private String description;

    @NotNull
    @Column(name = "account_id", nullable = false, updatable = false)
    private Integer accountId;

    @NotNull
    @Column(name = "tenant_id", nullable = false, updatable = false)
    private Integer tenantId;

    @Column(name = "tags", nullable = false, columnDefinition = "jsonb")
    @JdbcTypeCode(SqlTypes.JSON)
    private Map<String, String> tags;

    @Column(name = "contact_ids", nullable = false, columnDefinition = "jsonb")
    @JdbcTypeCode(SqlTypes.JSON)
    private List<UUID> contactIds;
    
    @Column(name = "phone_numbers", nullable = false, columnDefinition = "jsonb")
    @JdbcTypeCode(SqlTypes.JSON)
    private List<String> phoneNumbers;

}
