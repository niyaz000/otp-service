package io.channelapi.sms_service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import io.channelapi.sms_service.entity.Tenant;
import io.channelapi.sms_service.request.TenantCreateRequest;
import io.channelapi.sms_service.response.TenantCreateResponse;

@Mapper
public interface TenantMapper {

    public static TenantMapper INSTANCE = Mappers.getMapper(TenantMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "name", expression = "java(request.getName().trim().toLowerCase())")
    @Mapping(target = "active", constant = "true")
    @Mapping(target = "status", constant = "UNVERIFIED")
    @Mapping(target = "phoneNumber", expression = "java(request.getPhoneNumber().trim())")
    @Mapping(target = "countryCode", source = "countryCode")
    @Mapping(target = "email", expression = "java(request.getEmail().trim())")
    @Mapping(target = "phoneNumberVerified", constant = "false")
    @Mapping(target = "emailVerified", constant = "false")
    Tenant toEntity(TenantCreateRequest request);

    @Mapping(target = "name", source = "name")
    @Mapping(target = "id", source = "id")
    TenantCreateResponse toDto(Tenant tenant);
}
