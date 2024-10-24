package sortAglorithms;

import java.util.Arrays;

public class BubbleSort {
    public static void main(String[] args) {
        int[] arr = {3, 5, 1, 2, 10, 15, 7};
        bubbleSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void bubbleSort(int[] arr) {
        boolean isSorted;
        for (int i = 0; i < arr.length; i++) {
            isSorted = true;
            for (int j = 0; j < arr.length - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(j, j + 1, arr);
                    isSorted = false;
                }
            }
            if (isSorted) {
                return;
            }
        }
    }

    public static void swap(int index1, int index2, int[] arr) {
        int temp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = temp;
    }
}
