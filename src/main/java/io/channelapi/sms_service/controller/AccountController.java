package io.channelapi.sms_service.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.channelapi.sms_service.constants.RouteConstants;
import io.channelapi.sms_service.request.accounts.AccountCreateRequest;
import io.channelapi.sms_service.response.accounts.AccountCreateResponse;
import io.channelapi.sms_service.response.accounts.AccountGetResponse;
import io.channelapi.sms_service.service.AccountService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;

@RestController
@Validated
@RequestMapping(RouteConstants.ACCOUNTS)
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;
    
    @PostMapping("")
    public AccountCreateResponse create(@Valid @RequestBody AccountCreateRequest request,
        @RequestHeader(name = "X-Tenant-ID", required = true) Integer tenantId) {
        return accountService.create(tenantId, request);
    }

    @GetMapping("/{id}")
    public AccountGetResponse getById(@PathVariable("id") @Min(1) Integer id) {
        return accountService.getById(id);
    }
}
