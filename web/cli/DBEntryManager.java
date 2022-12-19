import java.sql.*;

import java.util.ArrayList;
import java.util.Arrays;

public class DBEntryManager
{
    private String address = "jdbc:mysql://localhost:8008/";
    private String user = "root";
    private String pass = "makeitwork";

    private Connection conn;

    public DBEntryManager()
    {
        try
        {
            conn = DriverManager.getConnection(address, user, pass);
        } catch (SQLException e)
        {
            System.out.println("Unable to connect to database.");
        }
    }

    public void createEntry(Entry entry)
    {
        try
        {
            PreparedStatement statement =
                conn.prepareStatement("insert into Entries () values ()", Statement.RETURN_GENERATED_KEYS);
        } catch (SQLException e)
        {
            System.out.println("Unable to prepare statement.");
        }
    }

    public static void main(String[] args)
    {
        DBEntryManager dbManager = new DBEntryManager();
        Star testEntry = Star.testEntry();
        System.out.println(testEntry);
    }
}
