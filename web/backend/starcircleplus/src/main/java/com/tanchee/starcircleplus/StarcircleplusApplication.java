package com.tanchee.starcircleplus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(
    basePackages = {
        "com.tanchee.starcircleplus.entry.*",
        "com.tanchee.starcircleplus.security.*"
    }
)
@ComponentScan(
    basePackages = {
        "com.tanchee.starcircleplus.entry.*",
        "com.tanchee.starcircleplus.security.*",
        "com.tanchee.starcircleplus.config.*"
    }
)
@EntityScan(
    basePackages = {
        "com.tanchee.starcircleplus.entry.*",
        "com.tanchee.starcircleplus.security.*"
    }
)
//@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
//@EnableAutoConfiguration
public class StarcircleplusApplication
{
    private static ApplicationContext applicationContext;

	public static void main(String[] args) {
		SpringApplication.run(StarcircleplusApplication.class, args);

        applicationContext = new AnnotationConfigApplicationContext(StarcircleplusApplication.class);

        for (String beanName : applicationContext.getBeanDefinitionNames())
        {
            System.out.println(beanName);
        }


	}

}
