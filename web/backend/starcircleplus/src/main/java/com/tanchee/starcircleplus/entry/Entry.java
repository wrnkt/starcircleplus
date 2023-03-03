package com.tanchee.starcircleplus.entry;

import com.tanchee.starcircleplus.tag.*;

import java.util.Set;
import java.util.HashSet;

import java.time.ZonedDateTime;
import java.util.ArrayList;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;
import jakarta.persistence.ManyToMany;

enum Type
{
    STAR,
    CIRCLE,
    PLUS
}

@Entity
public class Entry implements Serializable
{
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    //private Long uuid;

    @Enumerated(EnumType.STRING)
    private Type type;

    private boolean checked;
    private ZonedDateTime dateCreated;

    private String content;

    @ManyToMany(mappedBy = "entry")
    private Set<Tag> tags = new HashSet<Tag>();

    public Entry()
    {
    }

    public Entry(Type type, boolean checked, ZonedDateTime dateCreated, String content)
    {
        this.type = type;
        this.checked = checked;
        this.dateCreated = dateCreated;
        this.content = content;
    }

    public Type getType()
    {
        return this.type;
    }

    public boolean getChecked()
    {
        return this.checked;
    }

    public ZonedDateTime getDateCreated()
    {
        return this.dateCreated;
    }

    public String getContent()
    {
        return this.content;
    }

}
