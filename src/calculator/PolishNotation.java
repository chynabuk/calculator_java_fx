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
                    double a = Double.parseDouble(stackNumbers.pop());
                    stackNumbers.push(Double.toString(a * a));
                } else if (rpn[i].equals("√")) {
                    double a = Double.parseDouble(stackNumbers.pop());
                    stackNumbers.push(Double.toString(Math.sqrt(a)));
                }
                else if (rpn[i].equals("log")){
                    double a = Double.parseDouble(stackNumbers.pop());
                    stackNumbers.push(Double.toString(Math.log10(a)));
                }
                else if (rpn[i].equals("sin")){
                    double a = Double.parseDouble(stackNumbers.pop());
                    stackNumbers.push(Double.toString(Math.sin(a)));
                }
                else if (rpn[i].equals("cos")){
                    double a = Double.parseDouble(stackNumbers.pop());
                    stackNumbers.push(Double.toString(Math.cos(a)));
                }
                else if (rpn[i].equals("tg")){
                    double a = Double.parseDouble(stackNumbers.pop());
                    stackNumbers.push(Double.toString(Math.tan(a)));
                }
                else if (rpn[i].equals("ctg")){
                    double a = Double.parseDouble(stackNumbers.pop());
                    stackNumbers.push(Double.toString(1 / Math.tan(a)));
                }
                else {
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
        if (
                op.equals("^2") || op.equals("√") || op.equals("log") ||
                op.equals("sin") || op.equals("cos") || op.equals("tg") || op.equals("ctg")
        ) {
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


