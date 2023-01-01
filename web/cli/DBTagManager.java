import java.sql.*;

import java.util.ArrayList;

public class DBTagManager
{
    private Connection conn;

    /**
     * Class contructor defining a new connection for class methods to use.
     */
    public DBTagManager()
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
    public DBTagManager(Connection passedConn)
    {
        conn = passedConn;
    }

    public void updateTagsTable(Entry e)
    {
        ArrayList<String> tags = EntryFormatter.formatTagList(e);
        for (String tag : tags)
        {
            if(!tagInTable(tag))
            {
                addTag(tag);
            }
        }
    }

    private void addTag(String tag)
    {
        var sql = "INSERT into Tags (TAG) values (?)";
        try(PreparedStatement statement = conn.prepareStatement(sql))
        {
            statement.setString(1, tag);
            int result = statement.executeUpdate();
        }
        catch (SQLException e)
        {
            System.out.println(e);
        }
        
    }

    private boolean tagInTable(String tag)
    {
        try
        {
            var sql = String.format("SELECT * FROM Tags WHERE Tag = '%s'", tag);
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            if (rs.next())
            {
                return true;
            }
            else
            {
                return false;
            }
        } catch (SQLException e)
        {
            System.out.println(e);
        }
        return false;
    }
}
