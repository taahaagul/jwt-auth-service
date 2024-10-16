package com.taahaagul.jwtauthservice.controller;

import com.taahaagul.jwtauthservice.dto.request.UpdatePasswordRequest;
import com.taahaagul.jwtauthservice.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/TG/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PutMapping("/change-password")
    public ResponseEntity<String> changePassword(
            @Valid @RequestBody UpdatePasswordRequest request) {
        userService.changePassword(request);
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body("Change Password Successfully");
    }
}
