import java.time.format.DateTimeFormatter;

public class EntryFormatter
{
    /*
     * These methods could be defined as anonymous methods in the
     * DBEntryManager. This EntryFormatter could actually be defined
     * as an inner class in DBEntryManager. Consider to avoid
     * splitting logic between files.
     */
    public static String formatContent(Entry e)
    {
        return e.getContent();
    }

    public static int formatCertainOfDate(Entry e)
    {
        return e.getCertainOfDate() ? 1 : 0; 
    }

    public static String formatDateCreated(Entry e)
    {
        DateTimeFormatter formatter =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String sqlFormattedDate = e.getDateCreated().format(formatter);
        return sqlFormattedDate;
    }

    public static void formatTagList(Entry e)
    {
    }
}
