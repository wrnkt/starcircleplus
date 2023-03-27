package com.tanchee.starcircleplus.entry;

import com.tanchee.starcircleplus.tag.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.atomic.AtomicLong;
import java.util.Optional;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.stream.*;
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

    @Autowired private EntryService entryService;
    @Autowired private TagRepository tagRepository;
    @Autowired private ModelMapper mapper;


    @GetMapping(path="/")
    public ResponseEntity<EntryDTO> getEntry(@RequestParam Long id)
    {
        Entry entry = entryService.findById(id).orElse(null);
        return ResponseEntity.ok(convertToDTO(entry));
    }

    @GetMapping(path="/all")
    public ResponseEntity<Iterable<EntryDTO>> getAll()
    {
        ArrayList<EntryDTO> dataTransferList = new ArrayList<EntryDTO>();
        for(Entry entry : entryService.getAll())
        {
            dataTransferList.add(convertToDTO(entry));
        }
        return ResponseEntity.ok(dataTransferList);
    }


    @PostMapping(path="/save")
    public ResponseEntity<EntryDTO> addEntry(@RequestBody EntryDTO entryDTO) throws ParseException
    {
        //logger.debug("Recieved entryDTO: {}", entryDTO);

        //Entry entry = convertToEntry(entryDTO);
        Entry entry = entryService.save(entryDTO);
        return ResponseEntity.ok(convertToDTO(entry));

        //logger.debug("Entry from entry data: {}", entry);

    }




    //////////////////////////
    // NOTE: HELPER FUNCTIONS

    public EntryDTO convertToDTO(Entry entry)
    {
        logger.debug("Recieved entry: {}", entry);

        EntryDTO entryDTO = new EntryDTO();
        entryDTO.setId(entry.getId());
        entryDTO.setType(entry.getType());
        entryDTO.setDateCreated(entry.getDateCreated());
        entryDTO.setChecked(entry.isChecked());
        entryDTO.setContent(entry.getContent());
        entryDTO.setTags(
                entry.getTags().stream()
                .map(t -> t.getName())
                .collect(Collectors.toList())
        );
        logger.debug("entryDTO from recieved entry : {}", entryDTO);

        return entryDTO;
    }




    public Entry convertToEntry(EntryDTO entryDTO) throws ParseException
    {
        logger.debug("Recieved entryDTO: {}", entryDTO);

        Entry entry = new Entry();
        entry.setId(entryDTO.getId());
        entry.setType(entryDTO.getType());
        entry.setChecked(entryDTO.isChecked());
        entry.setContent(entryDTO.getContent());
        entry.setTags(
                entryDTO.getTags().stream()
                .map(n -> new Tag(n))
                .collect(Collectors.toSet())
        );

        logger.debug("entry from recieved entryDTO : {}", entry);

        return entry;
    }
}
