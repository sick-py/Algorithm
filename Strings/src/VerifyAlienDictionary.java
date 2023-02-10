import java.util.HashMap;
import java.util.Map;

public class VerifyAlienDictionary {
    /**
     * Given a vector of words written in an alien language and the order of their alphabet, return true only if the given words are sorted in ascending lexicographical order in the alien language.
     *
     * Given the standard English language ordering of letters, “cap” is lexicographically smaller than “cat”, because “p” appears before “t” in the alphabet. Similarly, “aleph” is lexicographically smaller than “alpha.”
     *
     * We can assume the following constraints:
     *
     * The maximum length of the dictionary is 100 words.
     * The maximum length of a word is 20 characters.
     * Length of the alien alphabet is 26 characters.
     * All characters in words[i] and order are English lowercase letters.
     *
     * Solution:
     * To verify the integrity of the dictionary, we can check the adjacent words to see if they are in the correct order or not. For each word, the word on its right should be lexicographically larger, and the one on its left should be lexicographically smaller.
     *
     * One thing to notice here is that we do not need to compare all the words. We can just compare each pair of adjacent words instead. If all the pairs of adjacent words are in order, we can safely assume that the entire list is correctly sorted. Conversely, if any of the pairs of adjacent words are not in order, the list is not correctly sorted.
     *
     * One of the methods to compare the adjacent words would be to iterate over the words individually. But, before we execute a naive loop for that, we need an efficient way to store the order of each letter and its ranking provided in the dictionary. Once we do that, we can move on to the comparison part. To compare two adjacent words, words[i] and words[i + 1], we iterate over the letters one by one and find the first index where the letter in both the words is different. If words[i] has a smaller letter than the corresponding one in words[i + 1], we can break this iteration because we know that these two words are in the right order.
     *
     * If words[i] has a lexicographically larger letter, we can immediately return false, as we have found a pair of consecutive words that are not in the correct order.
     *
     * We also need to consider boundary conditions. When we iterate over a word, we need to ensure that the other word has not ended. Considering one of the examples, “educated” and “educate”, we can not iterate over all the letters of “educated” because the word “educate” is shorter.
     *
     * In this case, we must examine the length of each word: if the words are the same length or the former word is shorter, then the words vector is sorted. However, if the latter word is shorter, then the words vector is not sorted.
     *
     * After an overview of the solution, we can move on to the actual implementation:
     *
     * Initialize a hashmap to record the relations between each letter and its ranking in the dictionary.
     *
     * Iterate over words and compare each pair of adjacent words.
     *
     * Find the first index in two consecutive words (words[i] and words[i + 1]) where the letter in the two words is different.
     *
     * If words[i + 1] ends before words[i] and no different letters are found, then we return false because words[i + 1] should come before words[i].
     *
     * If we find the first different letter and the two words are in the correct order, then we can exit from the current iteration and proceed to the next pair of words.
     *
     * If we find the first different letter and the two words are in the wrong order, then we can safely return false.
     *
     * Once we reach the end of the outer loop, it means that we have examined all pairs of adjacent words and that they are all sorted. Therefore, we can return true.
     * */

    boolean verify(String[] words, String order) {
        if (words.length == 1) {
            return true;
        }

        Map<Character, Integer> keyMap = new HashMap<>();
        for (int i = 0; i < order.length(); i++) {
            keyMap.put(order.charAt(i), i);
        }

        for (int i = 0; i < words.length - 1; i++) {
            for (int j = 0; j < words[i].length(); j++) {
                // If we do not find a mismatch letter between words[i] and words[i + 1],
                // we need to examine the case when words are like ("educated", "educate").
                if (j >= words[i + 1].length())
                    return false;
                if (words[i].charAt(j) != words[i + 1].charAt(j)) {
                    if (keyMap.get(words[i].charAt(j)) > keyMap.get(words[i + 1].charAt(j))) {
                        return false;
                    } else {
                        // if we find the first different character and they are sorted,
                        // then there's no need to check remaining letters
                        break;
                    }
                }
            }
        }
        return true;
    }

    boolean v(String[] words, String order) {
        if (words.length == 1) {
            return true;
        }

        Map<Character, Integer> keyMap = new HashMap<>();
        for (int i = 0; i < order.length(); i++) {
            keyMap.put(order.charAt(i), i);
        }

        for (int i = 0; i < words.length - 1; i++) {
            for (int j = 0; j < words[i].length(); j++) {
                if (j > words[i + 1].length()) {
                    return false;
                }
                if (words[i].charAt(j) != words[i + 1].charAt(j)) {
                    if (keyMap.get(words[i].charAt(j)) > keyMap.get(words[i + 1].charAt(j))) {
                        return false;
                    } else {
                        break;
                    }
                }
            }
        }
        return true;
    }
}
