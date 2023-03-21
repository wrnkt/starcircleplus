package com.tanchee.starcircleplus.entry;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;

import org.springframework.beans.factory.annotation.Autowired;

@Configuration
public class EntryMapperConfig {

    @Bean
    public ModelMapper mapper()
    {
        return new ModelMapper();
    }

}
