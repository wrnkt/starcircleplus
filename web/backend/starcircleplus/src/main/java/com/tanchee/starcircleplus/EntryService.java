package com.tanchee.starcircleplus;

import org.springframework.beans.factory.annotation.Autowired;

public interface EntryService
{
    Entry saveEntry(Entry entry);

    List<Entry> fetchEntryList();

    Entry updateEntry(Entry entry, Long entryID);

    void deleteEntryByID(Long entryID);
}
