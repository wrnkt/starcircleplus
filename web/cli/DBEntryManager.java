import java.util.Scanner;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;

public class DBEntryManager
{
    private final int starVal = 0;
    private final int plusVal = 1;
    private final int uncheckedCircleVal = 2;
    private final int checkedCircleVal = 3;

    private Connection conn;

    private DBTagManager dbtm;

    public DBEntryManager()
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

        DBSetup dbs = new DBSetup(conn);
        dbs.createAppDBIfNone();
        dbs.ensureInAppDB();

        DBTableSetup dbts = new DBTableSetup(conn);
        dbts.setupTables();

        dbtm = new DBTagManager(conn);
    }

    public void insertEntry(Entry entry) throws Exception
    {
        var sql = "INSERT into Entries (TYPE, CONTENT, DATECREATED, CERTAINOFDATE, DATECHECKED) values (?, ?, ?, ?, ?)";

        try(PreparedStatement statement = conn.prepareStatement(sql))
        {
            switch(entry.getEntryType())
            {
                case Entry.Type.Star:
                    statement.setInt(1, starVal);
                    break;
                case Entry.Type.Circle:
                    statement.setInt(1, uncheckedCircleVal);
                    // add check for checked status
                    break;
                case Entry.Type.Plus:
                    statement.setInt(1, plusVal);
                    break;
                default:
                    throw new Exception("Unhandled Entry type.");

            }

            statement.setString(2, EntryFormatter.formatContent(entry));
            statement.setString(3, EntryFormatter.formatDateCreated(entry));
            if(entry.getCertainOfDate()) {
                statement.setBoolean(4, true);
            } else {
                statement.setBoolean(4, false);
            }
            if(entry.getDateChecked().isPresent())
            {
                statement.setString(5, EntryFormatter.formatDateChecked(entry));
            } else {
                statement.setString(5, null);
            }

            dbtm.updateTagsTable(entry);

            int result = statement.executeUpdate();
        } catch (SQLException e)
        {
            System.out.println("Unable to save entry.");
            System.out.println(e);
        }
    }

    // TODO: May need to change this to be table specific
    public int getLastInsertId()
    {
        try
        {
            var sql = String.format("SELECT LAST_INSERT_ID()");
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            rs.next();
            return rs.getInt(1);
        }
        catch (SQLException e)
        {
            System.out.println(e);
        }
        return 0;
    }

    public static void main(String[] args)
    {
        DBEntryManager dbem = new DBEntryManager();

        Prompter prompter = new Prompter();
        Entry current = prompter.promptForEntry();
        
        try {
            dbem.insertEntry(current);
            System.out.println(dbem.getLastInsertId());
        }
        catch (Exception e)
        {
            System.out.println(e);
            System.out.println("Unable to save entry.");
        }
    }
}
