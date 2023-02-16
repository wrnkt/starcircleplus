import java.io.Serializable;
import java.util.Optional;

import java.lang.Object;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.time.ZonedDateTime;

public class Entry implements Serializable
{
    // done this way to allow external reference as Entry.Type.Star
    public enum Type
    {
        Star,
        Circle,
        Plus;
    }

    private Type entryType;

    private String content;
    private ZonedDateTime dateCreated;
    private ArrayList<String> tags = new ArrayList<String>();

    private boolean certainOfDate = false;


    public Entry()
    {
        this("", new ArrayList<String>());
        certainOfDate = false;
    }

    public Entry(String content, ArrayList<String> tags)
    {
        dateCreated = ZonedDateTime.now();
        this.content = content;
        setTagList(tags);
        certainOfDate = true;
    }

    public Entry(String content, ArrayList<String> tags, Type t)
    {
        this(content, tags);
        setEntryType(t);
    }

    /*
     * Check for entryType(Star and Plus should never be checked)
     * Add setCheckedStatus(bool status) to set value of checked.
     */
    public boolean checked()
    {
        return false;
    }

    public void setEntryType(Type t)
    {
        entryType = t;
    }

    public Type getEntryType()
    {
        return entryType;
    }


    public void setDateCreated(ZonedDateTime newDateCreated)
    {
        this.dateCreated = newDateCreated;
    }
    
    public ZonedDateTime getDateCreated()
    {
        return dateCreated;
    }

    public Optional<ZonedDateTime> getDateChecked()
    {
        return Optional.empty();
    }

    public boolean getCertainOfDate()
    {
        return certainOfDate;
    }

    public String getIdentifier()
    {
        return (
            switch(entryType)
            {
                case Star -> "*";
                case Circle -> "o";
                case Plus -> "+";
                default -> "Unassigned entryType";
            }
        );
    }

    // NOTE: Adding an updateTagList function
    // that works the way this one does may be more effective.
    // It could also return true if an update was required
    // and false otherwise.
    // This function could then call updateTagList on each value
    // in the passed list
    public void setTagList(ArrayList<String> tags)
    {
        for(String tag : tags)
        {
            if(!this.tags.contains(tag))
                this.tags.add(tag);
        }
    }

    public ArrayList<String> getTagList()
    {
        return new ArrayList<String>(tags);
    }

    public void setContent(String s)
    {
        content = s;
    }

    public String getContent()
    {
        return content;
    }

    public String toString()
    {
        return String.join(" ", getIdentifier(), ":", content) +
            "\n" +
            getTagList().stream().collect(Collectors.toList());
    }


    public static void main(String[] args) {
        Entry e = new Entry("test", new ArrayList<String>());
        e.setEntryType(Type.Star);
        System.out.println(e);
    }

}
