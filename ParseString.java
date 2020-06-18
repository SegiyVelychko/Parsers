import java.util.Collections;
import java.util.Stack;

public class ParseString implements Parsers {
    final String[] FUNCTION = {"abs", "cos", "sin", "atan", "tan", "acos", "asin", "ctan", "actan", "sqrt", "exp", "log"};
    final String OPERATION = "+-*/^";
    final String SEPARATOR = ".";
    final String VARIABLE = "x";
    final String PI = "pi";
    final String E = "e";
    final double rtd = 180/Math.PI;
    Stack<String> stack = new Stack<>();
    Stack<String> stackEXP = new Stack<>();
    Stack<String> stackOperation= new Stack<>();
    Tokenizer tok = new Tokenizer();
    @Override
    public boolean isNumber(String token) {
        try {
            Double.parseDouble(token);
        } catch (Exception ex) {
            if (token.equals(VARIABLE) || token.equals(PI) || token.equals(E)) {
                return true;
            }
            return false;
        }
        return true;
    }

    @Override
    public boolean isFunction(String token) {
        for (String temp: FUNCTION)
        {
            if(temp.equals(token))
                return true;
        }
        return false;
    }

    @Override
    public boolean isSeparator(String token) {
        return token.equals(SEPARATOR);
    }

    @Override
    public boolean isOpenBracket(String token) {
        return token.equals("(");
    }

    @Override
    public boolean isCloseBracket(String token) {
        return token.equals(")");
    }

    @Override
    public boolean isOperator(String token) {
        return OPERATION.contains(token);
    }

    @Override
    public double Trigonometrix(String type, double a) {
        switch (type)
        {
            case "sin": return  Math.sin(a)*rtd;
            case "cos": return Math.cos(a)*rtd;
            case "log": return  Math.log(a)*rtd;
            case "asin": return  Math.asin(a)*rtd;
            case "tan": return  Math.tan(a)*rtd;
            case "abs": return  Math.abs(a)*rtd;
            case "exp": return  Math.exp(a)*rtd;
            case "sqrt": return Math.sqrt(a)*rtd;
            case "acos": return Math.acos(a)*rtd;
            case "atan": return Math.atan(a)*rtd;
        }
        return 0;
    }
    @Override
    public byte getPriority(String token) {
        switch (token)
        {
            case "abs":
            case "sin":
            case "cos":
            case "tan":
            case "sqrt":
            case "ctan":
            case "exp":
            case "log":
            case "asin":
            case "acos":
            case "atan":
            case "actan": return 6;
            case "^":return 5;
            case "*":
            case "/": return 4;
            case "+":
            case "-": return 3;
            case "(":return 2;
            case ")":return 1;
        }
        return 0;
    }

    @Override
    public Stack<String> parseString() {
        tok.split("-55.4+33/2.6*1-4+sin(32)");
        while (tok.hasMoreTokens())
        {
            String token = tok.nextToken();
            int priority = getPriority(token);
            if (isNumber(token) || isFunction(token)) {
                stack.push(token);
                stackEXP.push(stack.pop());
            }
            else if (isOpenBracket(token))
            {
                stackOperation.push(token);
            }           else if (!isCloseBracket(token) && !isOpenBracket(token) && !isNumber(token))
            {
                while (!stackOperation.empty())
                {
                    if (getPriority(stackOperation.peek())>=priority)
                    {
                        stackEXP.push(stackOperation.pop());
                    }
                    else break;
                }
                stackOperation.push(token);
            }
            else if (isCloseBracket(token))
            {
                while (getPriority(stackOperation.peek())!=2)
                {

                    stackEXP.push(stackOperation.pop());
                }
                stackOperation.pop();
            }
        }
        while (!stackOperation.empty()) stackEXP.push(stackOperation.pop());
        Collections.reverse(stackEXP);
        return stackEXP;
    }
}
