package com.tanchee.starcircleplus.tag;

import com.tanchee.starcircleplus.entry.Entry;
import com.tanchee.starcircleplus.entry.EntryRepository;
import com.tanchee.starcircleplus.entry.EntryDTO;

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
    @Autowired
    private EntryRepository entryRepository;

    @Autowired
    private TagRepository tagRepository;


    public List<Tag> findAll()
    {
        return (List<Tag>) tagRepository.findAll();
    }

    public Optional<Tag> findByNameEquals(String name)
    {
        return tagRepository.findByName(name);
    }


}
