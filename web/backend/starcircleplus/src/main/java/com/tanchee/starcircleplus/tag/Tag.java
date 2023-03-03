package com.tanchee.starcircleplus.tag;

import com.tanchee.starcircleplus.entry.Entry;

import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Tag
{
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    private String name;

    @OneToMany
    private Set<Entry> entry;

    public void setName(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return this.name;
    }
    
    public void setEntries(Set<Entry> entry)
    {
        this.entry = entry;
    }

    public Set<Entry> getEntries()
    {
        return this.entry;
    }
}
