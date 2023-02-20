import java.util.Arrays;
import java.util.ArrayList;

import java.util.Scanner;

public class Prompter
{
    private ArrayList<Entry> entryList = new ArrayList<Entry>();
    private DBEntryManager dbEntryManager = new DBEntryManager();

    public Entry promptForEntry()
    {
        Entry entry;
        try(Scanner scanner = new Scanner(System.in))
        {
            Entry.Type entryType = switch(promptForEntryType(scanner)) {
                case '*' -> Entry.Type.Star;
                case 'o' -> Entry.Type.Circle;
                case '+' -> Entry.Type.Plus;
                default -> Entry.Type.Plus;
            };
            System.out.println();
            
            String content = promptForEntryContent(scanner);
            System.out.println();

            ArrayList<String> tagList = promptForEntryTagList(scanner);
            System.out.println();

            entry = new Entry(content, tagList, entryType);
        }
        
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

    public void displayTimeline()
    {
        for(Entry e : entryList)
        {
            System.out.println(detailEntry.format(e));
            System.out.println(divider.format(e));
        }
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

    public static void main(String... args)
    {
        Prompter prompter = new Prompter();

        prompter.addEntry(prompter.promptForEntry());
        prompter.sendEntryListToDB();
        prompter.entryList.clear(); // reassigns list refs to null values, does not resize list

        prompter.displayTimeline();
        
    }

    // NOTE: could be based on length of tagList.format(e)
    DisplayFormat divider = (Entry e) -> ("----------------------------");

    DisplayFormat shortEntry = (Entry e) -> {
        return String.join(" ", e.getIdentifier(), ":", e.getContent());
    };

    DisplayFormat tagList = (Entry e) -> {
        StringBuilder sb = new StringBuilder();
        sb.append("Tags:");
        for(String tag : e.getTagList())
        {
            sb.append(" #");
            sb.append(tag);
        }

        return sb.toString();
    };

    DisplayFormat detailEntry = (Entry e) -> {
        return shortEntry.format(e) + "\n" + tagList.format(e);
    };

}

interface DisplayFormat
{
    public String format(Entry e);
}
