package com.aman.controller.v1;

import com.aman.controller.IAuthenticationController;
import com.aman.dto.request.AuthenticationRequest;
import com.aman.dto.response.AuthenticationResponse;
import com.aman.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class AuthenticationControllerV1 implements IAuthenticationController {

    private final AuthenticationService authenticationService;

    public ResponseEntity<AuthenticationResponse> authenticationRequest(AuthenticationRequest authenticationRequest) {
        return ResponseEntity.ok(this.authenticationService.authenticate(authenticationRequest));
    }

}
