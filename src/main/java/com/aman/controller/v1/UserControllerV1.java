package com.aman.controller.v1;

import com.aman.controller.IUserController;
import com.aman.domain.entity.User;
import com.aman.dto.request.RegisterUser;
import com.aman.exception.ErrorInfo;
import com.aman.exception.NoUserFoundException;
import com.aman.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserControllerV1 implements IUserController {

    private final UserService userService;

    public ResponseEntity<User> getUserByName(@PathVariable("username") String username) {
        User aUser = userService.getUserByUsername(username)
                .orElseThrow(() -> new NoUserFoundException(username, ErrorInfo.USER_NOTFOUND_ERROR));
        return ResponseEntity.ok(aUser);
    }

    public ResponseEntity<User> registerUser(RegisterUser registerUser) {
        return new ResponseEntity<>(this.userService.registerUser(registerUser), HttpStatus.CREATED);
    }
}
