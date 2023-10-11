package com.taahaagul.tgjwtsecurity.service;

import com.taahaagul.tgjwtsecurity.entity.User;
import com.taahaagul.tgjwtsecurity.exception.UserNotFoundException;
import com.taahaagul.tgjwtsecurity.repository.UserRepository;
import com.taahaagul.tgjwtsecurity.request.UserChangePaswRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final AuthenticationService authenticationService;
    private final PasswordEncoder passwordEncoder;

    public void changePassword(UserChangePaswRequest request) {
        User user = authenticationService.getCurrentUser();
        if(passwordEncoder.matches(request.getOldPasw(), user.getPassword())) {
            user.setPassword(passwordEncoder.encode(request.getNewPasw()));
            userRepository.save(user);
        } else
            throw new UserNotFoundException("Password unmatched!");
    }
}
