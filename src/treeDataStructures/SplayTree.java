package treeDataStructures;

public class SplayTree {
    private class Node {
        private Node left;
        private Node right;
        private Node parent;
        private int value;

        public Node(int value) {
            this.value = value;
        }

        private Node getGrandParent() {
            return parent != null ? parent.parent : null;
        }

        private boolean isLeftChild() {
            return parent != null && parent.left == this;
        }

        private boolean isRightChild() {
            return parent.right != null && parent.right == this;
        }

        @Override
        public String toString() {
            return "value: " + value;
        }
    }

    private Node root;

    public void insert(int value) {
        Node newNode = new Node(value);
        root = insert(root, newNode);
        splay(newNode);
    }

    private Node insert(Node currentNode, Node newNode) {
        if (currentNode == null) {
            return newNode;
        }

        if (currentNode.value > newNode.value) {
            currentNode.left = insert(currentNode.left, newNode);
            currentNode.left.parent = currentNode;
        } else {
            currentNode.right = insert(currentNode.right, newNode);
            currentNode.right.parent = currentNode;
        }

        return currentNode;
    }

    private void splay(Node currentNode) {

        while (currentNode != root) {
            Node parent = currentNode.parent;
            Node grandParent = currentNode.getGrandParent();

            if (grandParent == null) {
                if (currentNode.isLeftChild()) {
                    rightRotate(parent);
                } else {
                    leftRotate(parent);
                }
            } else if (currentNode.isLeftChild() && parent.isLeftChild()) {
                rightRotate(grandParent);
                rightRotate(parent);
            } else if (currentNode.isRightChild() && parent.isRightChild()) {
                leftRotate(grandParent);
                leftRotate(parent);
            } else if (currentNode.isLeftChild() && parent.isRightChild()) {
                rightRotate(parent);
                leftRotate(parent);
            } else {
                leftRotate(parent);
                rightRotate(grandParent);
            }
        }
    }

    private void leftRotate(Node currentNode) {
        Node newRoot = currentNode.right;
        currentNode.right = newRoot.left;

        if (currentNode.right != null) {
            currentNode.right.parent = currentNode;
        }

        updateChildrenOfParent(currentNode, newRoot);
        newRoot.left = currentNode;
        newRoot.parent = currentNode.parent;
        currentNode.parent = newRoot;
    }

    public void rightRotate(Node currentNode) {
        Node newRoot = currentNode.left;
        currentNode.left = newRoot.right;

        if (currentNode.left != null) {
            currentNode.left.parent = currentNode;
        }
        updateChildrenOfParent(currentNode, newRoot);
        newRoot.parent = currentNode.parent;
        newRoot.right = currentNode;
        currentNode.parent = newRoot;
    }

    private void updateChildrenOfParent(Node node, Node parent) {
        if (node.parent == null) {
            root = parent;
        } else if (node.isLeftChild()) {
            node.parent.left = parent;
        } else {
            node.parent.right = parent;
        }
    }


}
