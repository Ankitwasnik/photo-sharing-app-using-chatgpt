package com.talentica.appusingchatgpt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class AppUsingChatgpt {

	public static void main(String[] args) {
		SpringApplication.run(AppUsingChatgpt.class, args);
	}

}
