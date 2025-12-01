package io.channelapi.sms_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.channelapi.sms_service.entity.Tenant;

@Repository
public interface TenantRepository extends JpaRepository<Tenant, Long> {
    
}
