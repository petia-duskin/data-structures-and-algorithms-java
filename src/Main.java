import linearDataStructures.DynamicArray;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int[] numbers = new int[3];
        numbers[0] = 10;
        numbers[1] = 20;
        numbers[2] = 30;
        System.out.println(numbers);
        System.out.println(Arrays.toString(numbers));
        int[] numbers2 =  {10, 20, 30};
        System.out.println(numbers2.length);

        DynamicArray dynamicArray = new DynamicArray(3);
        dynamicArray.insert(10);
        dynamicArray.insert(20);
        dynamicArray.insert(30);
        System.out.println(dynamicArray.print());
        System.out.println(dynamicArray.indexOf(30));
        System.out.println(dynamicArray.indexOf(10));
        dynamicArray.insert(40);
        System.out.println(dynamicArray.print());
        dynamicArray.insert(50);
        dynamicArray.insert(60);
        dynamicArray.removeAt(5);
        System.out.println(dynamicArray.print());
        dynamicArray.insert(70);
        System.out.println(dynamicArray.print());


        ArrayList<Integer> numbers5 = new ArrayList<>();
        numbers5.add(10);
        numbers5.add(20);
        numbers5.add(30);

        System.out.println(numbers5);
    }
}