package com.taahaagul.jwtauthservice.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.taahaagul.jwtauthservice.config.JwtService;
import com.taahaagul.jwtauthservice.entity.*;
import com.taahaagul.jwtauthservice.exception.UserNotFoundException;
import com.taahaagul.jwtauthservice.repository.UserRepository;
import com.taahaagul.jwtauthservice.repository.VerificationTokenRepository;
import com.taahaagul.jwtauthservice.request.ForgetPaswRequest;
import com.taahaagul.jwtauthservice.request.LoginRequest;
import com.taahaagul.jwtauthservice.request.RegisterRequest;
import com.taahaagul.jwtauthservice.response.AuthenticationResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final VerificationTokenRepository verificationTokenRepository;
    private final MailService mailService;

    public void register(RegisterRequest request) {

        var user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .userName(request.getUserName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.valueOf("USER"))
                .enabled(false)
                .build();
        userRepository.save(user);

        String token = generateVerificationToken(user);
        mailService.sendMail(new NotificationEmail("Please Activate your Account",
                user.getEmail(), "Thank you for signing up to Spring Reddit, " +
                "please click on the below url to activate your account : " +
                "http://localhost:8080/api/auth/accountVerification/" + token));
    }

    public AuthenticationResponse authenticate(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .build();
    }

    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String refreshToken;
        final String userEmail;
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return;
        }
        refreshToken = authHeader.substring(7);
        userEmail = jwtService.extractUsername(refreshToken);
        if (userEmail != null) {
            var user = this.userRepository.findByEmail(userEmail)
                    .orElseThrow();
            if (jwtService.isTokenValid(refreshToken, user)) {
                var accessToken = jwtService.generateToken(user);
                var authResponse = AuthenticationResponse.builder()
                        .accessToken(accessToken)
                        .refreshToken(refreshToken)
                        .build();
                new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
            }
        }
    }

    public String generateVerificationToken(User user) {
        if(user.isEnabled())
            revokeAllVerificationToken(user);

        String token = UUID.randomUUID().toString();

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.MINUTE, 4);
        Date expirationTime = calendar.getTime();

        VerificationToken verificationToken = VerificationToken.builder()
                .token(token)
                .user(user)
                .expirationTime(expirationTime)
                .build();

        verificationTokenRepository.save(verificationToken);
        return token;
    }

    private void revokeAllVerificationToken(User user) {
        var verificationTokens = verificationTokenRepository.findByUser(user);
        if (verificationTokens.isEmpty())
            return;
        verificationTokenRepository.deleteAll(verificationTokens);
    }

    public void verifyAccount(String token) {
        Optional<VerificationToken> verificationToken = verificationTokenRepository.findByToken(token);
        fetchUserAndEnable(verificationToken.orElseThrow(() -> new UserNotFoundException("Invalid Token")));
    }

    private void fetchUserAndEnable(VerificationToken verificationToken) {
        String username = verificationToken.getUser().getUsername();
        User  user = userRepository.findByEmail(username)
                .orElseThrow(()-> new UserNotFoundException("User not found with name -" + username));
        user.setEnabled(true);
        revokeAllVerificationToken(user);
        userRepository.save(user);
    }

    public void forgetMyPaswToken(String email) {
        User existingUser = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User is not found"));

        String token = generateVerificationToken(existingUser);

        mailService.sendMail(new NotificationEmail("Forget My Password",
                existingUser.getEmail(), "Please copy this token = " + token));
    }

    public void forgetChangePasw(ForgetPaswRequest forgetPaswRequest) {

        VerificationToken verificationToken = verificationTokenRepository
                .findByToken(forgetPaswRequest.getToken())
                .orElseThrow(() -> new UserNotFoundException("Token is not exist"));

        Date now = new Date();

        if(verificationToken.getExpirationTime().after(now)) {
            User user = verificationToken.getUser();
            user.setPassword(passwordEncoder.encode(forgetPaswRequest.getNewPasw()));
            userRepository.save(user);
        } else {
            throw new UserNotFoundException("Token is expired");
        }
    }

    public User getCurrentUser() {
        User user = userRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName())
                .orElseThrow(() -> new UserNotFoundException("Current user is not found"));
        return user;
    }
}
