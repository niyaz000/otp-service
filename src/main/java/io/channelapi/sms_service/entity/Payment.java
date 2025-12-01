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

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import io.channelapi.sms_service.enums.PaymentMode;
import io.channelapi.sms_service.enums.PaymentStatus;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "payments")
@NoArgsConstructor
@Data
@EntityListeners(AuditingEntityListener.class)
@AllArgsConstructor
@DynamicUpdate
@SuperBuilder
public class Payment extends ScopedEntity {

    @NotNull
    @Column(name = "amount", nullable = false)
    private Integer amount;

    @NotNull
    @Column(name = "currency", nullable = false)
    private String currency;

    @NotNull
    @Column(name = "status", nullable = false)
    @Enumerated(jakarta.persistence.EnumType.STRING)
    private PaymentStatus status;
    
    @Column(name = "transaction_id", nullable = true)
    private String transactionId;

    @NotNull
    @Column(name = "mode", nullable = false)
    @Enumerated(jakarta.persistence.EnumType.STRING)
    private PaymentMode mode;
}
