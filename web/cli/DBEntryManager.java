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

    private final String starBitVal = "00";
    private final String plusBitVal = "01";
    private final String uncheckedCircleBitVal = "10";
    private final String checkedCircleBitVal = "11";

    private Connection conn;

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
    }

    public void createIfNoEntriesTable()
    {
        try
        {
            var sql = "SHOW tables like 'Entries'";
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            
            if (rs.next())
            {
                System.out.println("Entries table exists.");
            }
            else
            {
                System.out.println("Entries table doesn't exist.");
                createEntriesTable();
                System.out.println("Created Entries table.");
            }

        } catch (SQLException e)
        {
            System.out.println("Failed to execute show table query.");
            System.out.println(e);
        }
    }

    public void createEntriesTable()
    {
        try
        {
            var sql = "CREATE TABLE Entries(ID INT PRIMARY KEY NOT NULL AUTO_INCREMENT, TYPE BIT(2), CONTENT VARCHAR(255), DATECREATED TIMESTAMP, CERTAINOFDATE BOOLEAN)";
            PreparedStatement statement = conn.prepareStatement(sql);
            int result = statement.executeUpdate();

        } catch (SQLException e)
        {
            System.out.println("Failed to create Entries table.");
            System.out.println(e);
        }
    }

    public void insertEntry(Entry entry) throws Exception
    {
        var sql = "INSERT into Entries (TYPE, CONTENT, DATECREATED, CERTAINOFDATE) values (b?, ?, ?, ?)";

        try(PreparedStatement statement = conn.prepareStatement(sql))
        {
            if(entry instanceof Star)
            {
                statement.setString(1, starBitVal);
            }
            else if(entry instanceof Plus)
            {
                statement.setString(1, plusBitVal);
            }
            else if(entry instanceof Circle)
            {
                if(entry.checked()) {
                    statement.setString(1, checkedCircleBitVal);
                } else {
                    statement.setString(1, uncheckedCircleBitVal);
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

            int result = statement.executeUpdate();
        } catch (SQLException e)
        {
            System.out.println("Unable to save entry.");
            System.out.println(e);
        }
    }

    /*
    public boolean doesEntryExist(Entry e)
    {
    }
     */

    public static void main(String[] args)
    {
        DBEntryManager dbManager = new DBEntryManager();
        dbManager.createIfNoEntriesTable();

        Star testEntry = Star.testEntry();
        System.out.println(testEntry);

        try {
            dbManager.insertEntry(testEntry);
        } catch (Exception e)
        {
            System.out.println(e);
            System.out.println("Unable to save entry.");
        }
    }
}
