package com.tanchee.starcircleplus.entry;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

// import org.springframework.data.relational.core.mapping.Table;

@Component
public interface EntryService
{
    Entry saveEntry(Entry entry);

    List<Entry> fetchEntryList();

    // TODO: Implement updating Entries in EntryServiceImplementation
    /*
    Entry updateEntry(Entry entry, Long entryID);
    */

    void deleteEntryByID(Long entryID);

    EntryDataTransfer getEntryDataTransferFrom(Entry e);
}
