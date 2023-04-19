package calculator;

import java.util.Arrays;
import java.util.Stack;

public class PolishNotation {
    private Stack<String> stack;
    private Stack<String> stackNumbers;

    private String expressionToRPN(String expression) {
        String[] expr = expression.split(" ");
        System.out.println(Arrays.toString(expr));
        int priority;

        stack = new Stack<>();

        String rpnExpression = "";
        for (int i = 0; i < expr.length; i++) {
            priority = getPriority(expr[i]);

            if (priority == 0 && expr[i].length() != 0) {
                rpnExpression += expr[i] + " ";
            }
            if (priority == 1 && expr[i].length() != 0){
                stack.push(expr[i]);
            }
            else if (priority > 1) {
                while (!stack.isEmpty()) {
                    if (getPriority(stack.lastElement()) >= priority && expr[i] != "") {
                        rpnExpression += stack.pop() + " ";
                    } else break;
                }
                stack.push(expr[i]);
            }

            if (priority == -1){
                while (getPriority(stack.peek()) != 1){
                    rpnExpression += stack.pop() + " ";
                }
                stack.pop();
            }
        }

        while (!stack.isEmpty()) {
            rpnExpression += stack.pop() + " ";
        }

        System.out.println(rpnExpression);
        return rpnExpression;
    }


    public String rpnExpressionToAnswer(String expression) {
        String[] rpn = expressionToRPN(expression).split(" ");
        System.out.println(Arrays.toString(rpn));

        stackNumbers = new Stack<>();

        String num = "";

        for (int i = 0; i < rpn.length; i++) {
            if (getPriority(rpn[i]) == 0) {
                while (rpn[i] != "" && getPriority(rpn[i]) == 0) {
                    num += rpn[i++];
                    if (i == rpn.length) break;

                    stackNumbers.push(num);
                    num = "";
                }
            }
            if (getPriority(rpn[i]) >= 1) {
                if (rpn[i].equals("^2")) {
                    double aa = Double.parseDouble(stackNumbers.pop());
                    stackNumbers.push(Double.toString(aa * aa));
                } else if (rpn[i].equals("√")) {
                    double aa = Double.parseDouble(stackNumbers.pop());
                    stackNumbers.push(Double.toString(Math.sqrt(aa)));
                } else {
                    double a = Double.parseDouble(stackNumbers.pop());
                    double b = Double.parseDouble(stackNumbers.pop());

                    if (rpn[i].equals("+")) stackNumbers.push(Double.toString(b + a));
                    else if (rpn[i].equals("-")) stackNumbers.push(Double.toString(b - a));
                    else if (rpn[i].equals("*")) stackNumbers.push(Double.toString(b * a));
                    else if (rpn[i].equals("/")) stackNumbers.push(Double.toString(b / a));
                }
            }
            System.out.println(num);
        }

        return stackNumbers.pop();
    }


    public int getPriority(String op) {
        if (op.equals("^2") || op.equals("√")) {
            return 4;
        } else if (op.equals("/") || op.equals("*")) {
            return 3;
        } else if (op.equals("-") || op.equals("+")) {
            return 2;
        } else if (op.equals("(")){
            return 1;
        } else if (op.equals(")")) {
            return -1;
        } else {
            return 0;
        }
    }

}


