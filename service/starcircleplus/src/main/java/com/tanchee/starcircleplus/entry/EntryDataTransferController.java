package com.tanchee.starcircleplus.entry;

import com.tanchee.starcircleplus.tag.*;

import java.util.Optional;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.time.ZonedDateTime;
import java.text.ParseException;

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

import org.modelmapper.ModelMapper;

@RestController
@RequestMapping(path="/entry")
public class EntryDataTransferController
{
    @Autowired
    private EntryService entryService;

    @Autowired
    private EntryRepository entryRepository;

    @Autowired
    private TagRepository tagRepository;


    @GetMapping(path="/all")
    public Iterable<EntryDataTransfer> getAll()
    {
        ArrayList<EntryDataTransfer> dataTransferList = new ArrayList<EntryDataTransfer>();
        for(Entry entry : entryService.fetchEntryList())
        {
            dataTransferList.add(entryService.convertToDTO(entry));
        }
        return dataTransferList;
    }

    @PostMapping(path="/save")
    public EntryDataTransfer saveEntry(@RequestBody EntryDataTransfer entryData) throws ParseException
    {
        Entry newEntry = entryService.convertToEntity(entryData);
        newEntry = entryService.saveEntry(newEntry);
        return entryService.convertToDTO(newEntry);
    }

}
