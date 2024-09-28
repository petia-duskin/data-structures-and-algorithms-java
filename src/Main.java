import linearDataStructures.CustomLinkedList;
import linearDataStructures.DynamicArray;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {
        CustomLinkedList list = new CustomLinkedList();

        list.addLast(10);
        list.addLast(20);
        list.addLast(30);
        System.out.println(Arrays.toString(list.toArray()));

        System.out.println(list.getKthFromTheEnd(3));
    }
}