import java.util.Optional;

import java.time.ZonedDateTime;

import java.util.ArrayList;
import java.time.format.DateTimeFormatter;

/*
 * This class defines the methods used to set SQL statement fields in the DBEntryManager.
 */

public class DBEntryFormatter
{
    /*
     * These methods could be defined as anonymous methods in the
     * DBEntryManager. This DBEntryFormatter could actually be defined
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

    public static String formatDateChecked(Entry e)
    {
        DateTimeFormatter formatter =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        Optional<ZonedDateTime> dateTime = e.getDateChecked();
        if (!dateTime.isPresent()) return "";
        String sqlFormattedDate = e.getDateChecked().get().format(formatter);
        return sqlFormattedDate;
    }

    public static ArrayList<String> formatTagList(Entry e)
    {
        return e.getTagList();
    }
}
