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

import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "tenants")
@NoArgsConstructor
@Data
@EntityListeners(AuditingEntityListener.class)
@AllArgsConstructor
@DynamicUpdate
@SuperBuilder
public class Totp extends ScopedEntity {

    @Column(name = "user_id", nullable = true, updatable = false)
    @NotNull
    private Integer userId;

    @NotNull
    @Column(name = "secret_key", nullable = false)
    private String secretKey;    
}
