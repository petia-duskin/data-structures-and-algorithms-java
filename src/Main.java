import linearDataStructures.CustomLinkedList;
import linearDataStructures.DynamicArray;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {
        CustomLinkedList list = new CustomLinkedList();

        list.addFirst(10);
        list.addFirst(20);
        list.addFirst(30);
        list.addLast(40);
        System.out.println(list.indexOf(30));
    }
}