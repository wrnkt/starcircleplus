public class Circle extends Entry
{
    private boolean isChecked = false;


    public Circle(String content, String... tags)
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
        var testCircleEntry = new Circle("this is a test star entry", "tag1", "tag2", "tag3");
        System.out.println(testCircleEntry);
    }

}
