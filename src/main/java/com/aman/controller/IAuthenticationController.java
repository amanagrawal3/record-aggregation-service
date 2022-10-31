package com.aman.controller;

import com.aman.dto.request.AuthenticationRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@RequestMapping("/auth")
public interface IAuthenticationController {

    @PostMapping()
    ResponseEntity<?> authenticationRequest(@RequestBody @Valid AuthenticationRequest authenticationRequest);
}
