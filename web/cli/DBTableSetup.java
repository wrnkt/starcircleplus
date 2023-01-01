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

    public boolean tableExists(String tableName)
    {
        try
        {
            var sql = String.format("SHOW tables like '%s'", tableName);
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            if (rs.next())
            {
                System.out.println(String.format("%s table exists.", tableName));
                return true;
            }
            else
            {
                System.out.println(String.format("%s table doesn't exist.", tableName));
                return false;
            }

        } catch (SQLException e)
        {
            System.out.println("Failed to execute show table query.");
            System.out.println(e);
        }
        return false;
    }

    public void createTable(String tableName, String columns)
    {
        try
        {
            var sql = String.format("CREATE TABLE %s(%s)", tableName, columns);
            PreparedStatement statement = conn.prepareStatement(sql);
            int result = statement.executeUpdate();
        }
        catch (SQLException e)
        {
            System.out.println(String.format("Failed to create %s table.", tableName));
            System.out.println(e);
        }
    }

    public void createIfNoTable(String tableName, String columns)
    {
        if(!tableExists(tableName))
        {
            createTable(tableName, columns);
        }
    }

    public void createIfNoEntriesTable()
    {
        String columns = "ID INT PRIMARY KEY NOT NULL AUTO_INCREMENT, TYPE BIT(2), CONTENT VARCHAR(255), DATECREATED TIMESTAMP, CERTAINOFDATE BOOLEAN, DATECHECKED TIMESTAMP";
        createIfNoTable("Entries", columns);
    }

    public void createIfNoTagsTable()
    {
        String columns = "ID INT PRIMARY KEY NOT NULL AUTO_INCREMENT, TAG VARCHAR(30)";
        createIfNoTable("Tags", columns);
    }

    public void createIfNoEntryTagTable()
    {
        String columns = "EntryID INT NOT NULL, TagID INT NOT NULL";
        createIfNoTable("Entry_Tag", columns);
    }

    public void setupTables()
    {
        createIfNoEntriesTable();
        createIfNoTagsTable();
        createIfNoEntryTagTable();
    }
}
