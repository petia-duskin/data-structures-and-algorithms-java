import java.util.*;

public class Main {
    public static void main(String[] args) {
        linearDataStructures.Stack stack = new linearDataStructures.Stack();
        stack.push(10);
        stack.push(20);
        stack.push(30);
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        stack.push(55);
        System.out.println(stack.peek());
        System.out.println(stack.isEmpty());
        stack.pop();
        stack.pop();
        System.out.println(stack.isEmpty());

        System.out.println(stack);
    }

    public static String reverseString(String str) {
        if (str == null || str.isEmpty()) {
            throw new IllegalArgumentException();
        }
        Stack<Character> chars = new Stack<>();
        StringBuilder builder = new StringBuilder();

        for (char ch : str.toCharArray()) {
            chars.push(ch);
        }

        while (!chars.empty()) {
            builder.append(chars.pop());
        }

        return builder.toString();
    }

    public static boolean isBalanced(String str) {
        List<Character> openBrackets = Arrays.asList('(', '{', '[', '<');
        List<Character> closeBrackets = Arrays.asList(')', '}', ']', '>');
        Stack<Character> stack = new Stack<>();

        for (char ch : str.toCharArray()) {
            if (openBrackets.contains(ch)) {
                stack.push(ch);
            } else if (closeBrackets.contains(ch)) {
                if (stack.isEmpty()) {
                    return false;
                }

                char topBracket = stack.pop();
                if (openBrackets.indexOf(topBracket) != closeBrackets.indexOf(ch)) {
                    return false;
                }
            }
        }

        return stack.isEmpty();
    }
}