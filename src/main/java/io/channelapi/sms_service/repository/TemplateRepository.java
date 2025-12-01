package io.channelapi.sms_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.channelapi.sms_service.entity.Template;

@Repository
public interface TemplateRepository extends JpaRepository<Template, Long>  {

    
}
