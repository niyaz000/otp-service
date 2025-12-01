package io.channelapi.sms_service.controller;

import java.util.UUID;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.channelapi.sms_service.enums.SmsChannel;
import io.channelapi.sms_service.request.TenantCreateRequest;
import io.channelapi.sms_service.response.OtpGenerateResponse;
import io.channelapi.sms_service.response.TenantCreateResponse;
import io.channelapi.sms_service.service.TenantService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;

@RestController
@Validated
@RequestMapping("/v1/teanants")
@RequiredArgsConstructor
public class TenantController {
    
    private final TenantService tenantService;

    @PostMapping("")
    @ResponseStatus(org.springframework.http.HttpStatus.CREATED)
    public TenantCreateResponse create(@Valid @RequestBody TenantCreateRequest request) {
        return tenantService.create(request);
    }

    @GetMapping("/{id}")
    public TenantCreateResponse getById(@PathVariable("id") @Min(1) Long id) {
        return tenantService.getById(id);
    }

    @PutMapping("/{id}/otp")
    public OtpGenerateResponse sendVerificationOtp(@PathVariable("id") @Min(1) Long id, 
        @RequestParam(name = "channel", required = true) SmsChannel channel,
        @RequestHeader(name = "X-Idempotency-ID", required = false) UUID idempotencyId) {
        return tenantService.sendVerificationOtp(id, channel, idempotencyId);
    }

    @PutMapping("/{id}/verify-otp")
    public void verifyOtp(@PathVariable("id") @Min(1) Long id, 
        @RequestParam(name = "channel", required = true) SmsChannel channel,
        @RequestParam(name = "otp", required = true) @Size(min = 6, max = 6) @NotBlank String otp) {
        tenantService.verifyOtp(id, channel, otp);
    }
}
