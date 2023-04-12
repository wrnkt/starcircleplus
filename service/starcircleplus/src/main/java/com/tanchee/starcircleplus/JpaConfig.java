package com.tanchee.starcircleplus;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import io.hypersistence.utils.spring.repository.BaseJpaRepositoryImpl;


@Configuration
@EnableJpaRepositories(
    value = {"com.tanchee.starcircleplus.entry",
             "com.tanchee.starcircleplus.tag",
             "com.tanchee.starcircleplus.user"}, 
    repositoryBaseClass = BaseJpaRepositoryImpl.class
)
public class JpaConfig
{
}
