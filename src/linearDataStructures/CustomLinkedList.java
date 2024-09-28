package linearDataStructures;

import java.util.NoSuchElementException;

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
    private int size;

    public void addFirst(int value) {
        if (isEmpty()) {
            initialize(value);
        } else {
            Node newNode = new Node(value);
            newNode.next = first;
            first = newNode;
        }
        size++;
    }

    public void addLast(int value) {
        if (isEmpty()) {
            initialize(value);
        } else {
            Node newNode = new Node(value);
            last.next = newNode;
            last = newNode;
        }
        size++;
    }

    public void removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        if (isOneElement()) {
            reset();
        } else {
            Node next = first.next;
            first.next = null;
            first = next;
        }


        size--;
    }

    private boolean isOneElement() {
        return first == last;
    }

    private void reset() {
        first = last = null;
    }

    public void removeLast() {
        if (isEmpty()) {
            throw new IllegalStateException();
        }

        if (isOneElement()) {
            reset();
        } else {
            Node prev = getPrevious();
            assert prev != null;
            prev.next = null;
            last = prev;
        }

        size--;
    }

    private Node getPrevious() {
        Node current = first;
        while (current != null) {
            if (current.next == last) {
                return current;
            }
            current = current.next;
        }

        return null;
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

    public int size() {
        return size;
    }

    private void initialize(int value) {
        Node newNode = new Node(value);
        first = last = newNode;
    }

    private boolean isEmpty() {
        return first == null;
    }

    public int[] toArray() {
        int[] array = new int[size];

        Node current = first;
        int index = 0;

        while (current != null) {
            array[index++] = current.value;
            current = current.next;
        }

        return array;
    }

}
