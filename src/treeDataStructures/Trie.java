package treeDataStructures;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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

        public Node[] getChildren() {
            return children.values().toArray(new Node[0]);
        }

        public boolean hasChildren() {
            return !children.isEmpty();
        }

        public void removeChild(char ch) {
            children.remove(ch);
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

    public void preOrderTraverse() {
        preOrderTraverse(root);
    }

    private void preOrderTraverse(Node node) {
        System.out.println(node.value);

        for (Node child : node.getChildren()) {
            preOrderTraverse(child);
        }
    }

    public void postOrderTraverse() {
        postOrderTraverse(root);
    }

    private void postOrderTraverse(Node node) {
        for (Node child : node.getChildren()) {
            postOrderTraverse(child);
        }

        System.out.println(node.value);
    }

    public void remove(String word) {
        if (!contains(word)) {
            throw new IllegalArgumentException();
        }

        remove(root, word, 0);
    }

    private void remove(Node node, String word, int index) {
        if (index == word.length()) {
            node.setIsEndOfWord(false);
            return;
        }

        char ch = word.charAt(index);

        remove(node.getChild(ch), word, index + 1);

        if (!node.getChild(ch).hasChildren() && !node.getChild(ch).isEndOfWord) {
            node.removeChild(ch);
        }
    }

    public List<String> autoComplete(String prefix) {
        if (prefix == null) {
            throw new IllegalArgumentException();
        }

        ArrayList<String> words = new ArrayList<>();
        Node lastNode = findLastNodeOf(prefix);

        if (lastNode == null) {
            return words;
        }

        autoComplete(lastNode, prefix, words);


        return words;
    }

    private Node findLastNodeOf(String prefix) {
        Node current = root;
        for (char ch : prefix.toCharArray()) {
            if (!current.hasChild(ch)) {
                return null;
            }
            current = current.getChild(ch);
        }

        return current;
    }

    private void autoComplete(Node node, String prefix, ArrayList<String> words) {
        if (node.isEndOfWord) {
            words.add(prefix);
        }

        if (!node.hasChildren() && node.isEndOfWord) {
            return;
        }

        for (Node child : node.getChildren()) {
            autoComplete(child, prefix + child.value, words);
        }
    }
}
