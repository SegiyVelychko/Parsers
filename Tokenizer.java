public class Tokenizer implements TokenSplit{
    private String[] output;
    private int length;
    private int i = 0;
    private String prom = new String();
    public String[] split(String input) {
        char[] c = input.toCharArray();
        for (int i = 0; i < c.length; i++) {
            if ((c[i] >= '0' && c[i] <= '9') || (c[i] == '.') || c[i] == ' '|| (c[i]>='a'&& c[i]<='z')) {
                prom += c[i];
            } else prom += " " + c[i] + " ";
            output = prom.trim().split("\\s+");
            length = output.length+1;
        }
        return output;
    }

    public boolean hasMoreTokens ()
    {
        length--;
        if(length == 0) return false;
        else return true;
    }
    public String nextToken()
    {
        String token = output[i];
        i++;
        return token;
    }
}

