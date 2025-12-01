package io.channelapi.sms_service.entity;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.UUID;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import io.channelapi.sms_service.utils.LoggerUtil;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Version;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@MappedSuperclass
@NoArgsConstructor
@SuperBuilder
public class BaseEntity implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(name = "created_at", nullable = false, updatable = false)
  @CreatedDate
  private OffsetDateTime createdAt;

  @Column(name = "updated_at", nullable = false)
  @LastModifiedDate
  private OffsetDateTime updatedAt;

  @Column(name = "created_by", nullable = false, updatable = false)
  @CreatedBy
  private Long createdBy;

  @Column(name = "updated_by", nullable = false)
  @LastModifiedBy
  private Long updatedBy;

  @Version
  @Column(name = "version", nullable = false)
  private Integer version;

  @Column(name = "request_id", nullable = false)
  private UUID requestId;

  @Column(name = "active", nullable = false)
  private boolean active;

  @PrePersist
  protected void onCreate() {
    requestId = LoggerUtil.currentRequestUuId();
  }

  @PreUpdate
  protected void onUpdate() {
    requestId = LoggerUtil.currentRequestUuId();
  }
    
}
