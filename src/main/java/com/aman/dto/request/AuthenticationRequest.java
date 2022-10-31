package com.aman.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;


@Getter
@Setter
public class AuthenticationRequest {

    @NotEmpty(message = "Username must be provided")
    private String username;
    @NotEmpty(message = "Password must be provided")
    private String password;
    private String device;
}
