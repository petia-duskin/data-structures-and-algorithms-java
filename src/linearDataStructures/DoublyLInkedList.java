package linearDataStructures;

import java.util.NoSuchElementException;

public class DoublyLInkedList {
    private class Node {
        Node next;
        Node prev;
        int value;

        public Node(int value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "value: " + value;
        }
    }

    Node head;
    Node tail;
    int size;

    public void addFirst(int value) {
        if (isEmpty()) {
            initialize(value);
            return;
        }

        Node node = new Node(value);
        node.next = head;
        head.prev = node;
        head = node;
        size++;
    }

    public void addLast(int value) {
        if (isEmpty()) {
            initialize(value);
            return;
        }


        Node node = new Node(value);
        node.prev = tail;
        tail.next = node;
        tail = node;
        size++;
    }

    public int removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        if (isOneElement()) {
            int headValue = head.value;
            clear();
            return headValue;
        }
        Node newHead = head.next;
        head.next = null;
        newHead.prev = null;
        head = newHead;
        size--;

        return newHead.value;
    }

    public void clear() {
        head = tail = null;
        size = 0;
    }


    public boolean isOneElement() {
        return head == tail;
    }

    public int removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        if (isOneElement()) {
            int headValue = head.value;
            clear();
            return headValue;
        }

        Node prev = tail.prev;
        int tailValue = tail.value;
        tail.prev = null;
        prev.next = null;
        tail = prev;
        size--;

        return tailValue;
    }


    private void initialize(int value) {
        Node node = new Node(value);
        head = node;
        tail = node;
    }

    public boolean isEmpty() {
        return head == null;
    }
}
