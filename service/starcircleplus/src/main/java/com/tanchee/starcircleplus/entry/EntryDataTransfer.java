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


@Entity
public class EntryDataTransfer implements Serializable
{
    @Id
    private Long id;

    private Long key;

    @Enumerated(EnumType.STRING)
    private Type type;

    private boolean checked;
    private ZonedDateTime dateCreated;
    private ArrayList<String> tags;

    private String content;

    public EntryDataTransfer()
    {
    }

    public EntryDataTransfer(Type type, boolean checked, ZonedDateTime dateCreated, ArrayList<String> tags, String content)
    {
        this.type = type;
        this.checked = checked;
        this.dateCreated = dateCreated;
        this.tags = tags;
        this.content = content;
    }

    public EntryDataTransfer(Long key, Type type, boolean checked, ZonedDateTime dateCreated, ArrayList<String> tags, String content)
    {
        this.key = key;
        this.type = type;
        this.checked = checked;
        this.dateCreated = dateCreated;
        this.tags = tags;
        this.content = content;
    }

    public void setKey(Long key)
    {
        this.key = key;
    }

    public Long getKey()
    {
        return this.key;
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
