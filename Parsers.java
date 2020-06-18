import java.util.Stack;

public interface Parsers {
     boolean isNumber(String token);
     boolean isFunction(String token);
     boolean isSeparator(String token);
     boolean isOpenBracket(String token);
     boolean isCloseBracket(String token);
     boolean isOperator(String token);
     double Trigonometrix(String type, double a);
     Stack<String> parseString();
     public byte getPriority(String token);
}
