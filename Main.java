public class Main {
    public static void main(String[] args) throws NullPointerException {
        ParseString parse = new ParseString();
        try {
            parse.parseString();
        } catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
}
