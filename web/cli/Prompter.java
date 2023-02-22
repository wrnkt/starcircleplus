import java.util.Arrays;
import java.util.ArrayList;

import java.util.Scanner;

public class Prompter
{
    private ArrayList<Entry> entryList = new ArrayList<Entry>();
    private DBEntryManager dbEntryManager = new DBEntryManager();

    public Entry promptForEntry(Scanner s)
    {
        Entry entry;
        Entry.Type entryType = switch(promptForEntryType(s)) {
            case '*' -> Entry.Type.Star;
            case 'o' -> Entry.Type.Circle;
            case '+' -> Entry.Type.Plus;
            default -> Entry.Type.Plus;
        };
        System.out.println();
        
        String content = promptForEntryContent(s);
        System.out.println();

        ArrayList<String> tagList = promptForEntryTagList(s);
        System.out.println();

        entry = new Entry(content, tagList, entryType);
    
        // NOTE: possibly returning a null reference
        return entry;
    }

    private char promptForEntryType(Scanner s)
    {
        char type = 'N';

        System.out.println("Enter *, o, or +: ");
        System.out.print("> ");

        if(s.hasNextLine())
        {
            type = s.nextLine().charAt(0);
            while(!(type == '*' || type == 'o' || type == '+'))
            {
                System.out.println("Invalid input.");
                System.out.println("Enter a *, o, or +: ");
                System.out.print("> ");
                type = s.nextLine().charAt(0);
            }
        }
        return type;
    }

    private String promptForEntryContent(Scanner s)
    {
        String content = "";

        System.out.println("Enter the body of the entry: ");
        System.out.print("> ");

        if(s.hasNextLine())
            content = s.nextLine();

        return content;
    }

    private ArrayList<String> promptForEntryTagList(Scanner s)
    {
        String[] tags = {};

        System.out.println("Type a list of tags.");
        System.out.print("> ");

        if(s.hasNextLine())
            tags = s.nextLine().split("\\s");

        return new ArrayList<String>(Arrays.asList(tags));
    }

    public void addEntry(Entry e)
    {
        entryList.add(e);
    }

    public void displayTimeline(DisplayFormat entryDisplayFormat)
    {
        System.out.println();
        System.out.println();
        for(Entry e : entryList)
        {
            System.out.println(entryDisplayFormat.format(e));
        }
        System.out.println();
        System.out.println();
    }

    public boolean sendEntryListToDB()
    {
        boolean status = false;
        for (Entry e : entryList) {
            if (e == null) {
                continue;
            }
            try {
                dbEntryManager.insertEntry(e);
            } catch (Exception exception) {
                System.out.println(exception);
                System.out.println("Unable to save entry.");
            }
        }
        return status;
    }

    public boolean writeEntryToDB(Entry e)
    {
        try {
            dbEntryManager.insertEntry(e);
            return true;
        } catch (Exception exception) {
            System.out.println(exception);
            System.out.println("Unable to write Entry");
            return false;
        }
    }
    
    public char promptTimelineEntryOrExit(Scanner s)
    {
        char type = 'N';

        System.out.println("Would you like to display the [t]imeline, enter an [e]ntry, or e[x]it?");
        System.out.print("> ");

        if(s.hasNextLine())
        {
            type = s.nextLine().charAt(0);
            while(!(type == 't' || type == 'e' || type == 'x'))
            {
                System.out.println("Invalid input.");
                System.out.println("Enter a 't', 'e', or 'x': ");
                System.out.print("> ");
                type = s.nextLine().charAt(0);
            }
        }
        return type;
    }

    public DisplayFormat promptTimelineFilter(Scanner s)
    {
        char type = 'N';

        System.out.println("Enter star[*], circle[o], or plus[+] to filter Entries or [a] to view all.");
        System.out.print("> ");

        if(s.hasNextLine())
        {
            type = s.nextLine().charAt(0);
            while(!(type == '*' || type == 'o' || type == '+' || type == 'a'))
            {
                System.out.println("Invalid input.");
                System.out.println("Enter a '*', 'o', '+', or 'a': ");
                System.out.print("> ");
                type = s.nextLine().charAt(0);
            }
        }

        return ENTRY_FORMAT_1;
    }

    public void promptLoop(){
        try (Scanner scanner = new Scanner(System.in)) {

            while(true)
            {
                char menuChoice;
                menuChoice = promptTimelineEntryOrExit(scanner);
                if (menuChoice == 'x') break;
                else if (menuChoice == 't') displayTimeline(promptTimelineFilter(scanner)); // NOTE: implement filter and saving to list instead of db
                else if (menuChoice == 'e') {
                    Entry e;
                    if(writeEntryToDB(e = promptForEntry(scanner))) {
                        // successful write
                        entryList.add(e); // Adding for testing purposes
                    } else {
                        entryList.add(e); // save for later saving if db not available
                    }
                }
            }

        }
    }

    public static void main(String... args)
    {
        Prompter prompter = new Prompter();
        prompter.promptLoop();

        // prompter.addEntry(prompter.promptForEntry());
        // prompter.sendEntryListToDB();
        // prompter.entryList.clear(); // reassigns list refs to null values, does not resize list

        // prompter.displayTimeline(Prompter.ENTRY_FORMAT_1);
        
    }

    // NOTE: could be based on length of tagList.format(e)
    static DisplayFormat divider = (Entry e) -> ("----------------------------");

    static DisplayFormat shortEntry = (Entry e) -> {
        return String.join(" ", e.getIdentifier(), ":", e.getContent());
    };

    static DisplayFormat tagList = (Entry e) -> {
        StringBuilder sb = new StringBuilder();
        sb.append("Tags:");
        for(String tag : e.getTagList())
        {
            sb.append(" #");
            sb.append(tag);
        }

        return sb.toString();
    };

    static DisplayFormat detailEntry = (Entry e) -> {
        return shortEntry.format(e) + "\n" + tagList.format(e);
    };

    static DisplayFormat ENTRY_FORMAT_1 = (Entry e) -> {
        return detailEntry.format(e) + "\n" + divider.format(e);
    };

}

interface DisplayFormat
{
    public String format(Entry e);
}
