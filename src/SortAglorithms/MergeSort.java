package SortAglorithms;

import java.util.Arrays;

public class MergeSort {
    public static void main(String[] args) {
        int[] arr = {2, 8, 4, 1, 3};
        mergeSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void mergeSort(int[] arr) {
        System.out.println(Arrays.toString(split(arr)));
    }

    public static int[] split(int[] arr) {
        if (arr.length <= 1) {
            return arr;
        }

        int[] left = split(Arrays.copyOfRange(arr, 0, middleIndex(arr.length)));
        int[] right = split(Arrays.copyOfRange(arr, middleIndex(arr.length), arr.length));

        return merge(left, right);
    }

    public static int[] merge(int[] arr1, int[] arr2) {
        int leftIndex = 0;
        int rightIndex = 0;
        int index = 0;

        int[] result = new int[arr1.length + arr2.length];

        while (leftIndex < arr1.length && rightIndex < arr2.length) {
            if (arr1[leftIndex] < arr2[rightIndex]) {
                result[index] = arr1[leftIndex];
                leftIndex++;
            } else {
                result[index] = arr2[rightIndex];
                rightIndex++;
            }
            index++;
        }

        if (rightIndex < arr2.length) {
            for (int i = rightIndex; i < arr2.length; i++) {
                result[index++] = arr2[i];
            }
        } else if (leftIndex < arr1.length) {
            for (int i = leftIndex; i < arr1.length; i++) {
                result[index++] = arr1[i];
            }
        }

        return result;
    }

    public static int middleIndex(int arrLength) {
        return arrLength / 2;
    }


    public static void swap(int index1, int index2, int[] arr) {
        int temp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = temp;
    }
}
