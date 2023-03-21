package com.tanchee.starcircleplus.entry;

import com.tanchee.starcircleplus.tag.*;

import java.time.ZonedDateTime;
import java.util.Set;
import java.util.HashSet;
import java.util.ArrayList;

import static org.assertj.core.api.Assertions.*;
//import static org.mockito.AdditionalAnswers.*;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;


public class EntryModelMapperUnitTests {

    private ModelMapper mapper;
    private TypeMap<Entry, EntryDTO> typeMap;

    @BeforeEach
    void init()
    {
        mapper = new ModelMapper();
        typeMap = this.mapper.createTypeMap(Entry.class, EntryDTO.class);
        typeMap.addMappings(
                mapper -> {
                    mapper.map(src -> src.getId(), EntryDTO::setId);
                }
        );
        typeMap.addMappings(
                mapper -> {
                    mapper.map(src -> src.getType(), EntryDTO::setType);
                }
        );
        typeMap.addMappings(
                mapper -> {
                    mapper.map(src -> src.getDateCreated(), EntryDTO::setDateCreated);
                }
        );
        typeMap.addMappings(
                mapper -> {
                    mapper.map(src -> src.isChecked(), EntryDTO::setChecked);
                }
        );
        typeMap.addMappings(
                mapper -> {
                    mapper.map(src -> src.getContent(), EntryDTO::setContent);
                }
        );
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
    }

    @Test
    void properlyConvertsEntryToEntryDTOId()
    {
        Entry entry = new Entry();
        entry.setId(1L);
        EntryDTO entryDTO = this.mapper.map(entry, EntryDTO.class);
        assertThat(entryDTO.getId()).isEqualTo(entry.getId());
    }

    @Test
    void properlyConvertsEntryToEntryDTOType()
    {
        Entry entry = new Entry();
        entry.setType(Type.STAR);
        EntryDTO entryDTO = this.mapper.map(entry, EntryDTO.class);
        assertThat(entryDTO.getType()).isEqualTo(entry.getType());
    }

    @Test
    void properlyConvertsEntryToEntryDTOChecked()
    {
        Entry entry = new Entry();
        entry.setChecked(false);
        EntryDTO entryDTO = this.mapper.map(entry, EntryDTO.class);
        assertThat(entryDTO.isChecked()).isEqualTo(entry.isChecked());
    }

    @Test
    void properlyConvertsEntryToEntryDTODateCreated()
    {
        Entry entry = new Entry();
        entry.setDateCreated(ZonedDateTime.now());
        EntryDTO entryDTO = this.mapper.map(entry, EntryDTO.class);
        assertThat(entryDTO.getDateCreated()).isEqualTo(entry.getDateCreated());
    }
    
    @Test
    void properlyConvertsEntryToEntryDTOContent()
    {
        Entry entry = new Entry();
        entry.setContent("Test content");
        EntryDTO entryDTO = this.mapper.map(entry, EntryDTO.class);
        assertThat(entryDTO.getContent()).isEqualTo(entry.getContent());
    }

    /*
    @Test
    void properlyConvertsEntryToEntryDTOTags()
    {
        Set<Tag> tagSet = new HashSet<Tag>();
        tagSet.add(new Tag("tag1"));
        tagSet.add(new Tag("tag2"));

        Entry entry = new Entry();
        entry.setTags(tagSet);

        EntryDTO entryDTO = this.mapper.map(entry, EntryDTO.class);

        for(Tag tag : entry.getTags())
        {
            assertThat(entryDTO.getTags()).contains(tag.getName());
        }
    }
    */

    // TEST: EntryDTO -> Entry
    @Test
    void properlyConvertsEntryDTOToEntryId()
    {
        EntryDTO entryDTO = new EntryDTO();
        entryDTO.setId(1L);
        Entry entry = this.mapper.map(entryDTO, Entry.class);
        assertThat(entryDTO.getId()).isEqualTo(entry.getId());
    }

    @Test
    void properlyConvertsEntryDTOToEntryType()
    {
        EntryDTO entryDTO = new EntryDTO();
        entryDTO.setType(Type.STAR);
        Entry entry = this.mapper.map(entryDTO, Entry.class);
        assertThat(entryDTO.getType()).isEqualTo(entry.getType());
    }

    @Test
    void properlyConvertsEntryDTOToEntryChecked()
    {
        EntryDTO entryDTO = new EntryDTO();
        entryDTO.setChecked(true);
        Entry entry = this.mapper.map(entryDTO, Entry.class);
        assertThat(entryDTO.isChecked()).isEqualTo(entry.isChecked());
    }

    @Test
    void properlyConvertsEntryDTOToEntryDateCreated()
    {
        EntryDTO entryDTO = new EntryDTO();
        entryDTO.setDateCreated(ZonedDateTime.now());
        Entry entry = this.mapper.map(entryDTO, Entry.class);
        assertThat(entryDTO.getDateCreated()).isEqualTo(entry.getDateCreated());
    }

    @Test
    void properlyConvertsEntryDTOToEntryContent()
    {
        EntryDTO entryDTO = new EntryDTO();
        entryDTO.setContent("Test content");
        Entry entry = this.mapper.map(entryDTO, Entry.class);
        assertThat(entryDTO.getContent()).isEqualTo(entry.getContent());
    }
    
    /*
    @Test
    void properlyConvertsEntryDTOToEntryTags()
    {
        ArrayList<String> tagList = new ArrayList<String>();
        tagList.add("tag1");
        tagList.add("tag2");

        EntryDTO entryDTO = new EntryDTO();
        entryDTO.setTags(tagList);

        Entry entry = this.mapper.map(entryDTO, Entry.class);


        for(Tag tag : entry.getTags())
        {
            assertThat(entryDTO.getTags()).contains(tag.getName());
        }
    }
    */
}
