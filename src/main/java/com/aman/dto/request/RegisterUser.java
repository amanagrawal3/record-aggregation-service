package com.aman.dto.request;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;


@Data
public class RegisterUser {

    private static final long serialVersionUID = 7151443507829405471L;

    @NotEmpty(message = "Username must be provided")
    private String username;
    @NotEmpty(message = "Password must be provided")
    private String password;
    private String device;
    @NotEmpty(message = "The email address is required.")
    @Email(message = "The email address is invalid.", flags = {Pattern.Flag.CASE_INSENSITIVE})
    private String email;
}
