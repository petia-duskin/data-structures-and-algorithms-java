package linearDataStructures;

public class CustomLinkedList {
    private class Node {
        private Node next = null;
        private int value;

        public Node(int value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "Node{value=" + value + '}';
        }
    }

    private Node first = null;
    private Node last = null;

    public void addFirst(int value) {
        if (isEmpty()) {
            initialize(value);
        } else {
            Node newNode = new Node(value);
            newNode.next = first;
            first = newNode;
        }
    }

    public void addLast(int value) {
        if (isEmpty()) {
            initialize(value);
        } else {
            Node newNode = new Node(value);
            last.next = newNode;
            last = newNode;
        }
    }

    public void removeFirst() {
        if (isEmpty()) {
            throw new IllegalStateException();
        }

        Node next = first.next;
        first.next = null;
        first = next;
    }

    public void removeLast() {
        if (isEmpty()) {
            throw new IllegalStateException();
        }

        Node prev = null;
        Node current = first;
        while (current.next != null) {
            if (current == last) {
                break;
            }
            prev = current;
            current = current.next;
        }

        prev.next = null;
        last = prev;
    }

    public boolean contains(int value) {
        if (isEmpty()) {
            return false;
        }

        Node current = first;
        while (current != null) {
            if (current.value == value) {
                return true;
            }
            current = current.next;
        }

        return false;
    }

    public int indexOf(int value) {
        if (isEmpty()) {
            throw new IllegalArgumentException();
        }

        int index = 0;
        Node current = first;
        while (current != null) {
            if (current.value == value) {
                return index;
            }
            current = current.next;
            index++;
        }

        return -1;
    }

    private void initialize(int value) {
        Node newNode = new Node(value);
        first = last = newNode;
    }

    private boolean isEmpty() {
        return first == null;
    }

}