package io.channelapi.sms_service.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.channelapi.sms_service.request.TemplateCreateRequest;
import io.channelapi.sms_service.response.TemplateCreateResponse;
import io.channelapi.sms_service.service.TemplateService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@Validated
@RequestMapping("/v1/templates")
@RequiredArgsConstructor
public class TemplateController {

    private final TemplateService templateService;
    
    @PostMapping("")
    public TemplateCreateResponse create(@Valid @RequestBody TemplateCreateRequest request) {
        return templateService.create(request);
    }
}
