package com.tanchee.starcircleplus.tag;

import com.tanchee.starcircleplus.entry.Entry;

import java.util.Set;
import java.util.HashSet;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;

@Entity
public class Tag
{
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    private String name;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
        name = "tag_entry", 
        joinColumns = { @JoinColumn(name = "tag_id") }, 
        inverseJoinColumns = { @JoinColumn(name = "entry_id") }
    )
    Set<Entry> entry = new HashSet<Entry>();;

    public Tag(String name)
    {
        this.name = name;
    }

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

    public void addEntry(Entry entry)
    {
        this.entry.add(entry);
    }
}
