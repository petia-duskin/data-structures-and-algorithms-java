package treeDataStructures;

public class BinaryTree {
    private class Node {
        private Node leftChild;
        private Node rightChild;
        private int value;

        public Node(int value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "value=" + value +
                    '}';
        }
    }

    private Node root;
    private int size = 0;

    public void insert(int value) {
        if (isEmpty()) {
            initialize(value);
            return;
        }

        Node newNode = new Node(value);
        Node current = root;
        while (true) {
            if (value < current.value) {
                if (current.leftChild == null) {
                    current.leftChild = newNode;
                    return;
                }
                current = current.leftChild;
            } else if (value > current.value) {
                if (current.rightChild == null) {
                    current.rightChild = newNode;
                    return;
                }
                current = current.rightChild;
            }
        }
    }

    public boolean find(int value) {
        if (isEmpty()) {
            return false;
        }

        Node current = root;
        while (current != null) {
            if (value < current.value) {
                current = current.leftChild;
            } else if (value > current.value) {
                current = current.rightChild;
            } else {
                return true;
            }
        }

        return false;
    }

    private boolean isEmpty() {
        return root == null;
    }

    private void initialize(int value) {
        root = new Node(value);
    }
}
