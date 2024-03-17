package com.taahaagul.jwtauthservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableAsync
@EnableTransactionManagement
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
public class JwtAuthServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(JwtAuthServiceApplication.class, args);
	}

}
