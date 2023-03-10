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

    public Long getKey()
    {
        return this.key;
    }

    public void setKey(Long key)
    {
        this.key = key;
    }

    public Type getType()
    {
        return this.type;
    }

    public void setType(Type type)
    {
        this.type = type;
    }

    public boolean getChecked()
    {
        return this.checked;
    }

    public void setChecked(boolean checked)
    {
        this.checked = checked;
    }

    public ZonedDateTime getDateCreated()
    {
        return this.dateCreated;
    }

    public void setDateCreated(ZonedDateTime dateCreated)
    {
        this.dateCreated = dateCreated;
    }

    public ArrayList<String> getTags()
    {
        return this.tags;
    }

    public void setTags(ArrayList<String> tags)
    {
        this.tags = tags;
    }

    public String getContent()
    {
        return this.content;
    }

    public void setContent(String content)
    {
        this.content = content;
    }

}
