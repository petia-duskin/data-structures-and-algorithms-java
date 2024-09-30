import java.util.*;

public class Main {
    public static void main(String[] args) {
        String testString = "({How {are <you>?}})";

        System.out.println(isBalanced(testString));
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
        ArrayList<Character> openBrackets = new ArrayList<>(Arrays.asList('(', '{', '['));
        ArrayList<Character> closeBrackets = new ArrayList<>(Arrays.asList(')', '}', ']'));
        Stack<Character> stack = new Stack<>();

        for (char ch : str.toCharArray()) {
            if (openBrackets.contains(ch)) {
                stack.push(ch);
            } else if (closeBrackets.contains(ch)) {
                if (stack.isEmpty()) {
                    return false;
                }
                stack.pop();
            }
        }

        return stack.isEmpty();
    }
}