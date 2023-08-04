package com.api.proyect;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("com.api.model")
@ComponentScan({"com.api.controller","com.api.service","com.api.repository","com.api.config"})
@EnableJpaRepositories("com.api.repository")
public class ProyectApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProyectApplication.class, args);
	}

}
