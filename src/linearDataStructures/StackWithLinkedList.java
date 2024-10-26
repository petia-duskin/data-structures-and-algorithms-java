package linearDataStructures;

import java.util.ArrayList;
import java.util.List;

public class StackWithLinkedList {
    List<Integer> values = new ArrayList<>();

    public void push(int value) {
        values.addFirst(value);
    }

    public int pop() {
        if (isEmpty()) {
            throw new IllegalStateException();
        }

        return values.removeFirst();
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
