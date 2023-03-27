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

    @Autowired
    public EntryService(EntryRepository entryRepository, TagRepository tagRepository, ModelMapper modelMapper)
    {
        this.entryRepository = entryRepository;
        this.tagRepository = tagRepository;
    }

    public Entry save(EntryDTO entryDTO) {
        Entry entry = new Entry();

        entry.setId(entryDTO.getId());
        entry.setType(entryDTO.getType());
        entry.setChecked(entryDTO.isChecked());
        entry.setDateCreated(entryDTO.getDateCreated());
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

}
