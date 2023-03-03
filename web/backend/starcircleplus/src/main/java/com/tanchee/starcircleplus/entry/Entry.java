package com.tanchee.starcircleplus.entry;

import java.time.ZonedDateTime;
import java.util.ArrayList;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;

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

    private Long uuid;

    @Enumerated(EnumType.STRING)
    private Type type;

    private boolean checked;
    private ZonedDateTime dateCreated;
    private ArrayList<String> tags;

    private String content;

    public Entry()
    {
    }

    public Entry(Long uuid, Type type, boolean checked, ZonedDateTime dateCreated, ArrayList<String> tags, String content)
    {
        this.uuid = uuid;
        this.type = type;
        this.checked = checked;
        this.dateCreated = dateCreated;
        this.tags = tags;
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

    public ArrayList<String> getTags()
    {
        return this.tags;
    }

    public String getContent()
    {
        return this.content;
    }

}
