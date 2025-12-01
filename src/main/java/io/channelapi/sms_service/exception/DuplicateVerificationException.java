package io.channelapi.sms_service.exception;

public class DuplicateVerificationException extends RuntimeException {
    public DuplicateVerificationException(String message) {
        super(message);
    }
}
