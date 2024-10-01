import java.util.*;

public class Main {
    public static void main(String[] args) {
        linearDataStructures.Queue queue = new linearDataStructures.Queue();
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(4);

        queue.dequeue();
        queue.dequeue();

        queue.enqueue(5);
        queue.enqueue(6);

        queue.dequeue();


        System.out.println(queue);
        System.out.println(queue.peek());
        System.out.println(queue.size());
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