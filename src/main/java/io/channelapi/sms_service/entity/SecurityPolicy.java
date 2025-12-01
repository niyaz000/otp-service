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

import io.channelapi.sms_service.enums.IpAction;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "security_policies")
@NoArgsConstructor
@Data
@EntityListeners(AuditingEntityListener.class)
@AllArgsConstructor
@DynamicUpdate
@SuperBuilder
public class SecurityPolicy extends ScopedEntity {

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Column(name = "description", nullable = false)
    private String description;

    @NotNull
    @Column(name = "ipv4_address", nullable = false)
    private String ipv4Address;

    @NotNull
    @Column(name = "ipv6_address", nullable = false)
    private String ipv6Address;

    @NotNull
    @Column(name = "action", nullable = false)
    @Enumerated(jakarta.persistence.EnumType.STRING)
    private IpAction action;   
}
