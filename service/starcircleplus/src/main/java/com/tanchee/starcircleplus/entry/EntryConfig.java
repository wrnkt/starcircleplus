package com.tanchee.starcircleplus.entry;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;

@Configuration
public class EntryConfig
{
    /*
      @Bean
      public EntryService entryService()
      {
        return new EntryService(entryRepository());
      }

      @Bean
      public EntryRepository entryRepository() {
        return new EntryRepository();
      }
      */
}

/*
[12,16] constructor EntryServiceImplementation in class com.tanchee.starcircleplus.EntryServiceImplementation cannot be applied to gi
ven types;
[ERROR]   required: no arguments
[ERROR]   found:    com.tanchee.starcircleplus.EntryRepository
[ERROR]   reason: actual and formal argument lists differ in length
*/
