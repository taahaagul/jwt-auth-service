package com.taahaagul.jwtauthservice.service;

import com.taahaagul.jwtauthservice.entity.User;
import com.taahaagul.jwtauthservice.exception.IllegalOperationException;
import com.taahaagul.jwtauthservice.repository.UserRepository;
import com.taahaagul.jwtauthservice.dto.request.UpdatePasswordRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final AuthenticationService authenticationService;
    private final PasswordEncoder passwordEncoder;

    public void changePassword(UpdatePasswordRequest request) {
        User user = authenticationService.getCurrentUser();
        if(passwordEncoder.matches(request.getOldPasw(), user.getPassword())) {
            user.setPassword(passwordEncoder.encode(request.getNewPasw()));
            userRepository.save(user);
        } else
            throw new IllegalOperationException("Password unmatched!");
    }
}
