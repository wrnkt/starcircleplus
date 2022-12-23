import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;

public class DBEntryManager
{
    private String database = "starcircleplus";
    private String address = String.format("jdbc:mysql://localhost:6603/%s", database);
    private String user = "root";
    private String pass = "makeitwork";

    private Connection conn;

    public DBEntryManager()
    {
        try
        {
            conn = DriverManager.getConnection(address, user, pass);

            System.out.println("Authenticated.");
            System.out.println("Connection established.");
        } catch (SQLException e)
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
            var sql =
                "CREATE TABLE Entries(ID INT NOT NULL AUTO_INCREMENT PRIMARY KEY, TYPE BIT(2), CONTENT VARCHAR(255), DATECREATED DATETIME, CERTAINOFDATE INT)";
            PreparedStatement statement = conn.prepareStatement(sql);
            int result = statement.executeUpdate();

        } catch (SQLException e)
        {
            System.out.println("Failed to create Entries table.");
            System.out.println(e);
        }
    }

    public void createEntry(Entry entry)
    {
        try
        {
            var sql = "INSERT into Entries () values ()";
            PreparedStatement statement =
                conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        } catch (SQLException e)
        {
            System.out.println("Unable to prepare statement.");
        }
    }

    public static void main(String[] args)
    {
        DBEntryManager dbManager = new DBEntryManager();
        dbManager.createIfNoEntriesTable();

        /*
        Star testEntry = Star.testEntry();
        System.out.println(testEntry);
        */
    }
}
