package com.tanchee.starcircleplus.entry;

import com.tanchee.starcircleplus.tag.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.atomic.AtomicLong;
import java.util.Optional;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.time.ZonedDateTime;
import java.text.ParseException;


import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;

//import jakarta.validation.Valid;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import org.modelmapper.ModelMapper;

@RestController
@RequestMapping(path="/entry")
public class EntryRestController
{
    private static final Logger logger = LogManager.getLogger(EntryRestController.class);

    private final EntryService entryService;
    private final EntryRepository entryRepository;
    private final TagRepository tagRepository;
    private final ModelMapper mapper;

    @Autowired
    public EntryRestController(EntryService entryService, EntryRepository entryRepository, TagRepository tagRepository, ModelMapper mapper)
    {
        this.entryService = entryService;
        this.entryRepository = entryRepository;
        this.tagRepository = tagRepository;
        this.mapper = mapper;
    }

    @GetMapping(path="/all")
    public Iterable<EntryDTO> getAll()
    {
        ArrayList<EntryDTO> dataTransferList = new ArrayList<EntryDTO>();
        for(Entry entry : entryService.fetchEntryList())
        {
            dataTransferList.add(convertToDTO(entry));
        }
        return dataTransferList;
    }

    @PostMapping(path="/save")
    public EntryDTO saveEntry(@RequestBody EntryDTO entryData) throws ParseException
    {
        logger.debug("Recieved entry data: {}", () -> entryData);

        Entry newEntry = convertToEntity(entryData);
        newEntry = entryService.saveEntry(newEntry);
        return convertToDTO(newEntry);
    }

    public EntryDTO convertToDTO(Entry entry)
    {
        EntryDTO entryData = mapper.map(entry, EntryDTO.class);
        return entryData;
    }

    public Entry convertToEntity(EntryDTO entryData) throws ParseException
    {
        Entry entry = mapper.map(entryData, Entry.class);
        /*
        if( entryData.getId() != null )
        {
            Entry oldEntry = findById(entryData.getId()).get();
            entry.setType(oldEntry.getType());
            entry.setChecked(oldEntry.isChecked());
            entry.setDateCreated(oldEntry.getDateCreated());
            entry.setContent(oldEntry.getContent());
            entry.setTags(oldEntry.getTags());
            // NOTE: check for values existing in the data transfer
            // and only load those to the new entry
        } else {
            entry.setDateCreated(ZonedDateTime.now());
        }
        */
        return entry;
    }
}
