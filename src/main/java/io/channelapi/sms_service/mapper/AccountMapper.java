package io.channelapi.sms_service.mapper;

import org.mapstruct.Mapper;

import io.channelapi.sms_service.entity.Account;
import io.channelapi.sms_service.request.accounts.AccountCreateRequest;
import io.channelapi.sms_service.response.accounts.AccountCreateResponse;
import io.channelapi.sms_service.response.accounts.AccountGetResponse;

@Mapper
public interface AccountMapper {
    public static final AccountMapper INSTANCE 
        = org.mapstruct.factory.Mappers.getMapper(AccountMapper.class);

    Account toEntity(AccountCreateRequest request);

    AccountCreateResponse toDto(Account account);

    AccountGetResponse toGetDto(Account account);
}
