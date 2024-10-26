package generalAlgorithms;

import java.util.*;


// ShuntingYard first, it transforms expression into reverse polish notation,
// for example 3 + 4 -> 34+
// then with the help of stack calculates the value
// when we face two operands we pop them both and perform calculation with operator
// Reverse polish notation is just transforms an expression into rpn 3 + 4 -> 34+
// it doesn't calculate the value
// There is also prefix form of polish notation 3 + 4 -> +34
public class ShuntingYard {
    private final HashMap<Character, Integer> operatorsPriority = new HashMap<>();

    public ShuntingYard() {
        operatorsPriority.put('+', 1);
        operatorsPriority.put('-', 1);
        operatorsPriority.put('*', 2);
        operatorsPriority.put('/', 2);
        operatorsPriority.put('(', 0);
    }

    public String[] infixToRPN(String expression) {
        Stack<Character> operators = new Stack<>();
        Stack<String> output = new Stack<>();

        char[] tokens = expression.toCharArray();

        for (int i = 0; i < tokens.length; i++) {
            char token = tokens[i];

            // Если текущий символ - число, может быть многозначным числом
            if (Character.isDigit(token)) {
                StringBuilder number = new StringBuilder();
                while (i < tokens.length && Character.isDigit(tokens[i])) {
                    number.append(tokens[i]);
                    i++;
                }
                i--;
                output.push(number.toString());
            } else if (token == '(') {
                operators.push(token);
            } else if (token == ')') {
                while (!operators.isEmpty() && operators.peek() != '(') {
                    output.push(String.valueOf(operators.pop()));
                }
                operators.pop(); // Удаляем '(' из стека
            } else if (isOperator(token)) {
                while (!operators.isEmpty() && operatorsPriority.get(token) <= operatorsPriority.get(operators.peek())) {
                    output.push(String.valueOf(operators.pop()));
                }
                operators.push(token);
            }
        }

        while (!operators.isEmpty()) {
            output.push(String.valueOf(operators.pop()));
        }

        return output.toArray(new String[0]);
    }

    private boolean isOperator(char token) {
        return operatorsPriority.containsKey(token);
    }

    public int evaluateRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();

        for (String token : tokens) {
            if (isOperator(token.charAt(0))) {
                // Извлекаем два числа из стека
                int b = stack.pop();
                int a = stack.pop();
                // Выполняем операцию и помещаем результат обратно в стек
                stack.push(performOperation(token.charAt(0), a, b));
            } else {
                // Если токен - число, то помещаем его в стек
                stack.push(Integer.parseInt(token));
            }
        }

        return stack.pop(); // Результат будет единственным элементом в стеке
    }

    private int performOperation(char operator, int a, int b) {
        return switch (operator) {
            case '+' -> a + b;
            case '-' -> a - b;
            case '*' -> a * b;
            case '/' -> {
                if (b == 0) throw new ArithmeticException("Division by zero");
                yield a / b;
            }
            default -> throw new IllegalArgumentException("Invalid operator: " + operator);
        };
    }

}
