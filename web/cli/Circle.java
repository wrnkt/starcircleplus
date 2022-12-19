import java.io.Serializable;

import java.util.ArrayList;
import java.util.Arrays;

public class Circle extends Entry
{
    private boolean isChecked = false;

    public Circle()
    {
        super();
    }

    public Circle(String content, ArrayList<String> tags)
    {
        super(content, tags);
    }

    public void unCheck()
    {
        isChecked = false;
    }

    public void check()
    {
        isChecked = true;
    }

    public String getIdentifier()
    {
        return isChecked ? "[x]" : "[]";
    }

    public static void main(String[] args)
    {
        var testCircleEntry = new Circle("this is a test circle entry", new ArrayList<String>(Arrays.asList("test1", "test2", "test3")));
        System.out.println(testCircleEntry);
    }

}
