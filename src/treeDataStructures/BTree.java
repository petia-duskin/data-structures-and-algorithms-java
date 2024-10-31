package treeDataStructures;


// Preemptive node split
class BTree {
    private static final int T = 2; // Минимальная степень дерева (макс. 2*T - 1 элементов в узле)
    private final int MAX_NODE_SIZE = 2 * T - 1;
    private final int MAX_CHILDREN_SIZE = 2 * T;
    private final int MIN_NODE_SIZE = T - 1;
    private Node root = new Node();

    // Класс, представляющий узел B-дерева
    private class Node {
        private int valuesCount; // Количество ключей в узле
        private int[] values = new int[MAX_NODE_SIZE];
        private Node[] children = new Node[MAX_CHILDREN_SIZE];
        private boolean isLeaf = true;

        // Вставка ключа в несмонтированный узел
        private void insertNonFull(int value) {
            if (isLeaf) {
                values[shiftValuesToInsert(value)] = value;
                valuesCount++;
            } else {
                int insertionIndex = getChildIndexToInsert(value);

                if (hasMaxSize(getChild(insertionIndex))) {
                    splitChild(insertionIndex, children[insertionIndex]);
                    if (isGreater(value, getValue(insertionIndex))) {
                        insertionIndex++;
                    }
                }
                getChild(insertionIndex).insertNonFull(value);
            }
        }

        private int getValue(int index) {
            return values[index];
        }

        private Node getChild(int index) {
            return children[index];
        }

        private void setChild(int index, Node child) {
            children[index] = child;
        }

        private void setIfLeaf(boolean flag) {
            isLeaf = flag;
        }

        // сдвигаем элементы вправо, пока не найдется место для вставки нового значения
        private int shiftValuesToInsert(int key) {
            int i = valuesCount - 1;

            while (i >= 0 && values[i] > key) {
                values[i + 1] = values[i];
                i--;
            }

            return i + 1;
        }

        // ищем индекс внутреннего узла, куда вставим элемент
        private int getChildIndexToInsert(int key) {
            int i = valuesCount - 1;
            while (i >= 0 && values[i] > key) {
                i--;
            }

            return i + 1;
        }

        // Разбиение полных узлов
        private void splitChild(int childIndex, Node leftChild) {
            Node rightChild = new Node();
            rightChild.isLeaf = leftChild.isLeaf;
            rightChild.valuesCount = MIN_NODE_SIZE;

            fillRightChild(rightChild, leftChild);

            if (!leftChild.isLeaf) {
                updateRightChildLinks(rightChild, leftChild);
            }

            // мы обрезаем правую часть
            leftChild.valuesCount = MIN_NODE_SIZE;

            shiftChildrenToRight(childIndex);
            setRightChild(childIndex, rightChild);

            shiftParentValues(childIndex);

            values[childIndex] = leftChild.values[MIN_NODE_SIZE];
            valuesCount++;
        }

        private void shiftParentValues(int childIndex) {
            for (int j = valuesCount - 1; j >= childIndex; j--) {
                values[j + 1] = values[j];
            }
        }

        private void setRightChild(int childIndex, Node rightChild) {
            children[childIndex + 1] = rightChild;
        }

        private void shiftChildrenToRight(int childIndex) {
            for (int j = valuesCount; j >= childIndex + 1; j--) {
                children[j + 1] = children[j];
            }
        }

        private void fillRightChild(Node rightChild, Node node) {
            for (int j = 0; j < MIN_NODE_SIZE; j++) {
                rightChild.values[j] = node.values[j + T];
            }
        }

        private void updateRightChildLinks(Node rightChild, Node node) {
            for (int j = 0; j < T; j++) {
                rightChild.children[j] = node.children[j + T];
            }
        }
    }

    private boolean hasMaxSize(Node node) {
        return node.valuesCount == MAX_NODE_SIZE;
    }

    private boolean isGreater(int value1, int value2) {
        return value1 > value2;
    }

    public void insert(int value) {
        if (hasMaxSize(root)) {
            Node newRoot = balance();
            newRoot.insertNonFull(value);
        } else {
            root.insertNonFull(value);
        }
    }

    private Node balance() {
        Node prevRoot = root;

        Node newRoot = new Node();
        root = newRoot;
        newRoot.setIfLeaf(false);
        newRoot.setChild(0, prevRoot);
        newRoot.splitChild(0, prevRoot);

        return newRoot;
    }

    public void print() {
        print(root, "");
    }

    private void print(Node node, String indent) {
        System.out.print(indent);
        for (int i = 0; i < node.valuesCount; i++) {
            System.out.print(node.values[i] + " ");
        }
        System.out.println();

        if (!node.isLeaf) {
            for (int i = 0; i <= node.valuesCount; i++) {
                print(node.children[i], indent + "    ");
            }
        }
    }

    public static void main(String[] args) {
        BTree tree = new BTree();

        tree.insert(10);
        tree.insert(20);
        tree.insert(30);
        tree.insert(40); // root split
        tree.insert(15);
        tree.insert(41);
        tree.insert(1);
        tree.insert(35);
        tree.insert(5);
        tree.insert(6);
        tree.insert(7);

        tree.print();
    }
}