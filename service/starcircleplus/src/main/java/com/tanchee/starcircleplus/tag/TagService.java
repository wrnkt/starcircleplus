package com.tanchee.starcircleplus.tag;

import com.tanchee.starcircleplus.entry.*;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Optional;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;


@Component
public class TagService
{
    private EntryRepository entryRepository;
    private TagRepository tagRepository;
    private EntryService entryService;

    @Autowired
    public TagService(EntryService entryService, EntryRepository entryRepository, TagRepository tagRepository)
    {
        this.entryService = entryService;
        this.entryRepository = entryRepository;
        this.tagRepository = tagRepository;
    }


    public List<Tag> findAll()
    {
        return (List<Tag>) tagRepository.findAll();
    }

    public Optional<Tag> findByNameEquals(String name)
    {
        return tagRepository.findByName(name);
    }

    public Map<String, Integer> getFreqMapForTags(Iterable<Tag> tags)
    {
        Map<String, Integer> tagFreqMap = new HashMap<>();

        for (Tag tag : tags)
        {
            int count = entryService.findByTagsEquals(tag).size();
            tagFreqMap.put(tag.getName(), Integer.valueOf(count));
        }

        return tagFreqMap; 

    }


}
