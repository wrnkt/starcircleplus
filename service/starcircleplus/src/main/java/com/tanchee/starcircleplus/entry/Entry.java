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
import jakarta.persistence.CascadeType;

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

    @Enumerated(EnumType.STRING)
    private Type type;

    private boolean checked;
    private ZonedDateTime dateCreated;

    private String content;

    @ManyToMany(mappedBy = "entry", cascade = CascadeType.ALL)
    private Set<Tag> tags = new HashSet<Tag>();

    public Entry()
    {
    }

    public Entry(Type type, boolean checked, ZonedDateTime dateCreated, String content)
    {
        this.type = type;
        if (this.type == Type.CIRCLE ) {
            this.checked = checked;
        } else {
            this.checked = false;
        }

        this.dateCreated = dateCreated;
        this.content = content;
    }

    public Entry(Long id, Type type, boolean checked, ZonedDateTime dateCreated, String content)
    {
        this.id = id;
        this.type = type;
        if (this.type == Type.CIRCLE ) {
            this.checked = checked;
        } else {
            this.checked = false;
        }

        this.dateCreated = dateCreated;
        this.content = content;
    }

    public Long getId()
    {
        return this.id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Type getType()
    {
        return this.type;
    }

    public void setType(Type type)
    {
        this.type = type;
    }

    public boolean isChecked()
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

    public String getContent()
    {
        return this.content;
    }

    public void setContent(String content)
    {
        this.content = content;
    }

    public Set<Tag> getTags() {
        return this.tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }
    
    public void addTag(Tag tag)
    {
        tags.add(tag);
    }

    /*
    public void removeTag(Tag tag)
    {
        tags.remove(
                tagRepository.
                );
    }
    */

}
