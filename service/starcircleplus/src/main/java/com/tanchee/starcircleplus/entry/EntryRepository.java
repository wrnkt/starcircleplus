package com.tanchee.starcircleplus.entry;

import com.tanchee.starcircleplus.tag.Tag;

import java.util.List;

//import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntryRepository extends CrudRepository<Entry, Long>
{
    List<Entry> findByTagsEquals(Tag tag);
}
