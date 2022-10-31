package com.aman.service;

import com.aman.dto.request.AuthenticationRequest;
import com.aman.dto.response.AuthenticationResponse;

public interface AuthenticationService {
    AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest);
}
