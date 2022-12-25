import java.sql.*;

public class DBSetup
{
    private static String DBName = DBConfig.database;
    private Connection conn;

    public DBSetup()
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

    public boolean appDBExists()
    {
        try
        {
            var sql = String.format("SHOW DATABASES like '%s'", DBName);
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

        } catch (SQLException e) {
            System.out.println(e);
        } 
        return false;
    }

    public void createAppDB()
    {
        try
        {
            var sql = String.format("CREATE DATABASE %s", DBName);
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.executeUpdate();
            System.out.println(String.format("Created database %s", DBName));
        } catch (SQLException e) {
            System.out.println(e);
            System.out.println(String.format("Failed to create database %s", DBName));
        } 
    }

    public void createAppDBIfNone()
    {
        if (!appDBExists())
        {
            createAppDB();
        }
    }


    public boolean inAppDB()
    {
        try
        {
            var sql = "SELECT DATABASE()";
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();

            if (rs.next())
            {
                if (DBName.equals(rs.getString("database()")))
                {
                    return true;
                }
                else
                {
                    System.out.println(String.format("Not in DB %s", DBName));
                    return false;
                }
            }
            else
            {
                System.out.println(String.format("Not in DB %s", DBName));
                return false;
            }

        } catch (SQLException e) {
            System.out.println(e);
            return false;
        } 
    }

    public void enterAppDB()
    {
        if(!inAppDB())
        {
            try
            {
                var sql = String.format("USE %s", DBName);
                PreparedStatement statement = conn.prepareStatement(sql);
                int result = statement.executeUpdate();
                System.out.println(String.format("Using db %s", DBName));
            } catch (SQLException e) {
                System.out.println(e);
                System.out.println("Unable to enter DB");
            } 
        }
    }
    
    
    public static void main(String[] args)
    {
        DBSetup dbs = new DBSetup();
        dbs.createAppDBIfNone();
        dbs.enterAppDB();
    }

}
