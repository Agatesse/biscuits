package com.agathevaisse.biscuits;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class BiscuitsApplication {

	public static void main(String[] args) {
		SpringApplication.run(BiscuitsApplication.class, args);
	}
}
