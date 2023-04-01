package com.tanchee.starcircleplus.entry;

import com.tanchee.starcircleplus.tag.Tag;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import io.hypersistence.utils.spring.repository.BaseJpaRepository;

@Repository
public interface EntryRepository extends BaseJpaRepository<Entry, Long>
{
    List<Entry> findByTagsEquals(Tag tag);

    @Query("from Entry")
    List<Entry> findAll();

    /*
    @Modifying
@Query("update Entry e set e.type = ?1, e.checked = ?2, e.content = ?3, e.tags = ?4 where e.id = ?5")
    void setEntryById(Type type, boolean checked, String content, Set<Tags> tags, Long id);
    */
}
