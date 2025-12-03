package io.channelapi.sms_service.service;

import org.springframework.stereotype.Service;

import io.channelapi.sms_service.enums.ChannelApiEntity;
import io.channelapi.sms_service.exception.EntityNotFoundException;
import io.channelapi.sms_service.mapper.AccountMapper;
import io.channelapi.sms_service.repository.AccountRepository;
import io.channelapi.sms_service.request.accounts.AccountCreateRequest;
import io.channelapi.sms_service.response.accounts.AccountCreateResponse;
import io.channelapi.sms_service.response.accounts.AccountGetResponse;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;

    public AccountCreateResponse create(Integer tenantId, AccountCreateRequest request) {
        var entity = AccountMapper.INSTANCE.toEntity(request);
        entity.setTenantId(tenantId.longValue());
        entity = accountRepository.save(entity);
        return AccountMapper.INSTANCE.toDto(entity);
    }

    public AccountGetResponse getById(@NotNull Integer id) {
        var entity = accountRepository.findById(id.longValue())
                .orElseThrow(() -> new EntityNotFoundException(ChannelApiEntity.ACCOUNT, "id", id.toString()));
        return AccountMapper.INSTANCE.toGetDto(entity);
    }
}
