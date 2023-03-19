package com.tanchee.starcircleplus.entry;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

// import org.springframework.data.relational.core.mapping.Table;

@Component
public interface EntryService
{
    Entry saveEntry(Entry entry);

    List<Entry> fetchEntryList();

    /*
    Entry updateEntry(Entry entry, Long entryID);
    */

    void deleteEntryByID(Long entryID);

    Optional<Entry> findById(Long id);
}
