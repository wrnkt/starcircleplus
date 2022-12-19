import java.io.Serializable;

import java.util.Arrays;
import java.util.ArrayList;

public class Star extends Entry
{
    public Star()
    {
        super();
    }

    public Star(String content, ArrayList<String> tags)
    {
        super(content, tags);
    }

    public String getIdentifier()
    {
        return "*";
    }

    public static Star testEntry()
    {
        return new Star("Test saving to MySQL database",
                new ArrayList<String>(Arrays.asList("programming", "productivity")));
    }

    public static void main(String[] args)
    {
        var testStarEntry = new Star("this is a test star entry", new ArrayList<String>(Arrays.asList("test1", "test2", "test3")));
        System.out.println(testStarEntry);
    }

}
