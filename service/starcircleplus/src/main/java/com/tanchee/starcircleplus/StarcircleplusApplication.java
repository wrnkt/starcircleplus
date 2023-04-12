package com.tanchee.starcircleplus;

import com.tanchee.starcircleplus.user.*;
import com.tanchee.starcircleplus.entry.*;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.jdbc.support.DatabaseStartupValidator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import java.util.stream.Stream;
import javax.sql.DataSource;
import org.springframework.boot.jdbc.DatabaseDriver;

import com.zaxxer.hikari.HikariDataSource;

@SpringBootApplication
public class StarcircleplusApplication {

    public static void main(String[] args) {
        SpringApplication.run(StarcircleplusApplication.class, args);
    }

    @Autowired
    HikariDataSource dataSource;

    @Bean
    public DatabaseStartupValidator databaseStartupValidator(DataSource dataSource) {
        var dsv = new DatabaseStartupValidator();
        dsv.setDataSource(dataSource);
        dsv.setTimeout(60);
        dsv.setInterval(7);
        dsv.setValidationQuery(DatabaseDriver.MYSQL.getValidationQuery());
        return dsv;
    }

    @Bean
    public static BeanFactoryPostProcessor dependsOnPostProcessor() {
        return bf -> {
            // Let beans that need the database depend on the DatabaseStartupValidator
            // like the JPA EntityManagerFactory
            String[] jpa = bf.getBeanNamesForType(EntityManagerFactory.class);
            Stream.of(jpa)
                .map(bf::getBeanDefinition)
                .forEach(it -> it.setDependsOn("databaseStartupValidator"));
        };
    }
}
