import java.util.Arrays;
import java.util.ArrayList;

import java.time.ZonedDateTime;

public abstract class Entry
{
    private transient final String divider = ":";
    private final ZonedDateTime dateCreated;
    private String content;
    private ArrayList<String> tags;

    public Entry(String content, String... tags)
    {
        dateCreated = ZonedDateTime.now();
        this.content = content;
        this.tags = new ArrayList<String>(Arrays.asList(tags));
    }

    public final ZonedDateTime getDateCreated()
    {
        return dateCreated;
    }

    public abstract String getIdentifier();

    public String getContent()
    {
        return content;
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
