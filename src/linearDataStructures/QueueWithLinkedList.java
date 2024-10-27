package linearDataStructures;

import java.util.LinkedList;

public class QueueWithLinkedList {
    private final LinkedList<Integer> values = new LinkedList<>();

    public void enqueue(int value) {
        values.addLast(value);
    }

    public void dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException();
        }

        values.removeFirst();
    }

    public int peek() {
        if (isEmpty()) {
            throw new IllegalStateException();
        }

        return values.getFirst();
    }

    public boolean isEmpty() {
        return values.isEmpty();
    }
}
