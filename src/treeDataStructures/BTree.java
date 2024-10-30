package treeDataStructures;

class BTree {
    private static final int T = 2; // Минимальная степень дерева (макс. 2*T - 1 элементов в узле)
    private Node root;

    // Класс, представляющий узел B-дерева
    private static class Node {
        int n; // Количество ключей в узле
        int[] keys = new int[2 * T - 1];
        Node[] children = new Node[2 * T];
        boolean isLeaf = true;

        // Вставка ключа в несмонтированный узел
        void insertNonFull(int key) {
            int i = n - 1;

            if (isLeaf) {
                while (i >= 0 && keys[i] > key) {
                    keys[i + 1] = keys[i];
                    i--;
                }
                keys[i + 1] = key;
                n++;
            } else {
                while (i >= 0 && keys[i] > key) {
                    i--;
                }
                i++;

                if (children[i].n == 2 * T - 1) {
                    splitChild(i, children[i]);
                    if (keys[i] < key) {
                        i++;
                    }
                }
                children[i].insertNonFull(key);
            }
        }

        // Разбиение полных узлов
        void splitChild(int i, Node y) {
            Node z = new Node();
            z.isLeaf = y.isLeaf;
            z.n = T - 1;

            for (int j = 0; j < T - 1; j++) {
                z.keys[j] = y.keys[j + T];
            }

            if (!y.isLeaf) {
                for (int j = 0; j < T; j++) {
                    z.children[j] = y.children[j + T];
                }
            }

            y.n = T - 1;

            for (int j = n; j >= i + 1; j--) {
                children[j + 1] = children[j];
            }
            children[i + 1] = z;

            for (int j = n - 1; j >= i; j--) {
                keys[j + 1] = keys[j];
            }
            keys[i] = y.keys[T - 1];
            n++;
        }
    }

    public BTree() {
        root = new Node();
    }

    public void insert(int key) {
        Node r = root;
        if (r.n == 2 * T - 1) {
            Node s = new Node();
            root = s;
            s.isLeaf = false;
            s.children[0] = r;
            s.splitChild(0, r);
            s.insertNonFull(key);
        } else {
            r.insertNonFull(key);
        }
    }

    public void print() {
        print(root, "");
    }

    private void print(Node node, String indent) {
        System.out.print(indent);
        for (int i = 0; i < node.n; i++) {
            System.out.print(node.keys[i] + " ");
        }
        System.out.println();

        if (!node.isLeaf) {
            for (int i = 0; i <= node.n; i++) {
                print(node.children[i], indent + "    ");
            }
        }
    }

    public static void main(String[] args) {
        BTree tree = new BTree();
        int[] values = {10, 20, 30, 40};

        for (int value : values) {
            tree.insert(value);
        }

        tree.print();
    }
}