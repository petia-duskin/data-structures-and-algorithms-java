package searchAlgorithms;

public class Search {
    public int linearSearch(int[] arr, int value) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == value) {
                return i;
            }
        }

        return -1;
    }

    public int binarySearch(int[] arr, int value) {
        return binarySearch(arr, 0, arr.length - 1, value);
    }

    public int binarySearch(int[] arr, int low, int max, int value) {
        if (low > max) {
            return -1;
        }
        int middleIndex = (low + max) / 2;
        if (arr[middleIndex] == value) {
            return middleIndex;
        }

        if (arr[middleIndex] > value) {
            return binarySearch(arr, low, middleIndex - 1, value);
        }
        if (arr[middleIndex] < value) {
            return binarySearch(arr, middleIndex + 1, max, value);
        }

        return -1;
    }

    public int ternarySearch(int[] arr, int value) {
        return ternarySearch(arr, value, 0, arr.length - 1);
    }

    private int ternarySearch(int[] arr, int value, int left, int right) {
        int partitionSize = (right - left) / 3;
        int mid1 = left + partitionSize;
        int mid2 = right - partitionSize;

        if (left > right) {
            return -1;
        }

        if (value == arr[mid1]) {
            return mid1;
        }

        if (value == arr[mid2]) {
            return mid2;
        }

        if (value > arr[mid2]) {
            return ternarySearch(arr, value, mid2 + 1, right);
        } else if (value < arr[mid1]) {
            return ternarySearch(arr, value, left, mid1 - 1);
        }

        return ternarySearch(arr, value, mid1 + 1, mid2 - 1);
    }
}
