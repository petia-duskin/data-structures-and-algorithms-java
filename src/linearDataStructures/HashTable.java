package linearDataStructures;

import java.util.*;

public class HashTable {
    private class Entry {
        private int key;
        private String value;

        public Entry(int key, String value) {
            this.key = key;
            this.value = value;
        }
    }

    private LinkedList<Entry>[] map = new LinkedList[10];

    public void put(int key, String value) {
        LinkedList<Entry> bucket = getOrCreateBucket(key);

        for (Entry entry : bucket) {
            if (entry.key == key) {
                entry.value = value;
                return;
            }
        }

        bucket.addFirst(new Entry(key, value));
    }

    public String get(int key) {
        if (getBucket(key) == null) {
            throw new IllegalStateException();
        }

        Entry entry = getEntry(key);
        return entry != null ? entry.value : null;
    }

    public void remove(int key) {
        LinkedList<Entry> bucket = getBucket(key);

        if (bucket == null) {
            throw new IllegalStateException();
        }


        for (Entry entry : bucket) {
            if (entry.key == key) {
                bucket.remove(entry);
                return;
            }
        }
    }


    private Entry getEntry(int key) {
        LinkedList<Entry> bucket = getBucket(key);

        for (Entry entry : bucket) {
            if (entry.key == key) {
                return entry;
            }
        }
        return null;
    }

    private LinkedList<Entry> getBucket(int key) {
        return map[getIndex(key)];
    }

    private LinkedList<Entry> getOrCreateBucket(int key) {
        LinkedList<Entry> bucket = getBucket(key);
        if (bucket == null) {
            map[getIndex(key)] = new LinkedList<Entry>();
        }
        return map[getIndex(key)];
    }

    private int getIndex(int key) {
        return hash(key);
    }

    private int hash(int key) {
        return key % map.length;
    }


    public static void firstNonRepeatedCharacter(String str) {
        HashMap<Character, Integer> map = new HashMap<>();

        for (char ch : str.toCharArray()) {
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }

        for (char ch : str.toCharArray()) {
            if (map.get(ch) == 1) {
                System.out.println(ch);
                return;
            }
        }

        System.out.println("No such character");
    }

    public static void firstRepeatedCharacter(String str) {
        Set<Character> set = new HashSet<>();

        for (char ch : str.toCharArray()) {
            if (set.contains(ch)) {
                System.out.println(ch);
                return;
            }
            set.add(ch);
        }

        System.out.println("No such character");
    }
}
