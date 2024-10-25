package stringAlgorithms;

import java.util.*;

public class StringAlgorithms {


    public int countVowels(String str) {
        int count = 0;

        Map<Character, Integer> vowels = new HashMap<>();
        vowels.put('a', 1);
        vowels.put('e', 1);
        vowels.put('i', 1);
        vowels.put('o', 1);
        vowels.put('u', 1);

        for (char ch : str.toCharArray()) {
            if (vowels.containsKey(ch)) {
                count++;
            }
        }

        return count;
    }


    public String reverseString(String str) {
        if (isEmpty(str)) {
            return "";
        }
        StringBuilder reversedString = new StringBuilder();
        Stack<Character> chars = new Stack<>();

        for (char ch : str.toCharArray()) {
            chars.push(ch);
        }

        while (!chars.isEmpty()) {
            reversedString.append(chars.pop());
        }

        return reversedString.toString();
    }

    public String reverseWords(String str) {
        if (isEmpty(str)) {
            return "";
        }
        String[] splitWords = str.trim().split(" ");
        StringBuilder reversedString = new StringBuilder();
        for (int i = splitWords.length - 1; i >= 0; i--) {
            reversedString.append(splitWords[i] + " ");
        }

        return reversedString.toString().trim();
    }

    public String reverseWords2(String str) {
        if (isEmpty(str)) {
            return "";
        }
        String[] splitWords = str.trim().split(" ");
        Collections.reverse(Arrays.asList(splitWords));

        return String.join(" ", splitWords);
    }

    // ABCD -> DABC
    //      -> CDAB
    //      -> BCDA
    //      -> ABCD
    public boolean areRotations(String str1, String str2) {
        if (str1.length() != str2.length()) {
            return false;
        }

        String concatanatedString = str1 + str1;

        return concatanatedString.contains(str2);
    }

    public String removeDuplicates(String str) {
        if (isEmpty(str)) {
            return "";
        }

        Set<Character> set = new HashSet<>();
        StringBuilder newString = new StringBuilder();
        String input = str.trim().toLowerCase();
        for (char ch : input.toCharArray()) {
            if (!set.contains(ch)) {
                set.add(ch);
                newString.append(ch);
            }
        }

        return newString.toString();
    }

    public char mostRepeatedChar(String str) {
        if (isEmpty(str)) {
            throw new IllegalArgumentException();
        }

        String input = str.trim().toLowerCase();
        Map<Character, Integer> map = new HashMap<>();

        for (char ch : input.toCharArray()) {
            if (Character.getNumericValue(ch) >= 10 && Character.getNumericValue(ch) <= 35) {
                map.put(ch, map.getOrDefault(ch, 0) + 1);
            }
        }

        Map.Entry<Character, Integer> mostRepeatedChar = null;
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            if (mostRepeatedChar == null || entry.getValue() > mostRepeatedChar.getValue()) {
                mostRepeatedChar = entry;
            }
        }

        assert mostRepeatedChar != null;
        return mostRepeatedChar.getKey();
    }

    public char mostRepeatedChar2(String str) {
        if (isEmpty(str)) {
            throw new IllegalArgumentException();
        }

        final int ASCII_SIZE = 256;
        int[] frequencies = new int[ASCII_SIZE];

        for (char ch : str.toCharArray()) {
            frequencies[ch]++;
        }

        int max = 0;
        char result = ' ';
        for (int i = 0; i < frequencies.length; i++) {
            if (frequencies[i] > max) {
                max = frequencies[i];
                result = (char) i;
            }
        }

        return result;

    }

    private boolean isEmpty(String str) {
        return str == null || str.isEmpty();
    }

    public String capitalize(String words) {
        if (isEmpty(words) || words.trim().isEmpty()) {
            return "";
        }

        String[] wordsArray = words
                .trim()
                .replaceAll(" +", " ")
                .split(" ");
        StringBuilder newWords = new StringBuilder();


        for (String word : wordsArray) {
            newWords.append(Character.toUpperCase(word.charAt(0)) + word.substring(1).toLowerCase() + " ");
        }

        return newWords.toString().trim();
    }

    public boolean areAnagrams(String first, String second) {
        if (first == null || second == null) {
            throw new IllegalArgumentException();
        }
        if (first.isEmpty() && second.isEmpty()) {
            return true;
        }
        String firstInput = first.trim().toLowerCase();
        String secondInput = second.trim().toLowerCase();

        if (firstInput.length() != secondInput.length()) {
            return false;
        }

        char[] arr1 = firstInput.toCharArray();
        char[] arr2 = secondInput.toCharArray();

        Arrays.sort(arr1);
        Arrays.sort(arr2);

        return Arrays.equals(arr1, arr2);
    }

    public boolean areAnagrams2(String first, String second) {
        if (first == null || second == null) {
            return false;
        }

        final int ENGLISH_ALPHABET = 26;
        int[] frequencies = new int[ENGLISH_ALPHABET];

        first = first.toLowerCase();
        for (int i = 0; i < first.length(); i++) {
            frequencies[first.charAt(i) - 'a']++;
        }

        second = second.toLowerCase();
        for (int i = 0; i < second.length(); i++) {
            int index = second.charAt(i) - 'a';
            if (frequencies[index] == 0) {
                return false;
            }
            frequencies[index]--;
        }


        return true;
    }

    public boolean areAnagrams3(String first, String second) {
        if (first == null || second == null) {
            return false;
        }

        Map<Character, Integer> map = new HashMap<>();
        first = first.toLowerCase();
        second = second.toLowerCase();

        if (first.length() != second.length()) {
            return false;
        }

        for (int i = 0; i < first.length(); i++) {
            map.put(first.charAt(i), map.getOrDefault(first.charAt(i), 0) + 1);
        }

        for (int i = 0; i < second.length(); i++) {
            if (map.get(second.charAt(i)) == 0) {
                return false;
            }
            map.put(second.charAt(i), map.get(second.charAt(i)) - 1);
        }

        return true;
    }

    public boolean arePalindrome(String word) {
        if (word == null) {
            return false;
        }

        if (word.isEmpty()) {
            return true;
        }

        String reversedString = new StringBuilder(word).reverse().toString();

        return reversedString.equals(word);
    }

    public boolean arePalindrome2(String word) {
        if (word == null) {
            return false;
        }

        if (word.isEmpty()) {
            return true;
        }

        int leftIndex = 0;
        int rightIndex = word.length() - 1;

        while (leftIndex < rightIndex) {
            if (word.charAt(leftIndex++) != word.charAt(rightIndex--)) {
                return false;
            }
        }

        return true;
    }
}
