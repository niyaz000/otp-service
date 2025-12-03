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

import java.time.LocalDate;

import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "balances")
@NoArgsConstructor
@Data
@EntityListeners(AuditingEntityListener.class)
@AllArgsConstructor
@DynamicUpdate
@SuperBuilder
public class Balance extends ScopedEntity {

    @NotNull
    @Column(name = "due_amount", nullable = false)
    private Integer dueAmount;

    @NotNull
    @Column(name = "available_credits", nullable = false)
    private Integer availableCredits;

    @NotNull
    @Column(name = "next_billing_date", nullable = true)
    private LocalDate nextBillingDate;

    @NotNull
    @Column(name = "last_payment_date", nullable = true)
    private LocalDate lastPaymentDate;

    @NotNull
    @Column(name = "is_overdue", nullable = false)
    private boolean isOverdue;

}
