import java.util.Stack;
import static java.lang.StrictMath.*;
public class RPNToAnswer extends ExprToRPN {
    public double answer(Stack<String> stackEXP)
    {
        double a,b;
        Stack<Double> stackAnswer = new Stack<>();
        String operand = "";
        while (!stackEXP.empty()) {
            if (isNumber(stackEXP.peek())) {
                operand += stackEXP.pop();
                if (operand.equals(VARIABLE))
                {
                    stackAnswer.push(getVariable(operand));
                }else if (operand.equals("pi")) stackAnswer.push(Math.PI);
                else if (operand.equals("e")) stackAnswer.push(Math.E);
                else
                stackAnswer.push(Double.parseDouble(operand));
                operand = "";
            }
            if (!isNumber(stackEXP.peek()) && !isOpenBracket(stackEXP.peek()) && !isCloseBracket(stackEXP.peek())) {
                 a = stackAnswer.pop() ;b= stackAnswer.pop();
                switch (stackEXP.pop())
                {
                    case "+": stackAnswer.push(b+a); break;
                    case "-": stackAnswer.push(b-a); break;
                    case "*": stackAnswer.push(b*a); break;
                    case "/":  try{stackAnswer.push(b/a); break; } catch (Exception ex) {ex.getLocalizedMessage();}
                    case "^": stackAnswer.push(Math.pow(b,a));
                 }
            }

        }
        while (!stack.isEmpty())
        {
            stackAnswer.push(Trigonometrix(stack.pop(),stackAnswer.pop()));
        }
        return stackAnswer.pop();
    }
}
