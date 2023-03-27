package com.tanchee.starcircleplus.entry;

import com.tanchee.starcircleplus.tag.TagRepository;
import com.tanchee.starcircleplus.tag.Tag;

import java.util.List;
import java.util.Set;
import java.util.ArrayList;
import java.util.Optional;
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
    private final ModelMapper modelMapper;

    @Autowired
    public EntryService(EntryRepository entryRepository, TagRepository tagRepository, ModelMapper modelMapper)
    {
        this.entryRepository = entryRepository;
        this.tagRepository = tagRepository;
        this.modelMapper = modelMapper;
    }

    private Entry updateTagsList(Entry entry, Set<Tag> tagList) {
        for (Tag tag : tagList) {
            Tag originalTag = tagRepository.findByName(tag.getName()).orElse(null);
            if( originalTag == null ) {
                logger.debug("TAG WITH NAME DOESNT ALREADY EXIST");
                tag.addEntry(entry);
                entry.addTag(tag);
                tagRepository.save(tag);
            } else {
                logger.debug("TAG WITH NAME EXISTS");
                entry.getTags().remove(tag);
                entry.getTags().add(originalTag);
                originalTag.addEntry(entry);
                //
            }
        }
        // DOES NOT WORK
        for (Tag tag : entry.getTags()) {
            if  ( tagList.contains(tag) ) { 
            } else { // if tag not in new taglist
                entry.getTags().remove(tag);
                tag.getEntries().remove(entry);
            }

        }
        return entry;
    }


    @Transactional
    public Entry save(Entry entry)
    {
        logger.debug("Attempting to save entry: {}", entry);

        if (entry.getId() == null) {
            logger.debug("ENTRY DOESNT ALREADY EXIST", entry);
            entry = updateTagsList(entry, entry.getTags());

            return entryRepository.save(entry);
        }

        Entry originalEntry = entryRepository.findById(entry.getId()).orElse(null);

        if (originalEntry == null) {
            logger.debug("ENTRY DOESNT ALREADY EXIST", entry);
            entry = updateTagsList(entry, entry.getTags());
            return entryRepository.save(entry);

        } else {
            logger.debug("ENTRY ALREADY EXISTS {}", originalEntry);
            originalEntry = updateTagsList(originalEntry, entry.getTags());
            /*
            for (Tag originalEntryTag : originalEntry.getTags()) {
            }
            */
            return entryRepository.save(originalEntry);
        }
        //return entryRepository.save(entry);
    }

    @Transactional
    public Entry save(EntryDTO entryDTO) {
        Entry entry = new Entry();
        if( entryDTO.getId() == null ) { // entry with id does not exist
            for( String name : entryDTO.getTags() ) {
                Tag originalTag = tagRepository.findByName(name).orElse(null);
                //Tag tag;
                if( originalTag == null ) {
                    Tag tag = new Tag(name);
                    tag.addEntry(entry);
                    entry.addTag(tag);
                    tagRepository.save(tag);
                } else {
                    entry.addTag(originalTag);
                    originalTag.addEntry(entry);
                    tagRepository.save(originalTag);
                }
            }
        } else { // entry already exists
            entry = entryRepository.findById(entry.getId()).orElse(null);
            if (entry == null) {
                entry = new Entry();
            } else {
            }
        }
        entry.setId(entryDTO.getId());
        entry.setType(entryDTO.getType());
        entry.setChecked(entryDTO.isChecked());
        entry.setContent(entryDTO.getContent());

        return entryRepository.save(entry);
    }

    @Transactional
    public Entry update(Entry entry)
    {
        return entryRepository.save(entry);
    }

    public List<Entry> fetchEntryList()
    {
        return (List<Entry>) entryRepository.findAll();
    }

    /*
    @Override
    public Entry updateEntry(Entry entry, Long entryID);
    */

    public void deleteEntryByID(Long entryID)
    {
        entryRepository.deleteById(entryID);
    }

    public Optional<Entry> findById(Long id) {

        return entryRepository.findById(id);
    }

}
