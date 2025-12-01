package io.channelapi.sms_service.exception;

import lombok.Getter;

@Getter
public class EntityNotFoundException extends RuntimeException {

    private String entityName;

    private String identifierName;

    private String identifierValue;

    public EntityNotFoundException(String entityName, String identifierName, String identifierValue) {
        super(String.format("%s not found with %s: %s", entityName, identifierName, identifierValue));
        this.entityName = entityName;
        this.identifierName = identifierName;
        this.identifierValue = identifierValue;
    }
}