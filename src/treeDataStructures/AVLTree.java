package treeDataStructures;

public class AVLTree {
    private class Node {
        private Node leftChild;
        private Node rightChild;
        private int value;
        private int height = 0;

        public Node(int value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "value=" + value +
                    ", height=" + height +
                    '}';
        }
    }

    private Node root;

    public void insert(int value) {
        root = insert(root, value);
    }

    private Node insert(Node node, int value) {
        if (node == null) {
            return new Node(value);
        }

        if (value < node.value) {
            node.leftChild = insert(node.leftChild, value);
        } else if (value > node.value) {
            node.rightChild = insert(node.rightChild, value);
        }

        node = balance(node);

        node.height = height(node);

        return node;
    }

    private Node balance(Node node) {
        if (isRightHeavy(node)) {
            if (balanceFactor(node.rightChild) > 0) {
                node.rightChild = rightRotate(node.rightChild);
            }
            return leftRotate(node);
        } else if (isLeftHeavy(node)) {
            if (balanceFactor(node.leftChild) < 0) {
                node.leftChild = leftRotate(node.leftChild);
            }
            return rightRotate(node);
        }

        return node;
    }

    private Node rightRotate(Node node) {
        Node newRoot = node.leftChild;

        node.leftChild = newRoot.rightChild;
        newRoot.rightChild = node;

        setHeight(node);

        return newRoot;
    }

    private Node leftRotate(Node node) {
        Node newRoot = node.rightChild;

        node.rightChild = newRoot.leftChild;
        newRoot.leftChild = node;

        setHeight(node);

        return newRoot;
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


        setHeight(node);
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

    private void setHeight(Node node) {
        node.height = height(node);
    }

    private boolean isLeftHeavy(Node node) {
        return balanceFactor(node) > 1;
    }

    private boolean isRightHeavy(Node node) {
        return balanceFactor(node) < -1;
    }

    // we can use in sorting data, avl sort time complexity: O(n * log n)
    // space complexity: O(n)
    public void inOrderTraversal() {
        inOrderTraversal(root);
    }

    private void inOrderTraversal(Node node) {
        if (node == null) {
            return;
        }

        inOrderTraversal(node.leftChild);
        System.out.print(node.value + " ");
        inOrderTraversal(node.rightChild);
    }

    private boolean isBalanced(Node node) {
        return Math.abs(height(node.leftChild) - height(node.rightChild)) <= 1;
    }

    private int balanceFactor(Node node) {
        return (node == null) ? 0 : height(node.leftChild) - height(node.rightChild);
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


    private boolean isEmpty() {
        return root == null;
    }
}
