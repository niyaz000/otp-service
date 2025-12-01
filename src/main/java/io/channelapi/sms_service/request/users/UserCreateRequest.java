package io.channelapi.sms_service.request.users;

import lombok.Data;

@Data
public class UserCreateRequest {

    private String firstName;

    private String lastName;

    private String email;

    private String phoneNumber;

}
