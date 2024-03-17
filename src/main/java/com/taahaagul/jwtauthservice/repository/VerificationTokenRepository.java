package com.taahaagul.jwtauthservice.repository;

import com.taahaagul.jwtauthservice.entity.User;
import com.taahaagul.jwtauthservice.entity.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {
    Optional<VerificationToken> findByToken(String token);

    List<VerificationToken> findByUser(User user);
}
