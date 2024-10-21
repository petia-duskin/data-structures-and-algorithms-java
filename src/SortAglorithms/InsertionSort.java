package SortAglorithms;

import java.util.Arrays;

public class InsertionSort {
    public static void main(String[] args) {
        int[] arr = {5, 3, 1, 2, 10, 15, 7};
        insertionSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void insertionSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int newIndex = indexToInsert(i, arr);
            int currentValue = arr[i];
            shiftItemsToRight(newIndex, i, arr);
            arr[newIndex] = currentValue;
        }
    }

    public static int indexToInsert(int currentIndex, int[] arr) {
        for (int i = 0; i < currentIndex; i++) {
            if (arr[currentIndex] < arr[i]) {
                return i;
            }
        }

        return currentIndex;
    }

    public static void shiftItemsToRight(int fromIndex, int toIndex, int[] arr) {
        for (int i = toIndex; i > fromIndex; i--) {
            swap(i, i - 1, arr);
        }
    }

    public static void swap(int index1, int index2, int[] arr) {
        int temp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = temp;
    }
}
