package io.channelapi.sms_service.exception;

public class DuplicateVerificationRequestException extends RuntimeException {
    
    public DuplicateVerificationRequestException(String message) {
        super(message);
    }

}
