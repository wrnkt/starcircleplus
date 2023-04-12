package com.tanchee.starcircleplus.tag;

import com.tanchee.starcircleplus.entry.Entry;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import io.hypersistence.utils.spring.repository.BaseJpaRepository;

@Repository
public interface TagRepository extends BaseJpaRepository<Tag, Long>
{
    List<Tag> findByNameEquals(String name);

    Optional<Tag> findByName(String name);

    List<Tag> findByEntryEquals(Entry entry);

    @Query("from Tag")
    List<Tag> findAll();
}
