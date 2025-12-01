package io.channelapi.sms_service.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

@Service
public class VerificationSmsService {

    public UUID sendSmsOtp(String phoneNumber, String otp) {
        return UUID.randomUUID();
    }

}
