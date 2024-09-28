import linearDataStructures.CustomLinkedList;
import linearDataStructures.DynamicArray;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        String str = "Hello world";

        System.out.println(reverseString(str));
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
}