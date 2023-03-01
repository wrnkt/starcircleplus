package  com.tanchee.starcircleplus;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EntryServiceImplementation implements EntryService
{

    @Autowired
    private EntryRepository entryRepository;

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
}
