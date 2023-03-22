package com.tanchee.starcircleplus.entry;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.*;

import java.io.Serializable;
import java.lang.StringBuilder;


public class EntryDTO implements Serializable
{
    private Long id;

    private Type type;

    private boolean checked;
    private ZonedDateTime dateCreated;
    private List<String> tags = new ArrayList<String>();

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

    public List<String> getTags() {
        //return new ArrayList(Arrays.asList("TESTING1", "TESTING2"));
        return this.tags;
    }

    public void setTags(List<String> tags) {
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
        desc.append("tags: " + getTags() + "\n");

        return desc.toString();
    }
}
