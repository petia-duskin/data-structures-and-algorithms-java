package linearDataStructures;

import java.util.Arrays;

public class Stack {
    private int[] values = new int[100];
    private int index = 0;

    public void push(int value) {
        if (index == values.length) {
            throw new StackOverflowError();
        }
        values[index++] = value;
    }

    public int pop() {
        if (isEmpty()) {
            throw new IllegalStateException();
        }
        return values[--index];
    }

    public int peek() {
        return values[index - 1];
    }

    public boolean isEmpty() {
        return index == 0;
    }

    @Override
    public String toString() {
        return Arrays.toString(values);
    }
}
