package treeDataStructures;

// Red-black tree properties
// 1) A node is either red or black
// 2) The root and leaves (Null) are black
// 3) If a node is red, then its children are always black
// 4) All paths from a node to its Null descendants
// contain the same number of black nodes
// While inserting a new node, the new node is always inserted as a RED node

public class RedBlackTree {
    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private class Node {
        int data;
        Node left, right, parent;
        boolean color;

        Node(int data) {
            this.data = data;
            left = right = parent = null;
            color = RED;
        }
    }

    private Node root;

    public void insert(int data) {
        Node newNode = new Node(data);
        root = insertRec(root, newNode);
        fixInsert(newNode);
    }

    private Node insertRec(Node root, Node node) {
        if (root == null) return node;

        if (node.data < root.data) {
            root.left = insertRec(root.left, node);
            root.left.parent = root;
        } else if (node.data > root.data) {
            root.right = insertRec(root.right, node);
            root.right.parent = root;
        }
        return root;
    }

    private void fixInsert(Node node) {
        Node parent = null;
        Node grandParent = null;

        while (node != root && node.color != BLACK && node.parent.color == RED) {
            parent = node.parent;
            grandParent = parent.parent;

            if (parent == grandParent.left) {
                Node uncle = grandParent.right;

                if (uncle != null && uncle.color == RED) {
                    grandParent.color = RED;
                    parent.color = BLACK;
                    uncle.color = BLACK;
                    node = grandParent;
                } else {
                    if (node == parent.right) {
                        rotateLeft(parent);
                        node = parent;
                        parent = node.parent;
                    }
                    rotateRight(grandParent);
                    boolean temp = parent.color;
                    parent.color = grandParent.color;
                    grandParent.color = temp;
                    node = parent;
                }
            } else {
                Node uncle = grandParent.left;

                if (uncle != null && uncle.color == RED) {
                    grandParent.color = RED;
                    parent.color = BLACK;
                    uncle.color = BLACK;
                    node = grandParent;
                } else {
                    if (node == parent.left) {
                        rotateRight(parent);
                        node = parent;
                        parent = node.parent;
                    }
                    rotateLeft(grandParent);
                    boolean temp = parent.color;
                    parent.color = grandParent.color;
                    grandParent.color = temp;
                    node = parent;
                }
            }
        }
        root.color = BLACK;
    }

    private void rotateLeft(Node node) {
        Node rightNode = node.right;
        node.right = rightNode.left;

        if (node.right != null) node.right.parent = node;

        rightNode.parent = node.parent;

        if (node.parent == null)
            root = rightNode;
        else if (node == node.parent.left)
            node.parent.left = rightNode;
        else
            node.parent.right = rightNode;

        rightNode.left = node;
        node.parent = rightNode;
    }

    private void rotateRight(Node node) {
        Node leftNode = node.left;
        node.left = leftNode.right;

        if (node.left != null) node.left.parent = node;

        leftNode.parent = node.parent;

        if (node.parent == null)
            root = leftNode;
        else if (node == node.parent.left)
            node.parent.left = leftNode;
        else
            node.parent.right = leftNode;

        leftNode.right = node;
        node.parent = leftNode;
    }

    public boolean search(int data) {
        return searchRec(root, data) != null;
    }

    private Node searchRec(Node root, int data) {
        if (root == null || root.data == data) return root;

        if (data < root.data) return searchRec(root.left, data);
        return searchRec(root.right, data);
    }

    public void remove(int data) {
        Node node = searchRec(root, data);
        if (node != null) deleteNode(node);
    }

    private void deleteNode(Node node) {
        Node replacement = node;
        Node child;
        boolean originalColor = replacement.color;

        if (node.left == null) {
            child = node.right;
            transplant(node, node.right);
        } else if (node.right == null) {
            child = node.left;
            transplant(node, node.left);
        } else {
            replacement = minimum(node.right);
            originalColor = replacement.color;
            child = replacement.right;
            if (replacement.parent == node) {
                if (child != null) child.parent = replacement;
            } else {
                transplant(replacement, replacement.right);
                replacement.right = node.right;
                replacement.right.parent = replacement;
            }
            transplant(node, replacement);
            replacement.left = node.left;
            replacement.left.parent = replacement;
            replacement.color = node.color;
        }

        if (originalColor == BLACK) fixDelete(child);
    }

    private void transplant(Node u, Node v) {
        if (u.parent == null)
            root = v;
        else if (u == u.parent.left)
            u.parent.left = v;
        else
            u.parent.right = v;
        if (v != null) v.parent = u.parent;
    }

    private Node minimum(Node node) {
        while (node.left != null) node = node.left;
        return node;
    }

    private void fixDelete(Node node) {
        while (node != root && (node == null || node.color == BLACK)) {
            if (node == node.parent.left) {
                Node sibling = node.parent.right;

                if (sibling.color == RED) {
                    sibling.color = BLACK;
                    node.parent.color = RED;
                    rotateLeft(node.parent);
                    sibling = node.parent.right;
                }

                if ((sibling.left == null || sibling.left.color == BLACK) &&
                        (sibling.right == null || sibling.right.color == BLACK)) {
                    sibling.color = RED;
                    node = node.parent;
                } else {
                    if (sibling.right == null || sibling.right.color == BLACK) {
                        if (sibling.left != null) sibling.left.color = BLACK;
                        sibling.color = RED;
                        rotateRight(sibling);
                        sibling = node.parent.right;
                    }
                    sibling.color = node.parent.color;
                    node.parent.color = BLACK;
                    if (sibling.right != null) sibling.right.color = BLACK;
                    rotateLeft(node.parent);
                    node = root;
                }
            } else {
                Node sibling = node.parent.left;

                if (sibling.color == RED) {
                    sibling.color = BLACK;
                    node.parent.color = RED;
                    rotateRight(node.parent);
                    sibling = node.parent.left;
                }

                if ((sibling.right == null || sibling.right.color == BLACK) &&
                        (sibling.left == null || sibling.left.color == BLACK)) {
                    sibling.color = RED;
                    node = node.parent;
                } else {
                    if (sibling.left == null || sibling.left.color == BLACK) {
                        if (sibling.right != null) sibling.right.color = BLACK;
                        sibling.color = RED;
                        rotateLeft(sibling);
                        sibling = node.parent.left;
                    }
                    sibling.color = node.parent.color;
                    node.parent.color = BLACK;
                    if (sibling.left != null) sibling.left.color = BLACK;
                    rotateRight(node.parent);
                    node = root;
                }
            }
        }
        if (node != null) node.color = BLACK;
    }
}