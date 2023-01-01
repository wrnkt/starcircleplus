import java.util.Scanner;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;

public class DBEntryManager
{
    // Extract these settings to properties file
    // (.properties, xml, json)
    // think about what might need to use this file in the future
    // DBSetup needs to get the address of the server
    // but will create the database if it doesnt exist.
    // The database name needs to then be accessible by the DBEntryManager
    private final String database = "starcircleplus";
    private final String address = String.format("jdbc:mysql://localhost:6603/%s", database);
    private final String user = "root";
    private final String pass = "makeitwork";

    private final int starVal = 0;
    private final int plusVal = 1;
    private final int uncheckedCircleVal = 2;
    private final int checkedCircleVal = 3;

    private Connection conn;

    private DBTagManager dbtm;

    public DBEntryManager()
    {
        try
        {
            conn = DriverManager.getConnection(address, user, pass);
            System.out.println("Connection established with authentication.");
        }
        catch (SQLException e)
        {
            System.out.println("Unable to connect to database.");
            System.out.println(e);
        }

        DBSetup dbs = new DBSetup(conn);
        dbs.createAppDBIfNone();
        dbs.ensureInAppDB();

        DBTableSetup dbts = new DBTableSetup(conn);
        dbts.setupTables();

        dbtm = new DBTagManager(conn);
    }

    public void insertEntry(Entry entry) throws Exception
    {
        var sql = "INSERT into Entries (TYPE, CONTENT, DATECREATED, CERTAINOFDATE, DATECHECKED) values (?, ?, ?, ?, ?)";

        try(PreparedStatement statement = conn.prepareStatement(sql))
        {
            if(entry instanceof Star)
            {
                statement.setInt(1, starVal);
            }
            else if(entry instanceof Plus)
            {
                statement.setInt(1, plusVal);
            }
            else if(entry instanceof Circle)
            {
                if(entry.checked()) {
                    statement.setInt(1, checkedCircleVal);
                } else {
                    statement.setInt(1, uncheckedCircleVal);
                }
            }
            else
            {
                throw new Exception("Unhandled Entry type.");
            }

            statement.setString(2, EntryFormatter.formatContent(entry));
            statement.setString(3, EntryFormatter.formatDateCreated(entry));
            if(entry.getCertainOfDate()) {
                statement.setBoolean(4, true);
            } else {
                statement.setBoolean(4, false);
            }
            if(entry.getDateChecked().isPresent())
            {
                statement.setString(5, EntryFormatter.formatDateChecked(entry));
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
        // entry.getTagList();
    }

    /*
    public boolean doesEntryExist(Entry e)
    {
    }
     */

    public static void main(String[] args)
    {
        DBEntryManager dbem = new DBEntryManager();
        Prompter prompter = new Prompter();

        Entry current = prompter.promptForEntry();
        
        try {
            dbem.insertEntry(current);
        }
        catch (Exception e)
        {
            System.out.println(e);
            System.out.println("Unable to save entry.");
        }
    }
}
