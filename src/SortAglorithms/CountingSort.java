package SortAglorithms;

import java.util.Arrays;

public class CountingSort {
    public static void main(String[] args) {
        int[] arr = {2, 3, 4, 4, 5, 5, 5, 1, 1, 3, 4, 7};
        countingSort(arr, 7);
        System.out.println(Arrays.toString(arr));
    }

    public static void countingSort(int[] arr, int maxValue) {
        int[] countArray = new int[maxValue + 1];

        for (int i = 0; i < arr.length; i++) {
            countArray[arr[i]]++;
        }

        int arrIndex = 0;
        for (int i = 0; i < countArray.length; i++) {
            for (int j = 0; j < countArray[i]; j++) {
                arr[arrIndex++] = i;
            }
        }
    }
}
