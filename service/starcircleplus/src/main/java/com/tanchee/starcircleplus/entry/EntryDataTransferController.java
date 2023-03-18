package com.tanchee.starcircleplus.entry;

import com.tanchee.starcircleplus.tag.*;

import java.util.Optional;
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

import org.modelmapper.ModelMapper;

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
        for(Entry entry : entryService.fetchEntryList())
        {
            dataTransferList.add(entryService.convertToDTO(entry));
        }
        return dataTransferList;
    }

    // NOTE: Add @Valid before @RequestBody
    @PostMapping(path="/save")
    public EntryDataTransfer saveEntry(@RequestBody EntryDataTransfer entryData)
    {
            
        Optional<Entry> dbEntryOpt = entryRepository.findById(entryData.getId());
        Entry newEntry = new Entry();

        if (dbEntryOpt.isPresent()) {
            Entry dbEntry = dbEntryOpt.get();
            newEntry.setId(dbEntry.getId()); // NOTE: it shoulde be doing this automatically already in dbEntry.get();
            newEntry.setType(entryData.getType());
            newEntry.setChecked(entryData.getChecked());
            newEntry.setContent(entryData.getContent());
            //newEntry.setTags(); // WARN: will require looping through previous tags, removing ones that are now missing
        } else { // new entry, with unspecified id, must be returned after entry is saved
            //newEntry.setId(entryData.getId()); // WARN: may set null Id
            newEntry.setType(entryData.getType());
            newEntry.setChecked(entryData.getChecked());
            newEntry.setDateCreated(ZonedDateTime.now());
            newEntry.setContent(entryData.getContent());
        }

    
        //for tag in tags 

        newEntry = entryRepository.save(newEntry);

        // NOTE: Check for entryID here and do tags stuff
        // after ID is set.

        for (String tagName : entryData.getTags())
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
            newEntry = entryRepository.save(newEntry);
            
        }

        return entryService.convertToDTO(newEntry);
    }

}
