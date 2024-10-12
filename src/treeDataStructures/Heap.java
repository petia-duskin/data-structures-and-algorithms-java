package treeDataStructures;

public class Heap {
    private int[] heap = new int[100];
    private int size = 0;

    public void insert(int value) {
        heap[size++] = value;
        bubbleUp();
    }

    public int remove() {
        if (isEmpty()) {
            throw new IllegalStateException();
        }
        int value = heap[0];

        swap(0, --size);

        heap[size] = 0;

        bubbleDown();

        return value;
    }

    private void bubbleDown() {
        int index = 0;
        while (!isValidParent(index)) {
            int newIndex = getGreaterChildIndex(index);
            swap(index, newIndex);
            index = newIndex;
        }
    }

    private boolean isValidParent(int index) {
        return getValue(index) > leftChild(index) && getValue(index) > rightChild(index);
    }

    private int getGreaterChildIndex(int index) {
        return getValue(index) < leftChild(index) ? leftChildIndex(index) : rightChildIndex(index);
    }

    public int max() {
        if (isEmpty()) {
            throw new IllegalStateException();
        }

        return heap[0];
    }

    private void bubbleUp() {
        int index = size - 1;
        while (index > 0 && !isValidChild(index)) {
            swap(parentIndex(index), index);
            index = parentIndex(index);
        }
    }

    private void swap(int index1, int index2) {
        int temp = heap[index1];
        heap[index1] = heap[index2];
        heap[index2] = temp;
    }

    private boolean isValidChild(int index) {
        return parent(index) > getValue(index);
    }

    private int getValue(int index) {
        return heap[index];
    }

    private int leftChild(int index) {
        return heap[leftChildIndex(index)];
    }

    private int rightChild(int index) {
        return heap[rightChildIndex(index)];
    }

    private int parent(int index) {
        return heap[parentIndex(index)];
    }

    private boolean isValidIndex(int index) {
        return index >= 0 && index < heap.length;
    }

    private int parentIndex(int index) {
        if (index <= 0) {
            return 0;
        }
        return index % 2 == 0 ? index / 2 - 1 : (index + 1) / 2 - 1;
    }

    private int leftChildIndex(int index) {
        return (index * 2) + 1;
    }

    private int rightChildIndex(int index) {
        return (index * 2) + 2;
    }

    private boolean isEmpty() {
        return size == 0;
    }
}
