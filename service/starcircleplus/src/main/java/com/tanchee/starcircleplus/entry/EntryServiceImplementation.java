package com.tanchee.starcircleplus.entry;

import com.tanchee.starcircleplus.tag.TagRepository;
import com.tanchee.starcircleplus.tag.Tag;

import java.util.List;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EntryServiceImplementation implements EntryService
{

    @Autowired
    private EntryRepository entryRepository;

    @Autowired
    private TagRepository tagRepository;

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

    public EntryDataTransfer getEntryDataTransferFrom(Entry entry)
    {
        ArrayList<String> tagList = new ArrayList<String>();
        for( Tag tag : tagRepository.findByEntryEquals(entry) )
        {
            tagList.add(tag.getName());
        }

        EntryDataTransfer entryDataTransfer = new EntryDataTransfer();
        entryDataTransfer.setKey(entry.getId());
        entryDataTransfer.setType(entry.getType());
        entryDataTransfer.setChecked(entry.getChecked());
        entryDataTransfer.setDateCreated(entry.getDateCreated());
        entryDataTransfer.setTags(tagList);
        entryDataTransfer.setContent(entry.getContent());

        return entryDataTransfer;
    }
}
