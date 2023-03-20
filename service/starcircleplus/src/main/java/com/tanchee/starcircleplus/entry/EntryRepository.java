package com.tanchee.starcircleplus.entry;

import com.tanchee.starcircleplus.tag.Tag;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntryRepository extends JpaRepository<Entry, Long>
{
    List<Entry> findByTagsEquals(Tag tag);

    /*
    @Modifying
@Query("update Entry e set e.type = ?1, e.checked = ?2, e.content = ?3, e.tags = ?4 where e.id = ?5")
    void setEntryById(Type type, boolean checked, String content, Set<Tags> tags, Long id);
    */
}
