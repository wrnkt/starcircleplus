package com.tanchee.starcircleplus.tag;

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
public class TagDTO implements Serializable
{
    @Id
    private Long id;

    private ArrayList<String> tagsList;

    public TagDTO()
    {
    }

    public TagDTO(ArrayList<String> tagsList)
    {
        this.tagsList = tagsList;
    }

    public void setTagsList(ArrayList<String> tagsList)
    {
        this.tagsList = tagsList;
    }

    public ArrayList<String> getTagsList()
    {
        return this.tagsList;
    }

}

