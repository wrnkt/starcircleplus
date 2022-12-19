import java.io.Serializable;

import java.util.ArrayList;
import java.util.Arrays;

public class Plus extends Entry
{
    public Plus()
    {
        super();
    }

    public Plus(String content, ArrayList<String> tags)
    {
        super(content, tags);
    }

    public String getIdentifier()
    {
        return "+";
    }

    public static void main(String[] args)
    {
        var testPlusEntry = new Plus("this is a test plus entry", new ArrayList<String>(Arrays.asList("test1", "test2", "test3")));
        System.out.println(testPlusEntry);
    }

}
