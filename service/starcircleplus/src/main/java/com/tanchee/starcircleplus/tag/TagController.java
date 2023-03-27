package com.tanchee.starcircleplus.tag;

import com.tanchee.starcircleplus.entry.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Arrays;
import java.util.stream.*;
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
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.http.ResponseEntity;


@RestController
@RequestMapping(path="/tag")
public class TagController
{
    private EntryService entryService;
    private TagService tagService;

    @Autowired
    public TagController(EntryService entryService, TagService tagService)
    {
        this.entryService = entryService;
        this.tagService = tagService;
    }


    @GetMapping("/")
    public ResponseEntity<?> getAllTagsWithFrequency()
    {
        return ResponseEntity.ok(tagService.getFreqMapForTags(tagService.findAll()));
    }

    @GetMapping("/{tagname}")
    public ResponseEntity<?> individualTagView(@PathVariable String tagname)
    {
        List<EntryDTO> entryDataList = entryService.findByTagsEquals(
                tagService.findByNameEquals(tagname).get())
                    .stream()
                    .map(entry -> entryService.convertToDTO(entry))
                    .collect(Collectors.toList());


        return ResponseEntity.ok(entryDataList);
    }


}
