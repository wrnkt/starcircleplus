package com.tanchee.starcircleplus;

import java.time.ZonedDateTime;
import java.util.ArrayList;

enum Type
{
    STAR,
    CIRCLE,
    PLUS
}

public record Entry(long id, long uuid, Type type, boolean checked, ZonedDateTime dateCreated, ArrayList<String> tags)
{
}
