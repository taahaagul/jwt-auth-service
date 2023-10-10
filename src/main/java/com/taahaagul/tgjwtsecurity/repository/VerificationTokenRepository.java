package com.taahaagul.tgjwtsecurity.repository;

import com.taahaagul.tgjwtsecurity.entity.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {
}
