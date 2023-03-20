package com.tanchee.starcircleplus.entry;

import com.tanchee.starcircleplus.tag.*;

import java.time.ZonedDateTime;
import java.util.Set;
import java.util.HashSet;

import static org.assertj.core.api.Assertions.*;
//import static org.mockito.AdditionalAnswers.*;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.modelmapper.ModelMapper;


public class EntryModelMapperUnitTests {

    private ModelMapper modelMapper;

    @BeforeEach
    void init()
    {
        modelMapper = new ModelMapper();
    }

    @Test
    void properlyConvertsEntryToEntryDTO()
    {
        Entry entry = new Entry();
        entry.setId(1L);
        entry.setType(Type.STAR);
        entry.setChecked(false);
        entry.setDateCreated(ZonedDateTime.now());
        entry.setContent("Test content");

        Set<Tag> tagSet = new HashSet<Tag>();
        tagSet.add(new Tag("tag1"));
        tagSet.add(new Tag("tag2"));
        entry.setTags(tagSet);

        EntryDataTransfer entryDTO = this.modelMapper.map(entry, EntryDataTransfer.class);

        assertThat(entry.getContent()).isEqualTo(entryDTO.getContent());
    }
    
}
