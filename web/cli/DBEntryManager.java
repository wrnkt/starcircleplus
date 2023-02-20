import java.util.Scanner;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Arrays;

import java.util.Optional;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

interface StringFieldFormatter
{
    public String format(Entry e);
}

interface IntFieldFormatter
{
    public int format(Entry e);
}

interface BooleanFieldFormatter
{
    public boolean format(Entry e);
}

interface ListFormatter
{
    public ArrayList<String> format(Entry e);
}


public class DBEntryManager
{
    private static HashMap<Entry.Type, Integer> entryTypeToTableKey = new HashMap<>();

    static {
        entryTypeToTableKey.put(Entry.Type.Star, 0);
        entryTypeToTableKey.put(Entry.Type.Plus, 1);
        entryTypeToTableKey.put(Entry.Type.Circle, 2);
    }

    private final int starVal = 0;
    private final int plusVal = 1;
    private final int uncheckedCircleVal = 2;
    private final int checkedCircleVal = 3;
    // FIX: checked should just be a separate field only observed in circles.

    private Connection conn;

    private DBTagManager dbtm;

    public DBEntryManager()
    {
        try
        {
            conn = DriverManager.getConnection(DBConfig.address, DBConfig.user, DBConfig.pass);
            System.out.println("Connection established with authentication.");
        }
        catch (SQLException e)
        {
            System.out.println("Unable to connect to database.");
            System.out.println(e);
        }

        // NOTE: the next 8 lines should probably go into the try block
        DBSetup dbs = new DBSetup(conn);
        dbs.createAppDBIfNone();
        dbs.ensureInAppDB();

        DBTableSetup dbts = new DBTableSetup(conn);
        dbts.setupTables();

        dbtm = new DBTagManager(conn);
    }


    // Formatters for building statement
    private IntFieldFormatter typeFormatter = (Entry e) -> {
        return entryTypeToTableKey.get(e.getEntryType()).intValue();
    };

    private StringFieldFormatter contentFormatter = (Entry e) -> (e.getContent());
    private IntFieldFormatter certainOfDateFormatter = (Entry e) -> (e.getCertainOfDate() ? 1 : 0);
    private StringFieldFormatter dateCreatedFormatter = (Entry e) -> {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDate = e.getDateCreated().format(formatter);
        return formattedDate;
    };
    private StringFieldFormatter dateCheckedFormatter = (Entry e) -> {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDate = e.getDateChecked().get().format(formatter);
        return formattedDate;
    };
    private ListFormatter tagListFormatter = (Entry e) -> (e.getTagList());

    public void insertEntry(Entry entry) throws Exception
    {
        var sql = "INSERT into Entries (TYPE, CONTENT, DATECREATED, CERTAINOFDATE, DATECHECKED) values (?, ?, ?, ?, ?)";

        try(PreparedStatement statement = conn.prepareStatement(sql))
        {

            // TODO: separate logic building the sql statement from the insert 
            //       this means a function that returns a sql statement when given an Entry

            statement.setInt(1, typeFormatter.format(entry));
            statement.setString(2, contentFormatter.format(entry));
            statement.setString(3, dateCreatedFormatter.format(entry));

            if(entry.getCertainOfDate()) {
                statement.setBoolean(4, true);
            } else {
                statement.setBoolean(4, false);
            }
            if(entry.getDateChecked().isPresent())
            {
                statement.setString(5, dateCheckedFormatter.format(entry));
            } else {
                statement.setString(5, null);
            }

            dbtm.updateTagsTable(entry);

            int result = statement.executeUpdate();
        } catch (SQLException e)
        {
            System.out.println("Unable to save entry.");
            System.out.println(e);
        }
    }

    // TODO: May need to change this to be table specific
    public int getLastInsertId()
    {
        try
        {
            var sql = String.format("SELECT LAST_INSERT_ID()");
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            rs.next();
            return rs.getInt(1);
        }
        catch (SQLException e)
        {
            System.out.println(e);
        }
        return 0;
    }

    

    public static void main(String[] args)
    {
        DBEntryManager dbem = new DBEntryManager();

        Prompter prompter = new Prompter();
        Entry current = prompter.promptForEntry();
        
        try {
            dbem.insertEntry(current);
            System.out.println(dbem.getLastInsertId());
        }
        catch (Exception e)
        {
            System.out.println(e);
            System.out.println("Unable to save entry.");
        }
    }
}


