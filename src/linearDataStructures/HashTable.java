package linearDataStructures;

import java.util.ArrayDeque;
import java.util.HashMap;

public class HashTable {
    public static void firstNonRepeatedCharacter(String str) {
        HashMap<Character, Integer> map = new HashMap<>();
        java.util.Queue<Character> queue = new ArrayDeque<>();

        for (char ch : str.toCharArray()) {
            map.put(ch, map.getOrDefault(ch, 0) + 1);
            queue.add(ch);
        }

        while (!queue.isEmpty()) {
            char ch = queue.remove();
            if (map.get(ch) == 1) {
                System.out.println(ch);
                return;
            }
        }

        System.out.println("No such character");
    }
}
