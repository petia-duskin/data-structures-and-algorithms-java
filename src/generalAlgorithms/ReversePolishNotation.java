package generalAlgorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class ReversePolishNotation {
    public Double reversePolishNotation(String expression) {
        if (expression == null || expression.isEmpty()) {
            throw new IllegalArgumentException();
        }

        String[] expressionArray = expression.split("(?<=\\d)(?=\\D)|(?<=\\D)(?=\\d)|(?<=\\()|(?=\\()|(?<=\\))|(?=\\))");
        expressionArray = Arrays.stream(expressionArray)
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .toArray(String[]::new);

        StringBuilder rpn = new StringBuilder();
        Stack<String> operators = new Stack<>();

        System.out.println(Arrays.toString(expressionArray));


        for (String token : expressionArray) {
            if (Character.isDigit(token.charAt(0))) {
                rpn.append(token);
            } else if (isOperator(token)) {
                if (operators.empty() || getTokenPriority(operators.peek()) < getTokenPriority(token)) {
                    operators.push(token);
                } else if (getTokenPriority(operators.peek()) > getTokenPriority(token) || getTokenPriority(operators.peek()) == getTokenPriority(token)) {
                    String prevToken = operators.peek();
                    while (!operators.isEmpty() && (getTokenPriority(prevToken) > getTokenPriority(token)
                            || getTokenPriority(prevToken) == getTokenPriority(token))) {
                        rpn.append(operators.pop());
                        if (operators.isEmpty()) {
                            break;
                        }
                        prevToken = operators.peek();
                    }
                    operators.push(token);
                }

            }
        }

        while (!operators.isEmpty()) {
            rpn.append(operators.pop());
        }

        System.out.println(rpn);

        return 0.0;
    }

    private boolean isOperator(String op) {
        List<String> operators = Arrays.asList("+", "*", "-", "/");
        return operators.contains(op);
    }

    private int getTokenPriority(String token) {
        return switch (token) {
            case "+", "-" -> 0;
            case "*", "/" -> 1;
            case "(", ")" -> 2;
            default -> -1;
        };
    }

    private double calculate(String token, double x1, double x2) {
        return switch (token) {
            case "+" -> x1 + x2;
            case "*" -> x1 * x2;
            case "-" -> x1 - x2;
            case "/" -> x2 / x1;
            default -> -1;
        };
    }

}
