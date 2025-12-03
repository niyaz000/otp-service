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

import io.channelapi.sms_service.enums.BillingAdjustmentType;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "adjustment_types")
@NoArgsConstructor
@Data
@EntityListeners(AuditingEntityListener.class)
@AllArgsConstructor
@DynamicUpdate
@SuperBuilder
public class AdjustmentType extends BaseEntity {

    @NotNull
    @Column(name = "name", nullable = false, updatable = false)
    private String name;

    @NotNull
    @Column(name = "type", nullable = false)
    @Enumerated(jakarta.persistence.EnumType.STRING)
    private BillingAdjustmentType type;

    @NotNull
    @Column(name = "description", nullable = false)
    private String description;

    @NotNull
    @Column(name = "is_credit", nullable = false)
    private boolean isCredit;

}
