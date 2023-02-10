import java.util.ArrayList;
import java.util.List;

public class FindPalindromeSubstrings {
    /**
     * Given a string, find all non-single letter substrings that are palindromes.
     *
     * A palindrome is a word, phrase, number, or other sequence of characters that reads the same backwards as it reads forwards.
     *
     * We can reduce the time complexity of the first solution to n ^ 2 to n ^ 3 using the following approach.
     *
     * For each letter in the input string, start expanding to the left and right while checking for even and odd length palindromes. If we know a palindrome doesn’t exist, move to the next letter.
     *
     * We expand one character to the left and right and compare them. If both characters are equal, we print out the palindrome substring.
     * */
    void find(String input, int j, int k, List<String> palindrome) {
        for (; j >= 0 && k < input.length(); j--, k++) {
            if (input.charAt(j) != input.charAt(k)) {
                break;
            }
            palindrome.add(input.substring(j, k + 1));
        }
    }

    List<String> findAll(String input) {
        List<String> res = new ArrayList<>();
        for (int i = 0; i < input.length(); i++) {
            find(input, i - 1, i + 1, res);
            find(input, i, i + 1, res);
        }
        return res;
    }
}
