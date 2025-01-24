package com.thyme;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class ThymeApplication {

	public static void main(String[] args) {
		SpringApplication.run(ThymeApplication.class, args);
	}

}
