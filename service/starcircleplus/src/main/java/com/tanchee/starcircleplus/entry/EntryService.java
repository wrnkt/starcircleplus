package com.tanchee.starcircleplus.entry;

import com.tanchee.starcircleplus.tag.TagRepository;
import com.tanchee.starcircleplus.tag.Tag;

import java.util.List;
import java.util.Set;
import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.*;
import java.time.ZonedDateTime;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import org.modelmapper.ModelMapper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Service
public class EntryService
{
    private static final Logger logger = LogManager.getLogger(EntryService.class);

    private final EntryRepository entryRepository;
    private final TagRepository tagRepository;

    @Autowired
    public EntryService(EntryRepository entryRepository, TagRepository tagRepository)
    {
        this.entryRepository = entryRepository;
        this.tagRepository = tagRepository;
    }

    private void updateTagsListAndAssociations(List<String> tagNames, Entry entry) {
        List<String> extraneousTags = entry.getTags().stream()
                                            .map(t -> t.getName())
                                            .collect(Collectors.toList());

        Tag tag;
        for( String name : tagNames ) {
            tag = tagRepository.findByName(name).orElse(null);
            if( tag != null ) {
                extraneousTags.remove(tag.getName());
            } else {
                tag = new Tag(name);
            }
            if( !tag.getEntries().contains(entry) )
                tag.addEntry(entry);
            if( !entry.getTags().contains(tag) )
                entry.addTag(tag);
        }
        
        if( !extraneousTags.isEmpty() ) {
            Tag extraTag;
            for( String name : extraneousTags ) {
                extraTag = tagRepository.findByName(name).orElse(null);
                extraTag.getEntries().remove(entry);
                entry.getTags().remove(extraTag);
            }
        }
    }


    @Transactional
    public Entry save(EntryDTO entryDTO) {
        Long passedId = entryDTO.getId();
        Entry entry;

        if( passedId != null ) { // ENTRY EXISTS
            entry = entryRepository.getReferenceById(passedId);

        } else { // ENTRY DOESNT EXIST ALREADY
            entry = new Entry();
            entry.setDateCreated(ZonedDateTime.now());
        }

        updateTagsListAndAssociations(entryDTO.getTags(), entry);

        entry.setType(entryDTO.getType());
        entry.setChecked(entryDTO.isChecked());
        entry.setContent(entryDTO.getContent());

        return entryRepository.persist(entry);
    }


    public List<Entry> getAll()
    {
        return (List<Entry>) entryRepository.findAll();
    }

    public void deleteEntryByID(Long entryID)
    {
        entryRepository.deleteById(entryID);
    }

    public Optional<Entry> findById(Long id) {

        return entryRepository.findById(id);
    }

    public List<Entry> findByTagsEquals(Tag tag) {
        return entryRepository.findByTagsEquals(tag);
    }

}
