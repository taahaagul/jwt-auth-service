package com.taahaagul.tgjwtsecurity.repository;

import com.taahaagul.tgjwtsecurity.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepository extends JpaRepository<Token, Long> {
}
