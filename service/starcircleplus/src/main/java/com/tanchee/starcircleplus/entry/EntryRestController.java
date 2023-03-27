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


@RestController
@RequestMapping(path="/entry")
public class EntryRestController
{
    private static final Logger logger = LogManager.getLogger(EntryRestController.class);

    private EntryService entryService;
    private TagRepository tagRepository;

    @Autowired
    public EntryRestController(EntryService entryService, TagRepository tagRepository) {
        this.entryService = entryService;
        this.tagRepository = tagRepository;
    }


    @GetMapping(path="/")
    public ResponseEntity<EntryDTO> getEntry(@RequestParam Long id)
    {
        Entry entry = entryService.findById(id).orElse(null);
        return ResponseEntity.ok(entryService.convertToDTO(entry));
    }

    @GetMapping(path="/all")
    public ResponseEntity<Iterable<EntryDTO>> getAll()
    {
        ArrayList<EntryDTO> dataTransferList = new ArrayList<EntryDTO>();
        for(Entry entry : entryService.getAll())
        {
            dataTransferList.add(entryService.convertToDTO(entry));
        }
        return ResponseEntity.ok(dataTransferList);
    }


    @PostMapping(path="/save")
    public ResponseEntity<EntryDTO> saveSingleEntry(@RequestBody EntryDTO entryDTO) throws ParseException
    {
        Entry entry = entryService.save(entryDTO);
        return ResponseEntity.ok(entryService.convertToDTO(entry));
    }



}
