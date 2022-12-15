import java.util.Arrays;
import java.util.ArrayList;

import java.util.Scanner;

public class TimelineManager
{
    public static String divider = "--------------------";
    public static ArrayList<Entry> entryList = new ArrayList<Entry>();

    public Entry getEntry()
    {
        Entry entry;
        try(Scanner scanner = new Scanner(System.in))
        {
            char type = getEntryType(scanner);
            System.out.println();
            String content = getEntryContent(scanner);
            System.out.println();
            var tagsList = getEntryTagsList(scanner);
            System.out.println();

            String[] tags = new String[tagsList.size()];
            tags = tagsList.toArray(tags);
            
            entry = switch(type) {
                case '*' -> new Star(content, tags);
                case 'o' -> new Circle(content, tags);
                case '+' -> new Plus(content, tags);
                default -> new Plus(content, tags);
            };
        }
        
        return entry;
    }

    private char getEntryType(Scanner s)
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

    private String getEntryContent(Scanner s)
    {
        String content = "";

        System.out.println("Enter the body of the entry: ");
        System.out.print("> ");

        if(s.hasNextLine())
            content = s.nextLine();

        return content;
    }

    private ArrayList<String> getEntryTagsList(Scanner s)
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
            System.out.println(e.shortEntry());
            System.out.println(divider);
        }
    }

    public static void main(String... args)
    {
        TimelineManager timelineManager = new TimelineManager();

        timelineManager.displayTimeline();

        timelineManager.addEntry(timelineManager.getEntry());
        timelineManager.displayTimeline();
        
    }
}
