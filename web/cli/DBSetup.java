import java.sql.*;

public class DBSetup
{
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
    
    public static void main(String[] args)
    {
        DBSetup dbs = new DBSetup();
        System.out.println("DBSetup running");
    }
}
