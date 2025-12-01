package io.channelapi.sms_service.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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

import io.channelapi.sms_service.enums.AccountTier;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "accounts")
@NoArgsConstructor
@Data
@EntityListeners(AuditingEntityListener.class)
@AllArgsConstructor
@DynamicUpdate
@SuperBuilder
public class AccountType extends BaseEntity {

    @NotNull
    @Column(name = "type_name", nullable = false, updatable = false)
    private String typeName;

    @NotNull
    @Column(name = "description", nullable = false)
    private String description;
    
    @Column(name = "grace_period_days", nullable = false)
    private int gracePeriodDays;

    @NotNull
    @Column(name = "tier", nullable = false)
    @Enumerated(EnumType.STRING)
    private AccountTier tier;

    @NotNull
    @Column(name = "max_credits", nullable = false)
    private int maxCredits;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "tags", nullable = false, columnDefinition = "jsonb")
    private Map<String, String> tags;
}
