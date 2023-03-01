package com.tanchee.starcircleplus;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;

@Configuration
public class EntryConfig
{
      @Bean
      public EntryService entryService()
      {
        return new EntryServiceImplementation(entryRepository());
      }

      @Bean
      public EntryRepository entryRepository() {
        return new EntryRepository();
      }
}
