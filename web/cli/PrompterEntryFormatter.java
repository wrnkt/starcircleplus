public class PrompterEntryFormatter extends EntryFormatter
{
    public static String detailEntry(Entry e)
    {
        return shortEntry(e) + "\n" + formatTagList(e);
    }

    public static String shortEntry(Entry e)
    {
        return String.join(" ", e.getIdentifier(), ":", e.getContent());
    }

    public static String formatTagList(Entry e)
    {
        StringBuilder sb = new StringBuilder();
        sb.append("Tags:");
        for(String tag : e.getTagList())
        {
            sb.append(" #");
            sb.append(tag);
        }

        return sb.toString();
    }
}

