package io.channelapi.sms_service.exception;

public class ConcurrentVerificationRequestException extends RuntimeException {
    
    public ConcurrentVerificationRequestException(String message) {
        super(message);
    }
    
}
