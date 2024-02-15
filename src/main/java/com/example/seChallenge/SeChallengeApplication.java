package com.example.seChallenge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SeChallengeApplication {
	public static void main(String[] args) {
		SpringApplication springApplication = new SpringApplication(SeChallengeApplication.class);
		springApplication.run(args);
	}
}
