package com.tanchee.starcircleplus.tag;

import com.tanchee.starcircleplus.entry.Entry;
import com.tanchee.starcircleplus.entry.EntryRepository;
import com.tanchee.starcircleplus.entry.EntryDataTransfer;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;


@Component
public class TagDataTransferService
{
    @Autowired
    private EntryRepository entryRepository;

    @Autowired
    private TagRepository tagRepository;


    Map<String, Integer> getTagFreqMapForAllTags()
    {
        /*
        TagDataTransfer tagDataTransfer = new TagDataTransfer();
        tagDataTransfer.setTagsList(new ArrayList<String>());
        */

        Map<String, Integer> tagFreqMap = new HashMap<>();

        for (Tag tag : tagRepository.findAll())
        {
            int count = entryRepository.findByTagsEquals(tag).size();
            tagFreqMap.put(tag.getName(), Integer.valueOf(count));
        }

        return tagFreqMap; 

    }
}
