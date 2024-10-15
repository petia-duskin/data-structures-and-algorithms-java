package treeDataStructures;

import java.util.HashMap;

public class Trie {
    public static int ALPHABET_SIZE = 26;

    private class Node {
        private char value;
        private HashMap<Character, Node> children = new HashMap();
        private boolean isEndOfWord = false;

        public Node(char value) {
            this.value = value;
        }

        public boolean hasChild(char ch) {
            return children.containsKey(ch);
        }

        public Node getChild(char ch) {
            return children.get(ch);
        }

        public void addChild(char ch) {
            children.put(ch, new Node(ch));
        }

        public void setIsEndOfWord(boolean isEndOfWord) {
            this.isEndOfWord = isEndOfWord;
        }

        @Override
        public String toString() {
            return "value=" + value;
        }
    }

    private Node root = new Node(' ');

    public void insert(String word) {
        Node current = root;
        for (char ch : word.toCharArray()) {
            if (!current.hasChild(ch)) {
                current.addChild(ch);
            }
            current = current.getChild(ch);
        }
        current.setIsEndOfWord(true);
    }

    public boolean contains(String word) {
        if (word == null) {
            return false;
        }

        Node current = root;

        for (char ch : word.toCharArray()) {
            if (!current.hasChild(ch)) {
                return false;
            }
            current = current.getChild(ch);
        }

        return current.isEndOfWord;
    }
}
