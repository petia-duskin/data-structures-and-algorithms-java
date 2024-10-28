package treeDataStructures;

import java.util.ArrayList;

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

    public Node root;

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
            } else {
                return;
            }
        }
    }


    public void remove(int value) {
        remove(root, value);
    }

    private Node remove(Node node, int value) {
        if (node == null) return node;

        if (value < node.value)
            node.leftChild = remove(node.leftChild, value);
        else if (value > node.value)
            node.rightChild = remove(node.rightChild, value);

        else {
            if (node.leftChild == null)
                return node.rightChild;
            else if (node.rightChild == null)
                return node.leftChild;

            node.value = findPredecessor(node.leftChild);

            node.leftChild = remove(node.leftChild, node.value);
        }

        return node;
    }


    private int findPredecessor(Node node) {
        int maxv = node.value;
        while (node.rightChild != null) {
            maxv = node.rightChild.value;
            node = node.rightChild;
        }
        return maxv;
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
        if (isLeaf(node)) {
            return 0;
        }

        return 1 + Math.max(height(node.leftChild), height(node.rightChild));
    }

    private boolean isLeaf(Node node) {
        return node.leftChild == null && node.rightChild == null;
    }

    public int minValue() {
        if (isEmpty()) {
            throw new IllegalStateException();
        }

        return minValue(root).value;
    }

    private Node minValue(Node node) {
        if (node.leftChild == null) {
            return node;
        }

        return minValue(node.leftChild);
    }

    public boolean equals(Node root2) {
        if (isEmpty() && root2 == null) {
            return true;
        }

        return equals(root, root2);
    }

    private boolean equals(Node node1, Node node2) {
        if (node1 == null && node2 == null) {
            return true;
        }

        if (node1 != null && node2 != null) {
            return node1.value == node2.value
                    && equals(node1.leftChild, node2.leftChild)
                    && equals(node1.rightChild, node2.rightChild);
        }

        return false;
    }

    public boolean isBinarySearchTree() {
        if (isEmpty()) {
            return true;
        }

        return isBinarySearchTree(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private boolean isBinarySearchTree(Node node, int minLimit, int maxLimit) {
        if (node == null) {
            return true;
        }

        if (node.value > minLimit && node.value < maxLimit) {
            return isBinarySearchTree(node.leftChild, minLimit, node.value)
                    && isBinarySearchTree(node.rightChild, node.value, maxLimit);
        }

        return false;
    }

    // method for checking isBinarySearchTree method works well
    public void swapRoot() {
        Node rootLeft = root.leftChild;
        root.leftChild = root.rightChild;
        root.rightChild = rootLeft;
    }

    public ArrayList<Node> getNodesAtDistance(int distance) {
        ArrayList<Node> nodes = new ArrayList<>();
        if (isEmpty()) {
            return nodes;
        }
        if (distance > height()) {
            throw new IllegalArgumentException();
        }

        getNodesAtDistance(root, nodes, distance);

        return nodes;
    }

    private void getNodesAtDistance(Node node, ArrayList<Node> nodes, int distance) {
        if (node == null) {
            return;
        }

        if (distance == 0) {
            nodes.add(node);
            return;
        }

        getNodesAtDistance(node.leftChild, nodes, distance - 1);
        getNodesAtDistance(node.rightChild, nodes, distance - 1);
    }

    public void leverOrderTraversal() {
        if (isEmpty()) return;
        for (int i = 0; i <= height(); i++) {
            System.out.println(getNodesAtDistance(i));
        }
    }

    private boolean isEmpty() {
        return root == null;
    }

    private void initialize(int value) {
        root = new Node(value);
    }
}
