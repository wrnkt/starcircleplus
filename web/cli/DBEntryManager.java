import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;

public class DBEntryManager
{
    private String database = "starcircleplus";
    private String address = String.format("jdbc:mysql://localhost:6603/%s", database);
    private String user = "root";
    private String pass = "makeitwork";

    private String starBitVal = "b'00'";
    private String plusBitVal = "b'01'";
    private String uncheckedCircleBitVal = "b'10'";
    private String checkedCircleBitVal = "b'11'";

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
            var sql = "CREATE TABLE Entries(ID INT NOT NULL AUTO_INCREMENT PRIMARY KEY, TYPE BIT(2), CONTENT VARCHAR(255), DATECREATED TIMESTAMP, CERTAINOFDATE BOOLEAN)";
            PreparedStatement statement = conn.prepareStatement(sql);
            int result = statement.executeUpdate();

        } catch (SQLException e)
        {
            System.out.println("Failed to create Entries table.");
            System.out.println(e);
        }
    }

    public void insertEntry(Entry entry)
    {
        var sql = "INSERT into Entries (ID, TYPE, CONTENT, DATECREATED, CERTAINOFDATE) values (?, ?, ?, ?, ?)";

        try(PreparedStatement statement = conn.prepareStatement(sql))
        {
            // statement.setInt(1, n);
            statement.setString(2, "b'00'");
            statement.setString(3, EntryFormatter.formatContent(entry));
            // statement.setString(4, );
            // statement.setBoolean(5, );
            int result = statement.executeUpdate();
        } catch (SQLException e)
        {
            System.out.println("Unable to prepare statement.");
            System.out.println(e);
        }
    }

    public static void main(String[] args)
    {
        DBEntryManager dbManager = new DBEntryManager();
        dbManager.createIfNoEntriesTable();

        Star testEntry = Star.testEntry();
        System.out.println(testEntry);
        System.out.println(EntryFormatter.formatDateCreated(testEntry));
    }
}
