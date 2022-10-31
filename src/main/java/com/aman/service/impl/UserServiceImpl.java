package com.aman.service.impl;

import com.aman.domain.entity.User;
import com.aman.dto.request.RegisterUser;
import com.aman.exception.ErrorInfo;
import com.aman.exception.DuplicateRecordException;
import com.aman.exception.NoUserFoundException;
import com.aman.domain.common.UserFactory;
import com.aman.repository.UserRepository;
import com.aman.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public Optional<User> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User registerUser(RegisterUser registerUser) {

        userRepository.findByUsername(registerUser.getUsername()).ifPresent(record1 -> {
            throw new DuplicateRecordException(ErrorInfo.DUPLICATE_USER);
        });


        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(registerUser.getPassword());
        User newUser = User.builder()
                .username(registerUser.getUsername())
                .password(hashedPassword)
                .email(registerUser.getEmail())
                .lastPasswordReset(new Date())
                .authorities("ADMIN")
                .build();
        return userRepository.save(newUser);
    }

    /**
     * This function is to implement spring security core userDetails
     *
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User aUser = this.getUserByUsername(username)
                .orElseThrow(
                        () -> new NoUserFoundException(username, ErrorInfo.USER_NOTFOUND_ERROR));
        return UserFactory.create(aUser);
    }
}
