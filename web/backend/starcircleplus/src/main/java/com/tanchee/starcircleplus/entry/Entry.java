package com.tanchee.starcircleplus.entry;

import java.time.ZonedDateTime;
import java.util.ArrayList;

import java.io.Serializable;

import jakarta.persistence.*;

// import org.springframework.data.annotation.Id;

enum Type
{
    STAR,
    CIRCLE,
    PLUS
}

@Entity
public class Entry implements Serializable
{
    /*
long id, long uuid, Type type, boolean checked, ZonedDateTime dateCreated, ArrayList<String> tags
    */

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    private Long uuid;

    private boolean checked;
    private ZonedDateTime dateCreated;
    private ArrayList<String> tags;

    public Entry(long uuid, Type type, boolean checked, ZonedDateTime dateCreated, ArrayList<String> tags)
    {
        this.id = id;
        this.uuid = uuid;
        this.checked = checked;
        this.dateCreated = dateCreated;
        this.tags = tags;
    }
}
