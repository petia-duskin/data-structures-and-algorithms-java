package linearDataStructures;

import treeDataStructures.Heap;

public class PriorityQueueWithHeap {
    private Heap heap = new Heap();

    public void enqueue(int value) {
        heap.insert(value);
    }

    public int dequeue() {
        return heap.remove();
    }

    public boolean isEmpty() {
        return heap.isEmpty();
    }
}
