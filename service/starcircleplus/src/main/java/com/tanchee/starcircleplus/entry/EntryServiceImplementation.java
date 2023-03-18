package com.tanchee.starcircleplus.entry;

import com.tanchee.starcircleplus.tag.TagRepository;
import com.tanchee.starcircleplus.tag.Tag;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;
import java.time.ZonedDateTime;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.modelmapper.ModelMapper;

@Service
public class EntryServiceImplementation implements EntryService
{

    @Autowired
    private EntryRepository entryRepository;

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public Entry saveEntry(Entry entry)
    {
        return entryRepository.save(entry);
    }

    @Override
    public List<Entry> fetchEntryList()
    {
        return (List<Entry>) entryRepository.findAll();
    }

    /*
    @Override
    public Entry updateEntry(Entry entry, Long entryID);
    */

    @Override
    public void deleteEntryByID(Long entryID)
    {
        entryRepository.deleteById(entryID);
    }

    @Override
    public Optional<Entry> findById(Long id) {

        return entryRepository.findById(id);
    }

    public EntryDataTransfer convertToDTO(Entry entry)
    {
        EntryDataTransfer entryData = modelMapper.map(entry, EntryDataTransfer.class);
        return entryData;
    }

    public Entry convertToEntity(EntryDataTransfer entryData) throws ParseException
    {
        Entry entry = modelMapper.map(entryData, Entry.class);
        if( entryData.getId() != null )
        {
            Entry oldEntry = findById(entryData.getId()).get();
            entry.setType(oldEntry.getType());
            entry.setChecked(oldEntry.getChecked());
            entry.setDateCreated(oldEntry.getDateCreated());
            entry.setContent(oldEntry.getContent());
            entry.setTags(oldEntry.getTags());
            // NOTE: check for values existing in the data transfer
            // and only load those to the new entry
        } else {
            entry.setDateCreated(ZonedDateTime.now());
        }
        return entry;
    }
}
