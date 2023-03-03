package com.tanchee.starcircleplus.entry;

import java.util.ArrayList;
import java.util.Arrays;
import java.time.ZonedDateTime;

import java.util.concurrent.atomic.AtomicLong;

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

@RestController
@RequestMapping(path="/entry")
public class EntryDataTransferController
{
    // WARN: switch controller to use service which uses repository
    //@Autowired private EntryDataTransferService entryService;

    @Autowired
    private EntryRepository entryRepository;


    @GetMapping(path="/test")
    public @ResponseBody EntryDataTransfer getTestEntryDataTransfer()
    {
        return new EntryDataTransfer(uID, Type.STAR, true, dateCreated, tags, content);
    }

    @GetMapping(path="/all")
    public Iterable<Entry> getAll()
    {
        return entryRepository.findAll();
    }

    // NOTE: Add @Valid before @RequestBody
    @PostMapping(path="/save")
    public Entry addEntry(@RequestBody EntryDataTransfer entry)
    {
        EntryDataTransfer newEntryDataTransfer = new EntryDataTransfer(
                1L,
                entry.getType(),
                entry.getChecked(),
                ZonedDateTime.now(),
                entry.getTags(),
                entry.getContent()
        );

        Entry newEntry = new Entry(
                newEntryDataTransfer.getType(),
                newEntryDataTransfer.getChecked(),
                newEntryDataTransfer.getDateCreated(),
                newEntryDataTransfer.getContent()
        );

        entryRepository.save(
                newEntry
        );

        return newEntry;
    }
}
