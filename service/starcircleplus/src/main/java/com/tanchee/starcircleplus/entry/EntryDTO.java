package com.tanchee.starcircleplus.entry;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.stream.*;

import java.io.Serializable;
import java.lang.StringBuilder;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;


@Entity
public class EntryDTO implements Serializable
{
    //@Id
    private Long id;

    @Enumerated(EnumType.STRING)
    private Type type;

    private boolean checked;
    private ZonedDateTime dateCreated;
    private ArrayList<String> tags;

    private String content;

    public EntryDTO() {}

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Type getType() {
        return this.type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public boolean isChecked() {
        return this.checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public ZonedDateTime getDateCreated() {
        return this.dateCreated;
    }

    public void setDateCreated(ZonedDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    public ArrayList<String> getTags() {
        return this.tags;
    }

    public void setTags(ArrayList<String> tags) {
        this.tags = tags;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        StringBuilder desc = new StringBuilder();
        desc.append("\n");
        desc.append("id: " + getId() + "\n");
        desc.append("type: " + getType() + "\n");
        desc.append("checked: " + isChecked() + "\n");
        desc.append("dateCreated: " + getDateCreated() + "\n");
        desc.append("content: " + getContent() + "\n");
        String tags = (getTags() == null) ?
            ("None") :
            (getTags().stream().collect(Collectors.joining(",")));
        desc.append("tags: " + tags + "\n");

        return desc.toString();
    }
}
