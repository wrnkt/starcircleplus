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
public class EntryController
{
    // private ApplicationContext context = new AnnotationConfigApplicationContext(EntryConfig.class);

    // EntryRepository repo = context.getBean(EntryRepository.class);

    // WARN: switch controller to use service which uses repository
    //@Autowired private EntryService entryService;

    @Autowired
    private EntryRepository entryRepository;

    // NOTE: Test values.
    private final AtomicLong entryID = new AtomicLong();
    private final long uID = 1L;
    private static final String content = "Placeholder entry text.";
    private static final ArrayList<String> tags = new ArrayList(Arrays.asList("tag1", "tag2"));
    private static final ZonedDateTime dateCreated = ZonedDateTime.now();


    @GetMapping(path="/test")
    public @ResponseBody Entry getTestEntry()
    {
        return new Entry(uID, Type.STAR, true, dateCreated, tags, content);
    }

    @GetMapping(path="/all")
    public Iterable<Entry> getAll()
    {
        return entryRepository.findAll();
    }

    // NOTE: Add @Valid before @RequestBody
    @PostMapping(path="/save")
    public Entry addEntry(@RequestBody Entry entry)
    {
        Entry newEntry = new Entry(
                1L,
                entry.getType(),
                entry.getChecked(),
                ZonedDateTime.now(),
                entry.getTags(),
                entry.getContent()
        );

        return entryRepository.save(newEntry);
    }
}
