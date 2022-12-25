import java.sql.*;

public class DBTableSetup
{
    private Connection conn;

    public DBTableSetup()
    {
        try
        {
            conn = DriverManager.getConnection(DBConfig.address, DBConfig.user, DBConfig.pass);
            System.out.println("Connection established with authentication.");
        }
        catch (SQLException e)
        {
            System.out.println(e);
            System.out.println("Unable to connect to database.");
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

    public boolean entryTableExists()
    {
        try
        {
            var sql = "SHOW tables like 'Entries'";
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            
            if (rs.next())
            {
                System.out.println("Entries table exists.");
                return true;
            }
            else
            {
                System.out.println("Entries table doesn't exist.");
                return false;
            }

        } catch (SQLException e)
        {
            System.out.println("Failed to execute show table query.");
            System.out.println(e);
        }
        return false;
    }

    public void createIfNoEntriesTable()
    {
        if(!entryTableExists())
        {
            createEntriesTable();
        }
    }

    public static void main(String[] args)
    {
        DBSetup dbs = new DBSetup();
        dbs.createAppDBIfNone();
        dbs.enterAppDB();
    }
}
