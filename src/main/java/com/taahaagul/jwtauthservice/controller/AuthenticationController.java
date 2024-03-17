package com.taahaagul.jwtauthservice.controller;

import com.taahaagul.jwtauthservice.dto.request.ForgetPasswordRequest;
import com.taahaagul.jwtauthservice.dto.request.LoginRequest;
import com.taahaagul.jwtauthservice.dto.request.RegisterRequest;
import com.taahaagul.jwtauthservice.dto.response.AuthenticationResponse;
import com.taahaagul.jwtauthservice.service.AuthenticationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;

    @PostMapping("/register")
    public ResponseEntity<String> register(
            @Valid @RequestBody RegisterRequest request
    ) {
        service.register(request);
        return ResponseEntity.status(OK)
                .body("User Registiration Successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @Valid @RequestBody LoginRequest request
    ) {
        return ResponseEntity.ok(service.authenticate(request));
    }

    @PostMapping("/refresh/token")
    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        service.refreshToken(request, response);
    }

    @GetMapping("/accountVerification/{token}")
    public ResponseEntity<String> verifyAccount(@PathVariable String token) {
        service.verifyAccount(token);
        return new ResponseEntity<>("Account Activated Successfully", OK);
    }

    @PostMapping("/forgetMyPassword/{email}")
    public ResponseEntity<String> forgetMyPassword(@PathVariable String email) {
        service.forgetMyPaswToken(email);
        return new ResponseEntity<>("Token is delivered", OK);
    }

    @PutMapping("/forgetMyPassword/newPasw")
    public ResponseEntity<String> forgetChangePasword(
            @Valid @RequestBody ForgetPasswordRequest forgetPasswordRequest) {
        service.forgetChangePasw(forgetPasswordRequest);
        return new ResponseEntity<>("New Password is determined", OK);
    }
}