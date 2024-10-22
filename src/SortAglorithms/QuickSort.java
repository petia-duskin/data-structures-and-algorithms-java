package SortAglorithms;

import java.util.Arrays;

public class QuickSort {
    public static void main(String[] args) {
        int[] arr = {15, 6, 3, 1, 22, 10, 13};

        sort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void sort(int[] arr) {
        sort(arr, 0, arr.length - 1);
    }

    public static void sort(int[] arr, int start, int end) {
        if (start >= end) {
            return;
        }
        int boundary = partition(arr, start, end);

        sort(arr, start, boundary - 1);
        sort(arr, boundary + 1, end);
    }

    private static int partition(int[] arr, int start, int end) {
        int pivot = arr[end];
        int boundary = start - 1;

        for (int i = start; i <= end; i++) {
            if (arr[i] <= pivot) {
                swap(i, ++boundary, arr);
            }
        }

        return boundary;
    }


    public static void quickSort(int[] arr) {
        int pivotIndex = arr.length - 1;

        partitioning(arr, pivotIndex);
    }

    public static void partitioning(int[] arr, int pivot) {
        if (pivot < 1 || pivot > arr.length) {
            return;
        }

        int i = 0;
        int b = -1;
        int pivotIndex = pivot;

        while (i < pivotIndex) {
            if (arr[i] < arr[pivotIndex]) {
                swap(++b, i, arr);
            }
            i++;
        }

        if (arr[pivotIndex] < arr[pivotIndex - 1]) {
            swap(pivotIndex, pivotIndex - 1, arr);
            pivotIndex--;
        }

        swap(b + 1, pivotIndex, arr);

        partitioning(arr, b);
    }

    public static void swap(int index1, int index2, int[] arr) {
        int temp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = temp;
    }
}
