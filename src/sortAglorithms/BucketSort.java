package sortAglorithms;

import java.util.*;

public class BucketSort {
    public static void main(String[] args) {
        int[] arr = {2, 3, 4, 5, 3, 7};
        bucketSort(arr, 3);
        System.out.println(Arrays.toString(arr));
    }

    public static void bucketSort(int[] arr, int numberOfBuckets) {
        Map<Integer, List<Integer>> buckets = createBucket(numberOfBuckets, arr);

        int arrIndex = 0;
        for (int i = 0; i < numberOfBuckets; i++) {
            Collections.sort(buckets.get(i));

            for (int j = 0; j < buckets.get(i).size(); j++) {
                arr[arrIndex++] = buckets.get(i).get(j);
            }
        }
    }

    private static Map<Integer, List<Integer>> createBucket(int numberOfBuckets, int[] arr) {
        Map<Integer, List<Integer>> buckets = new HashMap<>();

        for (int i = 0; i < numberOfBuckets; i++) {
            buckets.put(i, new ArrayList<>());
        }

        for (int value : arr) {
            int bucketIndex = value / numberOfBuckets;
            buckets.get(bucketIndex).add(value);
        }

        return buckets;
    }
}
