package io.channelapi.sms_service.service;

import org.springframework.stereotype.Service;

import io.channelapi.sms_service.mapper.TemplateMapper;
import io.channelapi.sms_service.repository.TemplateRepository;
import io.channelapi.sms_service.request.TemplateCreateRequest;
import io.channelapi.sms_service.response.TemplateCreateResponse;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TemplateService {

    private final TemplateRepository templateRepository;

    public TemplateCreateResponse create(TemplateCreateRequest request) {
        var entity = TemplateMapper.INSTANCE.toEntity(request);
        entity.setTenantId(1L);
        entity = templateRepository.save(entity);
        return TemplateMapper.INSTANCE.toDto(entity);
    }
}
