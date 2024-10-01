package linearDataStructures;

import java.util.Arrays;

public class Queue {
    private int[] values = new int[4];
    private int headIndex = 0;
    private int tailIndex = 0;
    private int count = 0;

    public void enqueue(int value) {
        if (isFull()) {
            throw new StackOverflowError();
        }
        values[tailIndex] = value;
        tailIndex = (tailIndex + 1) % values.length;
        count++;
    }

    public int dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException();
        }
        int value = values[headIndex];
        headIndex = (headIndex + 1) % values.length;
        count--;
        return value;
    }

    public int peek() {
        if (isEmpty()) {
            throw new IllegalStateException();
        }
        return values[headIndex];
    }

    public int size() {
        return count;
    }

    private boolean isFull() {
        return values.length == count;
    }

    private boolean isEmpty() {
        return count == 0;
    }

    @Override
    public String toString() {
        return Arrays.toString(values);
    }
}
