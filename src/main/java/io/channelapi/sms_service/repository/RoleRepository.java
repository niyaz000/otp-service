package io.channelapi.sms_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.channelapi.sms_service.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    
}
