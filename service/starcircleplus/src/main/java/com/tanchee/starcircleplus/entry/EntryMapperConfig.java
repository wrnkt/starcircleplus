package com.tanchee.starcircleplus.entry;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;

@Configuration
public class EntryMapperConfig {

    @Bean
    public ModelMapper mapper()
    {
        return new ModelMapper();
    }

    @Bean
    public ModelMapper entryToEntryDTOMapper()
    {
        ModelMapper mapper = new ModelMapper();

        return mapper;
    }

    /*
    @Bean
    public TypeMap typeMap()
    {
        return mapper.createTypeMap(Entry.class, EntryDTO.class);;

    }
    */
    
}
        /*
        typeMap.addMappings(
                mapper -> {
                    mapper.map(src -> {
                        ArrayList<String> tagsList = new ArrayList<String>();
                        for( Tag tag : src.getTags() ) {
                            tagsList.add(tag.getName());
                        }
                        return tagsList;
                    },
                    EntryDTO::setTags);
                }
        );
        */
