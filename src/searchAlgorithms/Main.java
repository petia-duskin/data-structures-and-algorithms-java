package searchAlgorithms;

public class Main {
    public static void main(String[] args) {
        int[] arr = {3, 5, 6, 9, 11, 18, 20, 21, 24, 30};
        Search search = new Search();
        System.out.println(search.ternarySearch(arr, 18));
    }
}
