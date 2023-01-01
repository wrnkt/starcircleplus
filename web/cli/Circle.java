import java.io.Serializable;
import java.util.Optional;

import java.time.ZonedDateTime;

import java.util.ArrayList;
import java.util.Arrays;

public class Circle extends Entry
{
    private boolean isChecked = false;

    private ZonedDateTime dateChecked;

    public Circle()
    {
        super();
    }

    public Circle(String content, ArrayList<String> tags)
    {
        super(content, tags);
    }

    public boolean checked()
    {
        return isChecked;
    }

    public void unCheck()
    {
        isChecked = false;
    }

    public void check()
    {
        isChecked = true;
        dateChecked = ZonedDateTime.now();
    }

    public Optional<ZonedDateTime> getDateChecked()
    {
        if (checked())
        {
            return Optional.of(dateChecked);
        } 
        return Optional.empty();
    }

    public String getIdentifier()
    {
        return isChecked ? "[x]" : "[]";
    }

    public static Circle testUncheckedEntry()
    {
        return new Circle("this is a test circle entry", new ArrayList<String>(Arrays.asList("test1", "test2", "test3")));
    }

    public static Circle testCheckedEntry()
    {
        var testEntry = new Circle("this is a checked test circle entry", new ArrayList<String>(Arrays.asList("test1", "test2", "test3")));
        testEntry.check();
        return testEntry;
    }
}
