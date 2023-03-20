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

    private ModelMapper modelMapper;

    @BeforeEach
    void init()
    {
        modelMapper = new ModelMapper();
        /*
        TypeMap<Entry, EntryDTO> propertyMapper = this.modelMapper.createTypeMap(Entry.class, EntryDTO.class);
        propertyMapper.addMappings(
                modelMapper -> {
                    modelMapper.map(src -> {
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
        EntryDTO entryDTO = this.modelMapper.map(entry, EntryDTO.class);
        assertThat(entry.getId()).isEqualTo(entryDTO.getId());
    }

    @Test
    void properlyConvertsEntryToEntryDTOType()
    {
        Entry entry = new Entry();
        entry.setType(Type.STAR);
        EntryDTO entryDTO = this.modelMapper.map(entry, EntryDTO.class);
        assertThat(entry.getType()).isEqualTo(entryDTO.getType());
    }

    @Test
    void properlyConvertsEntryToEntryDTOChecked()
    {
        Entry entry = new Entry();
        entry.setChecked(false);
        EntryDTO entryDTO = this.modelMapper.map(entry, EntryDTO.class);
        assertThat(entry.isChecked()).isEqualTo(entryDTO.isChecked());
    }

    @Test
    void properlyConvertsEntryToEntryDTODateCreated()
    {
        Entry entry = new Entry();
        entry.setDateCreated(ZonedDateTime.now());
        EntryDTO entryDTO = this.modelMapper.map(entry, EntryDTO.class);
        assertThat(entry.getDateCreated()).isEqualTo(entryDTO.getDateCreated());
    }
    
    @Test
    void properlyConvertsEntryToEntryDTOContent()
    {
        Entry entry = new Entry();
        entry.setContent("Test content");
        EntryDTO entryDTO = this.modelMapper.map(entry, EntryDTO.class);
        assertThat(entry.getContent()).isEqualTo(entryDTO.getContent());
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

        EntryDTO entryDTO = this.modelMapper.map(entry, EntryDTO.class);

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
        Entry entry = this.modelMapper.map(entryDTO, Entry.class);
        assertThat(entry.getId()).isEqualTo(entryDTO.getId());
    }

    @Test
    void properlyConvertsEntryDTOToEntryType()
    {
        EntryDTO entryDTO = new EntryDTO();
        entryDTO.setType(Type.STAR);
        Entry entry = this.modelMapper.map(entryDTO, Entry.class);
        assertThat(entry.getType()).isEqualTo(entryDTO.getType());
    }

    @Test
    void properlyConvertsEntryDTOToEntryChecked()
    {
        EntryDTO entryDTO = new EntryDTO();
        entryDTO.setChecked(true);
        Entry entry = this.modelMapper.map(entryDTO, Entry.class);
        assertThat(entry.isChecked()).isEqualTo(entryDTO.isChecked());
    }

    @Test
    void properlyConvertsEntryDTOToEntryDateCreated()
    {
        EntryDTO entryDTO = new EntryDTO();
        entryDTO.setDateCreated(ZonedDateTime.now());
        Entry entry = this.modelMapper.map(entryDTO, Entry.class);
        assertThat(entry.getDateCreated()).isEqualTo(entryDTO.getDateCreated());
    }

    @Test
    void properlyConvertsEntryDTOToEntryContent()
    {
        EntryDTO entryDTO = new EntryDTO();
        entryDTO.setContent("Test content");
        Entry entry = this.modelMapper.map(entryDTO, Entry.class);
        assertThat(entry.getContent()).isEqualTo(entryDTO.getContent());
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

        Entry entry = this.modelMapper.map(entryDTO, Entry.class);


        for(Tag tag : entry.getTags())
        {
            assertThat(entryDTO.getTags()).contains(tag.getName());
        }
    }
    */
}
