package treeDataStructures;

public class SplayTree {
    private Node root;

    private class Node {
        int key;
        Node left, right;

        Node(int key) {
            this.key = key;
        }
    }

    // Splay операция: поднимает узел с данным ключом на верх дерева
    private Node splay(Node root, int key) {
        if (root == null || root.key == key) return root;

        // Ключ в левом поддереве
        if (key < root.key) {
            if (root.left == null) return root;

            // Zig-Zig (левый-левый)
            if (key < root.left.key) {
                root.left.left = splay(root.left.left, key);
                root = rightRotate(root);
            }
            // Zig-Zag (левый-правый)
            else if (key > root.left.key) {
                root.left.right = splay(root.left.right, key);
                if (root.left.right != null)
                    root.left = leftRotate(root.left);
            }

            return (root.left == null) ? root : rightRotate(root);
        } else { // Ключ в правом поддереве
            if (root.right == null) return root;

            // Zag-Zig (правый-левый)
            if (key < root.right.key) {
                root.right.left = splay(root.right.left, key);
                if (root.right.left != null)
                    root.right = rightRotate(root.right);
            }
            // Zag-Zag (правый-правый)
            else if (key > root.right.key) {
                root.right.right = splay(root.right.right, key);
                root = leftRotate(root);
            }

            return (root.right == null) ? root : leftRotate(root);
        }
    }

    // Правый поворот
    private Node rightRotate(Node x) {
        Node y = x.left;
        x.left = y.right;
        y.right = x;
        return y;
    }

    // Левый поворот
    private Node leftRotate(Node x) {
        Node y = x.right;
        x.right = y.left;
        y.left = x;
        return y;
    }

    // Метод для вставки ключа
    public void insert(int key) {
        if (root == null) {
            root = new Node(key);
            return;
        }

        root = splay(root, key);

        if (root.key == key) return; // Ключ уже существует

        Node newNode = new Node(key);

        if (key < root.key) {
            newNode.right = root;
            newNode.left = root.left;
            root.left = null;
        } else {
            newNode.left = root;
            newNode.right = root.right;
            root.right = null;
        }

        root = newNode;
    }

    // Метод для поиска ключа
    public boolean find(int key) {
        if (root == null) return false;

        root = splay(root, key);

        return root.key == key;
    }

    // Метод для удаления ключа
    public void remove(int key) {
        if (root == null) return;

        root = splay(root, key);

        if (root.key != key) return; // Ключ не найден

        if (root.left == null) {
            root = root.right;
        } else {
            Node temp = root.right;
            root = root.left;
            root = splay(root, key);
            root.right = temp;
        }
    }

    // Дополнительный метод для печати дерева (для проверки)
    public void printTree() {
        printTree(root, "", true);
    }

    private void printTree(Node node, String indent, boolean last) {
        if (node != null) {
            System.out.print(indent);
            if (last) {
                System.out.print("R----");
                indent += "   ";
            } else {
                System.out.print("L----");
                indent += "|  ";
            }
            System.out.println(node.key);
            printTree(node.left, indent, false);
            printTree(node.right, indent, true);
        }
    }
}