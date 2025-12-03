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

import io.channelapi.sms_service.enums.Currency;
import io.channelapi.sms_service.enums.SmsType;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "prices")
@NoArgsConstructor
@Data
@EntityListeners(AuditingEntityListener.class)
@AllArgsConstructor
@DynamicUpdate
@SuperBuilder
public class Price extends BaseEntity {

    @Column(name = "price_per_unit", nullable = false)
    private Integer pricePerUnit;

    @Column(name = "plan_id", nullable = false)
    private Long planId;

    @NotNull
    @Column(name = "currency", nullable = false)
    @Enumerated(jakarta.persistence.EnumType.STRING)
    private Currency currency;

    @NotNull
    @Column(name = "sms_type", nullable = false)
    @Enumerated(jakarta.persistence.EnumType.STRING)
    private SmsType smsType;

    @NotNull
    @Column(name = "volume_threshold", nullable = false)
    private Integer volumeThreshold;

    @NotNull
    @Column(name = "tenant_id", nullable = true, updatable = false)
    private Long tenantId;

    @NotNull
    @Column(name = "account_id", nullable = true, updatable = false)
    private Long accountId;
}
