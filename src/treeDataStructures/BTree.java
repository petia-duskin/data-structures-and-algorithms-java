package treeDataStructures;


import java.util.Arrays;

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

        private Node getRightChild(int index) {
            return getChild(index + 1);
        }

        private Node getLeftChild(int index) {
            return getChild(index - 1);
        }

        private int findValue(int value) {
            int index = 0;
            while (index < valuesCount && values[index] < value) {
                index++;
            }

            return index;
        }

        @Override
        public String toString() {
            return Arrays.toString(Arrays.copyOfRange(values, 0, valuesCount));
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

    public boolean search(int value) {
        return search(root, value);
    }

    private boolean search(Node node, int value) {
        int index = 0;

        while (index < node.valuesCount && value > node.getValue(index)) {
            index++;
        }

        if (node.getValue(index) == value) {
            return true;
        } else if (node.isLeaf) {
            return false;
        }

        return search(node.getChild(index), value);
    }

    private void remove(int value) {
        remove(root, value);
        if (root.valuesCount == 0) {
            root = root.isLeaf ? null : root.getChild(0);
        }
    }

    private void remove(Node node, int value) {
        int index = node.findValue(value);

        if (index < node.valuesCount && node.getValue(index) == value) {
            if (node.isLeaf) {
                removeFromLeaf(node, index);
            } else {
                removeFromNonLeaf(node, index);
            }
        } else if (!node.isLeaf) {
            boolean lastChild = index == node.valuesCount;

            if (node.getChild(index).valuesCount < T) {
                fill(node, index);
            }

            if (lastChild && index > node.valuesCount) {
                remove(node.getLeftChild(index), value);
            } else {
                remove(node.getChild(index), value);
            }
        }
    }

    private void removeFromLeaf(Node node, int index) {
        for (int i = index + 1; i < node.valuesCount; i++) {
            node.values[i - 1] = node.values[i];
        }

        node.valuesCount--;
    }

    private void removeFromNonLeaf(Node node, int index) {
        int value = node.getValue(index);

        if (node.getChild(index).valuesCount >= T) {
            int pred = getPredecessor(node, index);
            node.values[index] = pred;
            remove(node.getChild(index), pred);
        } else if (node.getRightChild(index).valuesCount >= T) {
            int succ = getSuccessor(node, index);
            node.values[index] = succ;
            remove(node.getRightChild(index), succ);
        } else {
            merge(node, index);
            remove(node.getChild(index), value);
        }
    }

    private int getPredecessor(Node node, int index) {
        Node current = node.getChild(index);
        while (!current.isLeaf) {
            current = current.getChild(current.valuesCount);
        }

        return current.getValue(current.valuesCount - 1);
    }

    private int getSuccessor(Node node, int index) {
        Node current = node.getRightChild(index);
        while (!current.isLeaf) {
            current = current.getChild(0);
        }

        return current.getValue(0);
    }

    private void fill(Node node, int index) {
        if (index != 0 && node.children[index - 1].valuesCount >= T) {
            borrowFromPrev(node, index);
        } else if (index != node.valuesCount && node.children[index + 1].valuesCount >= T) {
            borrowFromNext(node, index);
        } else {
            if (index != node.valuesCount) {
                merge(node, index);
            } else {
                merge(node, index - 1);
            }
        }
    }

    private void borrowFromPrev(Node node, int index) {
        Node child = node.children[index];
        Node leftSibling = node.getLeftChild(index);

        for (int i = child.valuesCount - 1; i >= 0; i--) {
            child.values[i + 1] = child.values[i];
        }

        if (!child.isLeaf) {
            for (int i = child.valuesCount; i >= 0; i--) {
                child.children[i + 1] = child.children[i];
            }
        }

        child.values[0] = node.values[index - 1];

        if (!child.isLeaf) {
            child.children[0] = leftSibling.children[leftSibling.valuesCount];
        }

        node.values[index - 1] = leftSibling.values[leftSibling.valuesCount - 1];
        child.valuesCount++;
        leftSibling.valuesCount--;
    }

    private void borrowFromNext(Node node, int index) {
        Node child = node.children[index];
        Node rightSibling = node.getRightChild(index);

        child.values[child.valuesCount] = node.values[index];
        if (!child.isLeaf) {
            child.children[child.valuesCount + 1] = rightSibling.children[0];
        }

        node.values[index] = rightSibling.values[0];
        for (int i = 1; i < rightSibling.valuesCount; i++) {
            rightSibling.values[i - 1] = rightSibling.values[i];
        }

        if (!rightSibling.isLeaf) {
            for (int i = 1; i <= rightSibling.valuesCount; i++) {
                rightSibling.children[i - 1] = rightSibling.children[i];
            }
        }

        child.valuesCount++;
        rightSibling.valuesCount--;
    }

    private void merge(Node node, int index) {
        Node child = node.getChild(index);
        Node rightSibling = node.getRightChild(index);

        child.values[T - 1] = node.getValue(index);

        for (int i = 0; i < rightSibling.valuesCount; i++) {
            child.values[i + T] = rightSibling.values[i];
        }

        if (!child.isLeaf) {
            for (int i = 0; i <= rightSibling.valuesCount; i++) {
                child.children[i + T] = rightSibling.children[i];
            }
        }

        for (int i = index + 1; i < node.valuesCount; i++) {
            node.values[i - 1] = node.values[i];
        }

        for (int i = index + 2; i <= node.valuesCount; i++) {
            node.children[i - 1] = node.children[i];
        }

        child.valuesCount += rightSibling.valuesCount + 1;
        node.valuesCount--;
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

        for (int i = 1; i <= 17; i++) {
            tree.insert(i);
        }

        tree.remove(15);
        tree.remove(13);
        tree.remove(4);

        System.out.println("Debug message");
    }
}