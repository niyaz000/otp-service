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

import java.time.OffsetDateTime;
import java.util.Map;
import java.util.UUID;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import io.channelapi.sms_service.enums.MessageSentStatus;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "api_keys")
@NoArgsConstructor
@Data
@EntityListeners(AuditingEntityListener.class)
@AllArgsConstructor
@DynamicUpdate
@SuperBuilder
public class Message extends ScopedEntity {

    @NotNull
    @Column(name = "content", nullable = false, updatable = false)
    private String content;

    @NotNull
    @Column(name = "template_id", nullable = true, updatable = false)
    private Integer templateId;

    @NotNull
    @Column(name = "status", nullable = false)
    @Enumerated(jakarta.persistence.EnumType.STRING)
    private MessageSentStatus status;

    @NotNull
    @Column(name = "sent_at", nullable = true)
    private OffsetDateTime sentAt;

    @NotNull
    @Column(name = "delivered_at", nullable = true)
    private OffsetDateTime deliveredAt;

    @NotNull
    @Column(name = "read_at", nullable = true)
    private OffsetDateTime readAt;

    @NotNull
    @Column(name = "phone_number", nullable = false, updatable = false)
    private String phoneNumber;

    @NotNull
    @Column(name = "transaction_id", nullable = false, updatable = false)
    private UUID transactionId;
}
