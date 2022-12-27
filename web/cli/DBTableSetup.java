import java.sql.*;

public class DBTableSetup
{
    private Connection conn;

    /**
     * Class contructor defining a new connection for class methods to use.
     */
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

    /**
     * Class contructor providing a connection to be used instead of defining a new one.
     */
    public DBTableSetup(Connection passedConn)
    {
        conn = passedConn;
    }

    /**
     * Creates the Entry Table. Currently the Entry table's structure is defined here.
     */
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

    /**
     * Check if an entry table already exists.
     *
     * @return if Entries table exists.
     */
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

    public void createTagsTable()
    {
        try
        {
            var sql = "CREATE TABLE Tags(ID INT PRIMARY KEY NOT NULL AUTO_INCREMENT, TAG VARCHAR(255))";
            PreparedStatement statement = conn.prepareStatement(sql);
            int result = statement.executeUpdate();

        } catch (SQLException e)
        {
            System.out.println("Failed to create Entries table.");
            System.out.println(e);
        }
    }

    public boolean tagsTableExists()
    {
        try
        {
            var sql = "SHOW tables like 'Tags'";
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            
            if (rs.next())
            {
                System.out.println("Tags table exists.");
                return true;
            }
            else
            {
                return false;
            }
        }
        catch (SQLException e)
        {
            System.out.println(e);
        }
        return false;
    }

    public void createIfNoTagsTable()
    {
        if(!tagsTableExists())
        {
            createTagsTable();
        }
    }

    public void createEntryTagTable()
    {
        try
        {
            var sql = "CREATE TABLE Entry_Tag(EntryID INT NOT NULL, TagID INT NOT NULL)";
            PreparedStatement statement = conn.prepareStatement(sql);
            int result = statement.executeUpdate();

        } catch (SQLException e)
        {
            System.out.println("Failed to create Entry_Tag table.");
            System.out.println(e);
        }
    }

    public boolean entryTagTableExists()
    {
        try
        {
            var sql = "SHOW tables like 'Entry_Tag'";
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            
            if (rs.next())
            {
                System.out.println("Entry_Tag table exists.");
                return true;
            }
            else
            {
                return false;
            }
        }
        catch (SQLException e)
        {
            System.out.println(e);
        }
        return false;
    }

    public void createIfNoEntryTagTable()
    {
        if(!entryTagTableExists())
        {
            createEntryTagTable();
        }
    }

    public void setupTables()
    {
        createIfNoEntriesTable();
        createIfNoTagsTable();
        createIfNoEntryTagTable();
    }
}
