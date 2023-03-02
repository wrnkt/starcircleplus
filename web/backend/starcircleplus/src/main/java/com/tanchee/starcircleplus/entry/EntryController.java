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

//@RestController
@Controller
public class EntryController
{
    // private ApplicationContext context = new AnnotationConfigApplicationContext(EntryConfig.class);

    // EntryRepository repo = context.getBean(EntryRepository.class);

    @Autowired private EntryService entryService;

    // NOTE: Test values.
    private final AtomicLong entryID = new AtomicLong();
    private final long uID = 1L;
    private static final String content = "Placeholder entry text.";
    private static final ArrayList<String> tags = new ArrayList(Arrays.asList("tag1", "tag2"));
    private static final ZonedDateTime dateCreated = ZonedDateTime.now();

    //EntryEntity
    @GetMapping("/testentry")
    public Entry getTestEntry()
    {
        return new Entry(entryID.incrementAndGet(), uID, Type.STAR, true, dateCreated, tags);
    }

    // NOTE: Add @Valid before @RequestBody
    @PostMapping("/saveentry")
    public Entry saveEntry(@RequestBody Entry entry)
    {
        Entry testEntry = new Entry(
                entryID.incrementAndGet(),
                1L,
                Type.STAR,
                true,
                ZonedDateTime.now(),
                new ArrayList(Arrays.asList("tag1", "tag2"))
                );
        // testEntry = repo.save(testEntry);
        // TEST: FOR TESTING
        return entryService.saveEntry(testEntry);
    }
}
