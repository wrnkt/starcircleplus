import java.sql.*;

public class DBSetup
{
    private static String DBName = "Test";
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
            System.out.println("Unable to connect to database.");
            System.out.println(e);
        }
    }

    public void createAppDBIfNone()
    {
        try
        {
            var sql = String.format("SHOW DATABASES like '%s'", DBName);
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();

            if (rs.next())
            {
                System.out.println(String.format("%s database exists.", DBName));
            }
            else
            {
                System.out.println(String.format("%s database doesn't exist.", DBName));
                // createAppDB();
            }

        } catch (SQLException e) {
        } 
    }

    public void createAppDB()
    {
        try
        {
            var sql = String.format("CREATE DATABASE %s", DBName);
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(String.format("Failed to create %s DB", DBName));
        } 
            System.out.println(String.format("Created database %s", DBName));
            
    }

    public void enterAppDB()
    {
        try
        {
            var sql = "SELECT DATABASE()";
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();

            if (rs.next())
            {
                System.out.println(rs.getString("database()"));
            }
            else
            {
                System.out.println(String.format("Not in DB %s", DBName));
            }

        } catch (SQLException e) {
        } 
    }
    
    
    public static void main(String[] args)
    {
        DBSetup dbs = new DBSetup();
        dbs.enterAppDB();
        System.out.println("DBSetup running");
    }

}
