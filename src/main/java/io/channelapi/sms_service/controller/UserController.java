package io.channelapi.sms_service.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.channelapi.sms_service.constants.RouteConstants;
import io.channelapi.sms_service.request.users.UserCreateRequest;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@Validated
@RequestMapping(RouteConstants.USERS)
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    public UserCreateResponse create(@Valid @RequestBody UserCreateRequest request,
        @RequestHeader(name = "X-Tenant-ID", required = true) Integer tenantId,
        @RequestHeader(name = "X-Account-ID", required = true) Integer accountId) {
        return userService.create(tenantId, accountId, request);
    }
}
