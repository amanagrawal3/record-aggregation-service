package com.aman.controller;

import com.aman.domain.entity.User;
import com.aman.dto.request.RegisterUser;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@RequestMapping("/users")
public interface IUserController {

    @GetMapping("/{username}")
    ResponseEntity<User> getUserByName(@PathVariable("username") String username);

    @PostMapping("/register")
    ResponseEntity<User> registerUser(@RequestBody @Valid RegisterUser registerUser);
}
