package com.tanchee.starcircleplus.entry;

import com.tanchee.starcircleplus.tag.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.*;
//import static org.mockito.AdditionalAnswers.*;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.*;
//
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.modelmapper.ModelMapper;


@ExtendWith(MockitoExtension.class)
public class EntryRestControllerUnitTests {
    private static final Logger logger = LogManager.getLogger(EntryRestController.class);

    @InjectMocks
    EntryRestController entryController;

    @Mock
    EntryService entryService;

    @Mock
    ModelMapper mapper;


    /*
    @BeforeEach
    void init()
    {
        entryController = new EntryRestController();
    }
    */

    /*
    @Test
    public void testAddEntry() throws ParseException
    {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        //when(entryService.save(any(Entry.class))).thenReturn(true);

        EntryDTO entryDTO = new EntryDTO();
        entryDTO.setType(Type.STAR);
        entryDTO.setChecked(true);
        entryDTO.setDateCreated(ZonedDateTime.now());
        entryDTO.setContent("test content");
        //entryDTO.setTags(Arrays.asList({"test"}));
        
        //logger.debug("Entry data to be saved: {}", () -> entryDTO);
        ResponseEntity<EntryDTO> responseEntity = entryController.addEntry(entryDTO);

        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200); // 200 OK
        //assertThat(responseEntity.getHeaders().getLocation().getPath()).isEqualTo("/1");
        //assertThat(responseEntity.getBody()).isEqualTo("/1");
    }
    */

    @Test
    void properConversionFromEntryToDTO()
    {
        Entry entry = new Entry();
        entry.setType(Type.STAR);
        entry.setChecked(true);
        entry.setDateCreated(ZonedDateTime.now());
        entry.setContent("test content");
        //entry.setTags(Arrays.asList({"test"}));
        EntryDTO entryDTO = entryController.convertToDTO(entry);
        assertThat(entryDTO.getContent()).isNotNull();
    }
    
}
