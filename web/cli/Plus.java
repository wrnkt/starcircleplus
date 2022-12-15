public class Plus extends Entry
{

    public Plus(String content, String... tags)
    {
        super(content, tags);
    }

    public String getIdentifier()
    {
        return "+";
    }

    public static void main(String[] args)
    {
        var testPlusEntry = new Plus("this is a test star entry", "tag1", "tag2", "tag3");
        System.out.println(testPlusEntry);
    }

}
