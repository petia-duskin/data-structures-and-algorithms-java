package linearDataStructures;

public class QueueWithStack {
    private int count = 0;
    private java.util.Stack<Integer> enqueueStack = new java.util.Stack();
    private java.util.Stack<Integer> dequeueStack = new java.util.Stack();

    public void enqueue(int value) {
        enqueueStack.push(value);
        count++;
    }

    public int dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException();
        }

        updateStacks();
        count--;

        return dequeueStack.pop();
    }

    private void updateStacks() {
        if (dequeueStack.isEmpty()) {
            while (!enqueueStack.isEmpty()) {
                dequeueStack.push(enqueueStack.pop());
            }
        }
    }

    public int peek() {
        if (isEmpty()) {
            throw new IllegalStateException();
        }
        if (dequeueStack.isEmpty()) {
            updateStacks();
        }

        return dequeueStack.peek();
    }

    public int count() {
        return count;
    }

    public boolean isEmpty() {
        return enqueueStack.isEmpty() && dequeueStack.isEmpty();
    }
}
