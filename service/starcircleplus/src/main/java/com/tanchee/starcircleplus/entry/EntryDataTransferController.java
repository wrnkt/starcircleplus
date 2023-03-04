package com.tanchee.starcircleplus.entry;

import com.tanchee.starcircleplus.tag.*;

import java.util.ArrayList;
import java.util.List;
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

    @Autowired
    private EntryServiceImplementation entryService;

    @Autowired
    private EntryRepository entryRepository;

    @Autowired
    private TagRepository tagRepository;


    @GetMapping(path="/all")
    public Iterable<EntryDataTransfer> getAll()
    {
        ArrayList<EntryDataTransfer> dataTransferList = new ArrayList<EntryDataTransfer>();
        for(Entry entry : entryRepository.findAll())
        {
            dataTransferList.add(
                    entryService.getEntryDataTransferFrom(entry)
                    );
        }
        return dataTransferList;
    }


    // NOTE: Add @Valid before @RequestBody
    @PostMapping(path="/save")
    public EntryDataTransfer addEntry(@RequestBody EntryDataTransfer entry)
    {
        EntryDataTransfer newEntryDataTransfer = new EntryDataTransfer(
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

        // NOTE: Check for entryID here and do tags stuff
        // after ID is set.

        for (String tagName : newEntryDataTransfer.getTags())
        {
            List<Tag> dbTagMatchList = tagRepository.findByNameEquals(tagName);

            if (dbTagMatchList.isEmpty()) {
                Tag newTag = new Tag(tagName);

                newTag.addEntry(newEntry);
                newEntry.addTag(newTag);

                tagRepository.save(newTag);
            } else {
                Tag tagMatch = dbTagMatchList.remove(0);
                tagMatch.addEntry(newEntry);
                //tagMatch.getId()
            }
            entryRepository.save(newEntry);
            
        }

        return newEntryDataTransfer;
    }

}
