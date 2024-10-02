import linearDataStructures.QueueWithStack;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        QueueWithStack q = new QueueWithStack();
        q.enqueue(10);
        q.enqueue(20);
        q.enqueue(30);

        System.out.println(q.dequeue());
        System.out.println(q.dequeue());

        q.enqueue(40);
        q.enqueue(50);

        System.out.println(q.dequeue());
        System.out.println(q.dequeue());
        System.out.println(q.peek());
        System.out.println(q.dequeue());
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

    public static void reverse(Queue<Integer> queue) {
        Stack<Integer> stack = new Stack<>();
        while (!queue.isEmpty()) {
            stack.push(queue.remove());
        }
        while (!stack.isEmpty()) {
            queue.add(stack.pop());
        }

        System.out.println(queue);
    }
}