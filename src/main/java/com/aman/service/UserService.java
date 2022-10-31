package com.aman.service;

import com.aman.domain.entity.User;
import com.aman.dto.request.RegisterUser;

import java.util.Optional;

public interface UserService {

    Optional<User> getUserByUsername(String username);

    User registerUser(RegisterUser registerUser);



}
