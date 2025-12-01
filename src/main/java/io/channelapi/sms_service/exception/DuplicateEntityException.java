package io.channelapi.sms_service.exception;

import io.channelapi.sms_service.enums.ChannelApiEntity;
import lombok.Data;

@Data
public class DuplicateEntityException extends RuntimeException {

    private final ChannelApiEntity entity;

    private final String field;

    private final String value;
}

