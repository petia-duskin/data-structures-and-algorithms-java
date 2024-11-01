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

        private boolean hasLeftChild(int index) {
            return index > 0 && getChild(index - 1) != null;
        }

        private boolean hasRightChild(int index) {
            return index < children.length && getChild(index + 1) != null;
        }

        private Node getLeftChild(int index) {
            return getChild(index - 1);
        }

        private Node getRightChild(int index) {
            return getChild(index + 1);
        }

        private int getPredecessor(int childIndex) {
            Node predecessor = getChild(childIndex - 1);
            return predecessor.getValue(predecessor.valuesCount - 1);
        }

        private int getSuccessor(int childIndex) {
            Node successor = getChild(childIndex + 1);
            return successor.getValue(0);
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

    public void print() {
        print(root, "");
    }

    // Сперва нам надо найти parent узла, который мы будем удалять
    public void remove(int value) {
        Node parentNode = getParent(value);
        if (parentNode == null) {
            throw new IllegalArgumentException();
        }

        int childIndex = searchChildIndex(parentNode, value);
        Node removeNode = parentNode.getChild(childIndex);

        removeValue(removeNode, value);

        System.out.println("parent " + parentNode);

        if (isValidSize(removeNode)) {
            System.out.println(removeNode + " size is valid");
        } else {
            System.out.println(removeNode + " size is not valid");
            if (parentNode.hasLeftChild(childIndex)) {
                System.out.println(removeNode + " has left child");
                if (mayBorrow(parentNode.getLeftChild(childIndex))) {
                    int predecessor = parentNode.getPredecessor(childIndex);
                    Node predecessorNode = parentNode.getLeftChild(childIndex);
                    System.out.println("We can borrow " + parentNode.getPredecessor(childIndex));
                    removeNode.values[0] = parentNode.values[0];
                    parentNode.values[0] = predecessor;
                    removeNode.valuesCount = removeNode.valuesCount + 1;
                    predecessorNode.valuesCount = predecessorNode.valuesCount - 1;
                } else {
                    System.out.println("We can't borrow " + parentNode.getPredecessor(childIndex));
                }
            } else if (parentNode.hasRightChild(childIndex)) {
                System.out.println(removeNode + " has right child");
                if (mayBorrow(parentNode.getRightChild(childIndex))) {
                    int successor = parentNode.getSuccessor(childIndex);
                    Node successorNode = parentNode.getRightChild(childIndex);
                    System.out.println("We can borrow " + parentNode.getSuccessor(childIndex));
                    int parentValue = parentNode.values[parentNode.valuesCount - 1];
                    removeNode.values[0] = parentValue;
                    parentNode.values[parentNode.valuesCount - 1] = successor;
                    removeNode.valuesCount = removeNode.valuesCount + 1;
                    shiftItemsToRemove(0, successorNode.valuesCount, successorNode.values);
                    successorNode.valuesCount = successorNode.valuesCount - 1;
                } else {
                    System.out.println("We can't borrow " + parentNode.getSuccessor(childIndex));
                }
            } else {
                // merge the node with either the left sibling node or the right sibling node
                System.out.println("We can't borrow from left and right");
                if (parentNode.hasLeftChild(childIndex)) {
                    Node predecessorNode = parentNode.getLeftChild(childIndex - 1);
                    int[] leftValues = Arrays.copyOfRange(predecessorNode.values, 0, predecessorNode.valuesCount);
                    shiftItemsToRemove(0, leftValues.length, removeNode.values);
                    for (int i = 0; i < leftValues.length; i++) {
                        removeNode.values[i] = leftValues[i];
                    }

                    parentNode.getChild(childIndex - 1).valuesCount = 0;
                    removeNode.valuesCount = removeNode.valuesCount + leftValues.length;
                } else if (parentNode.hasRightChild(childIndex)) {
                    Node successorNode = parentNode.getRightChild(childIndex + 1);
                    int[] rightValues = Arrays.copyOfRange(successorNode.values, 0, successorNode.valuesCount);
                    int index = removeNode.valuesCount;
                    int successorIndex = 0;
                    while (successorIndex < rightValues.length) {
                        removeNode.values[index++] = rightValues[successorIndex++];
                    }

                    parentNode.getRightChild(childIndex - 1).valuesCount = 0;
                    removeNode.valuesCount = removeNode.valuesCount + rightValues.length;
                }
            }
        }


    }

    private void removeValue(Node removeNode, int value) {
        for (int i = 0; i < removeNode.valuesCount; i++) {
            if (removeNode.getValue(i) == value) {
                shiftItemsToRemove(i, removeNode.valuesCount - 1, removeNode.values);
                removeNode.valuesCount = removeNode.valuesCount - 1;
                return;
            }
        }
    }

    private boolean isValidSize(Node node) {
        return node.valuesCount >= MIN_NODE_SIZE;
    }

    private boolean mayBorrow(Node node) {
        return node.valuesCount > MIN_NODE_SIZE;
    }

    private void shiftItemsToRemove(int from, int to, int[] arr) {
        for (int i = from; i < to; i++) {
            arr[i] = arr[i + 1];
        }
    }

    private Node getParent(int value) {
        return getParent(root, root, value);
    }

    private int searchChildIndex(Node node, int value) {
        return searchChildIndex(node, 0, value);
    }

    private int searchChildIndex(Node node, int prevIndex, int value) {
        int currentIndex = 0;

        while (currentIndex < node.valuesCount && value > node.getValue(currentIndex)) {
            currentIndex++;
        }

        if (node.getValue(currentIndex) == value) {
            return prevIndex;
        } else if (node.isLeaf) {
            return -1;
        }

        return searchChildIndex(node.getChild(currentIndex), currentIndex, value);
    }

    private Node getParent(Node node, Node prev, int value) {
        int index = 0;

        while (index < node.valuesCount && value > node.getValue(index)) {
            index++;
        }

        if (node.getValue(index) == value) {
            return prev;
        } else if (node.isLeaf) {
            return null;
        }

        return getParent(node.getChild(index), node, value);
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

        tree.insert(20);
        tree.insert(40);
        tree.insert(10);
        tree.insert(28);
        tree.insert(33);
        tree.insert(50);
        tree.insert(60);
        tree.insert(5);
        tree.insert(15);
        tree.insert(25);
        tree.insert(30);
        tree.insert(35);
        tree.insert(45);
        tree.insert(55);
        tree.insert(65);

        tree.remove(30);
        System.out.println("Debug message");
    }
}