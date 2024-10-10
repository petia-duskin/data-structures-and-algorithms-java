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

    public void traversePreOrder() {
        if (isEmpty()) {
            return;
        }
        traversePreOrder(root);
    }

    private void traversePreOrder(Node current) {
        if (current == null) {
            return;
        }
        System.out.println(current.value);
        traversePreOrder(current.leftChild);
        traversePreOrder(current.rightChild);
    }

    public void traverseInOrder() {
        if (isEmpty()) {
            return;
        }
        traverseInOrder(root);
    }

    private void traverseInOrder(Node current) {
        if (current == null) {
            return;
        }

        traverseInOrder(current.leftChild);
        System.out.println(current.value);
        traverseInOrder(current.rightChild);
    }

    public void traversePostOrder() {
        if (isEmpty()) {
            return;
        }

        traversePostOrder(root);
    }

    private void traversePostOrder(Node current) {
        if (current == null) {
            return;
        }

        traversePostOrder(current.leftChild);
        traversePostOrder(current.rightChild);
        System.out.println(current.value);
    }

    public int height() {
        if (isEmpty()) {
            return 0;
        }

        return height(root);

    }

    private int height(Node node) {
        if (node == null) {
            return -1;
        }
        if (node.leftChild == null && node.rightChild == null) {
            return 0;
        }

        return 1 + Math.max(height(node.leftChild), height(node.rightChild));
    }

    private boolean isEmpty() {
        return root == null;
    }

    private void initialize(int value) {
        root = new Node(value);
    }
}
