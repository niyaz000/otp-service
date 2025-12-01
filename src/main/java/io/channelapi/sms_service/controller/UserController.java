package io.channelapi.sms_service.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.channelapi.sms_service.constants.RouteConstants;
import lombok.RequiredArgsConstructor;

@RestController
@Validated
@RequestMapping(RouteConstants.USERS)
@RequiredArgsConstructor
public class UserController {
    
}
