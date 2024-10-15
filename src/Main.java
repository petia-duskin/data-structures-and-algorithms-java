import treeDataStructures.Trie;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("car");
        trie.insert("care");
        trie.remove("care");

        System.out.println("debug message");
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