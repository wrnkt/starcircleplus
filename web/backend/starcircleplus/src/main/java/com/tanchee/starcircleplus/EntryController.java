package com.tanchee.starcircleplus;

import java.util.ArrayList;
import java.util.Arrays;
import java.time.ZonedDateTime;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EntryController
{
    private final AtomicLong entryID = new AtomicLong();
    private final long uID = 1L;
    private static final String content = "Placeholder entry text.";
    private static final ArrayList<String> tags = new ArrayList(Arrays.asList("tag1", "tag2"));
    private static final ZonedDateTime dateCreated = ZonedDateTime.now();

    @GetMapping("/testentry")
    public Entry getTestEntry()
    {
        return new Entry(entryID.incrementAndGet(), uID, Type.STAR, true, dateCreated, tags);
    }

    @PostMapping("/testentry")
    public Entry postEntry()
    {
    }
}
