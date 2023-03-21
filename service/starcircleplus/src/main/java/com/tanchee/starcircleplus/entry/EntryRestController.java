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

import org.springframework.http.ResponseEntity;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;

@RestController
@RequestMapping(path="/entry")
public class EntryRestController
{
    private static final Logger logger = LogManager.getLogger(EntryRestController.class);

    @Autowired
    private EntryService entryService;

    @Autowired
    private ModelMapper mapper;



    @GetMapping(path="/all")
    public ResponseEntity<Iterable<EntryDTO>> getAll()
    {
        ArrayList<EntryDTO> dataTransferList = new ArrayList<EntryDTO>();
        for(Entry entry : entryService.fetchEntryList())
        {
            dataTransferList.add(convertToDTO(entry));
        }
        return ResponseEntity.ok(dataTransferList);
    }

    // NOTE: Consider changing response to Entry
    @PostMapping(path="/save")
    public ResponseEntity<EntryDTO> addEntry(@RequestBody EntryDTO entryDTO) throws ParseException
    {
        //logger.debug("Recieved entryDTO: {}", entryDTO);

        Entry entry = convertToEntry(entryDTO);
        entry = entryService.save(entry);

        //logger.debug("Entry from entry data: {}", entry);

        return ResponseEntity.ok(convertToDTO(entry));
    }


    //////////////////////////
    // NOTE: HELPER FUNCTIONS

    public EntryDTO convertToDTO(Entry entry)
    {
        logger.debug("Recieved entry: {}", entry);
        
        //mapper.createTypeMap(Entry.class, EntryDTO.class);
        /*
        typeMap.addMappings(
                mapper -> mapper.map(src -> src.getContent(), EntryDTO::setContent));
                */

        //EntryDTO entryDTO = new EntryDTO();

        EntryDTO entryDTO = mapper.map(entry, EntryDTO.class);
        logger.debug("entryDTO from recieved entry : {}", entryDTO);

        return entryDTO;
    }

    public Entry convertToEntry(EntryDTO entryDTO)
    {
        logger.debug("Recieved entryDTO: {}", entryDTO);

        Entry entry = mapper.map(entryDTO, Entry.class);
        logger.debug("entry from recieved entryDTO : {}", entry);
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
