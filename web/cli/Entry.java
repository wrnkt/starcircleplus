import java.io.Serializable;
import java.util.Optional;

import java.lang.Object;

import java.util.Arrays;
import java.util.ArrayList;
import java.time.ZonedDateTime;

public class Entry implements Serializable
{

    private ZonedDateTime dateCreated;
    private boolean certainOfDate = false;
    private String content;
    private ArrayList<String> tags = new ArrayList<String>();

    // done this way to allow external reference as Entry.Type.Star
    public enum Type
    {
        Star,
        Circle,
        Plus;
    }

    private Type entryType;

    public Entry()
    {
        dateCreated = ZonedDateTime.now();
        certainOfDate = false;
        this.content = "";
        this.tags = new ArrayList<String>();
    }

    public Entry(String content, ArrayList<String> tags)
    {
        dateCreated = ZonedDateTime.now();
        certainOfDate = true;
        this.content = content;
        setTagList(tags);
    }

    public boolean checked()
    {
        return false;
    }

    /*
     * I originally wanted the entry type to be unchangeable
     * but now I think it will be okay if it is exposed
     * to change
     */
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

    public String formatTagList()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("Tags:");
        for(String tag : tags)
        {
            sb.append(" #");
            sb.append(tag);
        }

        return sb.toString();
    }

    public String detailEntry()
    {
        return shortEntry() + "\n" + formatTagList();
    }

    public String shortEntry()
    {
        return String.join(" ", getIdentifier(), ":", content);
    }

    public String toString()
    {
        return detailEntry();
    }



}
