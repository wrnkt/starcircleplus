public class Star extends Entry
{
    public Star(String content, String... tags)
    {
        super(content, tags);
    }

    public String getIdentifier()
    {
        return "*";
    }

    public static void main(String[] args)
    {
        var testStarEntry = new Star("this is a test star entry", "tag1", "tag2", "tag3");
        System.out.println(testStarEntry);
    }

}
