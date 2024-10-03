package linearDataStructures;

import java.util.Arrays;

public class PriorityQueueWithArray {
    private int[] values = new int[3];
    private int count = 0;


    public void insert(int value) {
        if (isFull()) {
            throw new IllegalStateException();
        }

        int index = getIndexToInsert(value);
        shiftItemsToRight(index);

        values[index] = value;
        count++;
    }

    private int getIndexToInsert(int value) {
        int index = 0;
        while (index < count) {
            if (value < values[index]) {
                break;
            }
            index++;
        }

        return index;
    }

    private void shiftItemsToRight(int index) {
        for (int i = count; i > index; i--) {
            values[i] = values[i - 1];
        }
    }

    public int remove() {
        if (isEmpty()) {
            throw new IllegalStateException();
        }

        int removedValue = values[0];
        for (int i = 0; i < count; i++) {
            values[i] = values[i + 1];
        }
        count--;

        return removedValue;
    }

    public int peek() {
        if (isEmpty()) {
            throw new IllegalStateException();
        }

        return values[0];
    }

    public boolean isFull() {
        return count == values.length;
    }

    public boolean isEmpty() {
        return count == 0;
    }

    public int size() {
        return count;
    }

    @Override
    public String toString() {
        return Arrays.toString(Arrays.copyOfRange(values, 0, count));
    }
}
