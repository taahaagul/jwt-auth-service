package com.taahaagul.jwtauthservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class JwtAuthService {

	public static void main(String[] args) {
		SpringApplication.run(JwtAuthService.class, args);
	}

}
