import java.util.Arrays;
import java.util.ArrayList;

import java.util.Scanner;

public class Prompter
{
    public static String divider = "--------------------";
    public static ArrayList<Entry> entryList = new ArrayList<Entry>();

    public Entry promptForEntry()
    {
        Entry entry;
        try(Scanner scanner = new Scanner(System.in))
        {
            char type = promptForEntryType(scanner);
            System.out.println();
            String content = promptForEntryContent(scanner);
            System.out.println();
            ArrayList<String> tagList = promptForEntryTagList(scanner);
            System.out.println();

            entry = switch(type) {
                case '*' -> new Entry(content, tagList, Entry.Type.Star);
                case 'o' -> new Entry(content, tagList, Entry.Type.Circle);
                case '+' -> new Entry(content, tagList, Entry.Type.Plus);
                default -> new Entry(content, tagList, Entry.Type.Plus);
            };
        }
        
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
            System.out.println(PrompterEntryFormatter.detailEntry(e));
            System.out.println(divider);
        }
    }

    public static void main(String... args)
    {
        Prompter prompter = new Prompter();

        prompter.displayTimeline();

        prompter.addEntry(prompter.promptForEntry());
        prompter.displayTimeline();
        
    }
}


class PrompterEntryFormatter implements Formatter
{
    public static String detailEntry(Entry e)
    {
        return shortEntry(e) + "\n" + formatTagList(e);
    }

    public static String shortEntry(Entry e)
    {
        return String.join(" ", e.getIdentifier(), ":", e.getContent());
    }

    public static String formatTagList(Entry e)
    {
        StringBuilder sb = new StringBuilder();
        sb.append("Tags:");
        for(String tag : e.getTagList())
        {
            sb.append(" #");
            sb.append(tag);
        }

        return sb.toString();
    }
}

