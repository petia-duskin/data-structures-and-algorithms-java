package SortAglorithms;

import java.util.Arrays;

public class MergeSort {
    public static void main(String[] args) {
        int[] arr = {2, 8, 4, 1, 3};
        int[] sortedArr = mergeSort(arr);
        System.out.println(Arrays.toString(sortedArr));
    }

    public static int[] mergeSort(int[] arr) {
        return split(arr);
    }

    public static int[] split(int[] arr) {
        if (arr.length <= 1) {
            return arr;
        }

        int[] left = split(Arrays.copyOfRange(arr, 0, middleIndex(arr.length)));
        int[] right = split(Arrays.copyOfRange(arr, middleIndex(arr.length), arr.length));

        return merge(left, right);
    }

    public static int[] merge(int[] left, int[] right) {
        int leftIndex = 0;
        int rightIndex = 0;
        int index = 0;

        int[] result = new int[left.length + right.length];

        while (leftIndex < left.length && rightIndex < right.length) {
            if (left[leftIndex] < right[rightIndex]) {
                result[index] = left[leftIndex];
                leftIndex++;
            } else {
                result[index] = right[rightIndex];
                rightIndex++;
            }
            index++;
        }

        while (leftIndex < left.length) {
            result[index++] = left[leftIndex++];
        }

        while (rightIndex < right.length) {
            result[index++] = right[rightIndex++];
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
