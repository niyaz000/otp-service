package io.channelapi.sms_service.mapper;

import org.mapstruct.Mapper;

import io.channelapi.sms_service.entity.Template;
import io.channelapi.sms_service.request.TemplateCreateRequest;
import io.channelapi.sms_service.response.TemplateCreateResponse;

@Mapper
public interface TemplateMapper {
    
    public static TemplateMapper INSTANCE 
        = org.mapstruct.factory.Mappers.getMapper(TemplateMapper.class);

    Template toEntity(TemplateCreateRequest request);

    TemplateCreateResponse toDto(Template template);
}
