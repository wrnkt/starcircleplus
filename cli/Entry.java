import java.io.Serializable;
import java.util.Optional;

import java.lang.Object;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.time.ZonedDateTime;

/*
 * FEATURE: tag entries with the current date every time over 50% of them are changed.
 *          possibly 30% with smaller content and 40% with larger content
 */

public class Entry implements Serializable
{
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
    private boolean checked = false;


    public Entry()
    {
        this("", new ArrayList<String>());
    }
    public Entry(String content, ArrayList<String> tags)
    {
        this(content, tags, Type.Star);
    }

    public Entry(String content, ArrayList<String> tags, Type t)
    {
        setDateCreated(ZonedDateTime.now());
        // FIX: maybe the date and certainty should be set when the content is updated?
        //      or maybe when a certain proportion of the content is changed?
        //      this logic may need to be put another place.
        setCertainOfDate(true);

        setContent(content);
        setTagList(tags);
        setEntryType(t);
    }

    public void setCheckedStatus(boolean status)
    {
        checked = status;
    }

    public boolean getCheckedStatus()
    {
        return checked;
    }

    public boolean isChecked()
    {
        if (getEntryType() == Type.Star || getEntryType() == Type.Plus)
            return false;
        else
            return getCheckedStatus();
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

    public void setCertainOfDate(boolean b)
    {
        certainOfDate = b;
    }

    public boolean getCertainOfDate()
    {
        return certainOfDate;
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
