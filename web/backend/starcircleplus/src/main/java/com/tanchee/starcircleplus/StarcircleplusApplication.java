package com.tanchee.starcircleplus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = { "com.tanchee.starcircleplus.*" })
@ComponentScan(basePackages = "com.tanchee.starcircleplus.*")
@EntityScan("com.tanchee.starcircleplus.*")
public class StarcircleplusApplication
{
	public static void main(String[] args) {
		SpringApplication.run(StarcircleplusApplication.class, args);
	}

}
