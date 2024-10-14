package treeDataStructures;

public class Trie {
    private class Node {
        private char value;
        private Node[] children;
        private boolean isEndOfWord = false;

        public Node(char value) {
            this.value = value;
            children = new Node[26];
        }

        public boolean hasChar(int index) {
            return children[index] != null;
        }

        public Node getChildrenNode(int index) {
            return children[index];
        }

        public void setChildrenNode(Node node) {
            children[getCharIndex(node.value)] = node;
        }

        public void setIsEndOfWord(boolean isEndOfWord) {
            this.isEndOfWord = isEndOfWord;
        }


    }

    private Node root = new Node(' ');

    public void insert(String word) {
        Node current = root;
        for (char ch : word.toCharArray()) {
            int charIndex = getCharIndex(ch);
            if (current.hasChar(charIndex)) {
                current = current.getChildrenNode(charIndex);
                continue;
            }
            Node newNode = new Node(ch);
            current.setChildrenNode(newNode);
            current = newNode;
        }
        current.setIsEndOfWord(true);
    }

    private int getCharIndex(char ch) {
        return ch - 'a';
    }

}
