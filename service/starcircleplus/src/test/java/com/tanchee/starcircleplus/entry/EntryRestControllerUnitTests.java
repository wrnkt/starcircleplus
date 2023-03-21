package com.tanchee.starcircleplus.entry;

import com.tanchee.starcircleplus.tag.*;

import java.time.ZonedDateTime;

import static org.assertj.core.api.Assertions.*;
//import static org.mockito.AdditionalAnswers.*;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.modelmapper.ModelMapper;


public class EntryRestControllerUnitTests {

    private EntryRestController entryController;
    private ModelMapper modelMapper = new ModelMapper();

    @BeforeEach
    void initEntryService()
    {
        entryController = new EntryRestController();
    }

    @Test
    void properConversionFromEntryToDTO()
    {
        Entry entry = new Entry();
        entry.setType(Type.STAR);
        entry.setChecked(true);
        entry.setDateCreated(ZonedDateTime.now());
        entry.setContent("test content");
        //entry.setTags(Arrays.asList({"test"}));
        EntryDTO entryData = entryController.convertToDTO(entry);
        assertThat(entryData.getContent()).isNotNull();
    }
    
}
