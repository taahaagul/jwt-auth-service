package com.taahaagul.tgjwtsecurity.repository;

import com.taahaagul.tgjwtsecurity.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
