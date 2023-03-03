package com.tanchee.starcircleplus.tag;

import com.tanchee.starcircleplus.entry.*;

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
@RequestMapping(path="/tag")
public class TagDataTransferController
{
    // WARN: switch controller to use service which uses repository
    //@Autowired private EntryDataTransferService entryService;

    @Autowired
    private EntryRepository entryRepository;

    @Autowired
    private TagRepository tagRepository;


    @GetMapping
    public TagDataTransfer getAll()
    {
        ArrayList<String> tagNameList = new ArrayList<String>();
        Iterable<Tag> tagList = tagRepository.findAll();
        for (Tag tag : tagList) {
            tagNameList.add(tag.getName());
        }
        return new TagDataTransfer(tagNameList);
    }

}
