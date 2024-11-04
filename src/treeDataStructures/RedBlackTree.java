package treeDataStructures;

// Red-black tree properties
// 1) A node is either red or black
// 2) The root and leaves (Null) are black
// 3) If a node is red, then its children are always black
// 4) All paths from a node to its Null descendants
// contain the same number of black nodes
// While inserting a new node, the new node is always inserted as a RED node

public class RedBlackTree {
    private class Node {
        // 0 - is black, 1 - is red
        private byte color = 1;
        private Node left;
        private Node right;
        private Node parent;
        private int value;


        public Node(int value) {
            this.value = value;
        }

        public boolean isBlack() {
            return color == 0;
        }

        public boolean isRed() {
            return color == 1;
        }

        public boolean isLeftChild() {
            return this == parent.left;
        }

        public boolean isRightChild() {
            return this == parent.right;
        }

        public void flipColor() {
            color = (byte) (color == 0 ? 1 : 0);
        }

        @Override
        public String toString() {
            return "value: " + value + " color: " + (color == 0 ? "black" : "red");
        }
    }

    private Node root;


    public void insert(int value) {
        Node node = new Node(value);
        root = insert(root, node);
        rotateAndRecolor(node);
    }

    private void rotateAndRecolor(Node node) {
        Node parent = node.parent;
        if (node != root && parent.isRed()) {
            Node grandParent = node.parent.parent;
            Node uncle = parent.isLeftChild() ? grandParent.right : grandParent.left;

            if (uncle != null && uncle.isBlack()) {
                recolor(parent, uncle, grandParent);
            } else if (parent.isLeftChild()) {
                handleLeftSituations(node, parent, grandParent);
            } else if (parent.isRightChild()) {
                handleRightSituations(node, parent, grandParent);
            }
        }

        root.color = 0;
    }

    private void handleRightSituations(Node node, Node parent, Node grandParent) {

        if (node.isLeftChild()) {
            rightRotate(parent);
        }

        parent.flipColor();
        grandParent.flipColor();
        leftRotate(grandParent);
        rotateAndRecolor(node.isLeftChild() ? grandParent : parent);
    }

    private void handleLeftSituations(Node node, Node parent, Node grandParent) {
        if (node.isRightChild()) {
            leftRotate(parent);
        }

        parent.flipColor();
        grandParent.flipColor();
        rightRotate(grandParent);

        rotateAndRecolor(node.isLeftChild() ? parent : grandParent);
    }

    private void recolor(Node parent, Node uncle, Node grandParent) {
        uncle.flipColor();
        parent.flipColor();
        grandParent.flipColor();
        rotateAndRecolor(grandParent);
    }

    private Node insert(Node root, Node node) {
        if (root == null) {
            return node;
        }

        if (node.value < root.value) {
            root.left = insert(root.left, node);
            root.left.parent = root;
        } else if (node.value > root.value) {
            root.right = insert(root.right, node);
            root.right.parent = root;
        }

        return root;
    }

    private void rightRotate(Node node) {
        Node leftNode = node.left;
        node.left = leftNode.right;

        if (node.left != null) {
            node.left.parent = node;
        }

        leftNode.right = node;
        leftNode.parent = node.parent;
        updateChildrenOfParentNode(node, leftNode);
        node.parent = leftNode;
    }

    private void updateChildrenOfParentNode(Node node, Node tempNode) {
        if (node.parent == null) {
            root = tempNode;
        } else if (node.isLeftChild()) {
            node.parent.left = tempNode;
        } else {
            node.parent.right = tempNode;
        }
    }

    private void leftRotate(Node node) {
        Node rightNode = node.right;
        node.right = rightNode.left;

        if (node.right != null) {
            node.right.parent = node;
        }

        rightNode.left = node;
        rightNode.parent = node.parent;
        updateChildrenOfParentNode(node, rightNode);
        node.parent = rightNode;
    }


    public boolean isEmpty() {
        return root == null;
    }
}
