package com.tanchee.starcircleplus.tag;

import com.tanchee.starcircleplus.entry.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
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
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.http.ResponseEntity;


@RestController
@RequestMapping(path="/tag")
public class TagController
{

    @Autowired
    private EntryService entryService;

    @Autowired
    private TagService tagService;


    @GetMapping("/")
    public ResponseEntity<?> getAllTagsWithFrequency()
    {
        return ResponseEntity.ok(getTagFreqMapForAllTags());
    }

    // BROKEN
    @GetMapping("/{tagname}")
    public ResponseEntity<?> individualTagView(@PathVariable String tagname)
    {
        ArrayList<EntryDTO> entryDataList = new ArrayList<EntryDTO>();
        List<Entry> entryList = entryService.findByTagsEquals(
                tagService.findByNameEquals(tagname).get()
        );
        
        /*
        for (Entry entry : entryList)
        {
            entryDataList.add(entryService.convertToDTO(entry));
        }
        */

        return ResponseEntity.ok(entryDataList);
    }


    Map<String, Integer> getTagFreqMapForAllTags()
    {
        Map<String, Integer> tagFreqMap = new HashMap<>();

        for (Tag tag : tagService.findAll())
        {
            int count = entryService.findByTagsEquals(tag).size();
            tagFreqMap.put(tag.getName(), Integer.valueOf(count));
        }

        return tagFreqMap; 

    }

}
