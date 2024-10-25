package stringAlgorithms;

public class Main {
    public static void main(String[] args) {
        StringAlgorithms algorithms = new StringAlgorithms();
        System.out.println(algorithms.countVowels("Hello world, how are you?"));
        System.out.println(algorithms.reverseString("Hello world"));
        System.out.println(algorithms.reverseWords("Hello how are you?"));
        System.out.println(algorithms.areRotations("ABCD", "BCDA"));
        System.out.println(algorithms.removeDuplicates("AAAABccDDdOppP"));
        System.out.println(algorithms.mostRepeatedChar("Helloworld,howareyou? Are you fine?"));
        System.out.println(algorithms.capitalize("     dedeDE ded ed e  "));
        System.out.println(algorithms.areAnagrams("ABCD", "DBCA"));
        System.out.println(algorithms.areAnagrams("ABCD", "DDBCA"));
        System.out.println(algorithms.areAnagrams("ABCD", "BCA"));
        System.out.println(algorithms.areAnagrams("ABCD", "BCAA"));
        System.out.println(algorithms.areAnagrams("", ""));
        System.out.println(algorithms.arePalindrome2("ABBA"));
        System.out.println(algorithms.arePalindrome2("ABCBA"));
        System.out.println(algorithms.arePalindrome2("AAABBC"));
        System.out.println(algorithms.arePalindrome2("MADAM"));
    }
}
