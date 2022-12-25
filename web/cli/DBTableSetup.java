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

    public static void main(String[] args)
    {
        DBSetup dbs = new DBSetup();
        dbs.createAppDBIfNone();
        dbs.enterAppDB();
    }
}
