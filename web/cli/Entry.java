import java.io.Serializable;

import java.lang.Object;

import java.util.Arrays;
import java.util.ArrayList;
import java.time.ZonedDateTime;

public abstract class Entry implements Serializable
{
    private transient final String divider = ":";
    private ZonedDateTime dateCreated;
    private boolean certainOfDate = false;
    private String content;
    private ArrayList<String> tags = new ArrayList<String>();

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

    public final String getEntryType()
    {
        return this.getClass().getSimpleName();
    }

    public final boolean getCertainOfDate()
    {
        return certainOfDate;
    }
    
    public final ZonedDateTime getDateCreated()
    {
        return dateCreated;
    }

    public final void setDateCreated(ZonedDateTime newDateCreated)
    {
        this.dateCreated = newDateCreated;
    }

    public abstract String getIdentifier();

    public String getContent()
    {
        return content;
    }

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

    public final String shortEntry()
    {
        return String.join(" ", getIdentifier(), divider, content);
    }

    public String toString()
    {
        return detailEntry();
    }

}
