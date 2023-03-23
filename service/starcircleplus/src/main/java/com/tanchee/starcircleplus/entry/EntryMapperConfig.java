package com.tanchee.starcircleplus.entry;

import com.tanchee.starcircleplus.tag.Tag;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeMap;

import java.util.stream.*;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;

@Configuration
public class EntryMapperConfig {

    @Bean
    public ModelMapper mapper()
    {
        ModelMapper mm = new ModelMapper();
        mm.getConfiguration().setSkipNullEnabled(true);
        mm.addMappings(new PropertyMap<Entry, EntryDTO>() {
            @Override
            protected void configure() {
                map().setId(source.getId());
                map().setType(source.getType());
                map().setDateCreated(source.getDateCreated());
                map().setChecked(source.isChecked());
                map().setContent(source.getContent());
                /*
                map().setTags(
                    source.getTags() == null ?
                    (new ArrayList<String>()) :
                    source.getTags().stream().map(t -> t.getName())
                    .collect(Collectors.toList())
                );
                */
            }
        });
        mm.addMappings(new PropertyMap<EntryDTO, Entry>() {
            @Override
            protected void configure() {
                map().setId(source.getId());
                map().setType(source.getType());
                map().setDateCreated(source.getDateCreated());
                map().setChecked(source.isChecked());
                map().setContent("DSJALKDJ");
                /*
                map().setTags(
                    source.getTags() == null ?
                    (new Hashset<Tag>()) :
                    source.getTags().stream().map(s -> new Tag(s))
                    .collect(Collectors.toSet())
                );
                */
            }
        });
        return mm;
    }

}
