import java.util.Set;

public class WordBreak {
    /**
     * We’re given a dictionary of words and an input string. Find out whether the input string can be completely segmented into the words of a given dictionary. Input string and the dictionary words will not contain spaces.
     *
     * input:
     * Words dictionary: ["apple", "pear", "pier", "pie"]
     * Input string: "applepie"
     * output:
     * True
     *
     * We can solve this problem by segmenting the large string at each possible position to see if the string can be completely segmented into words in the dictionary. If we write the algorithm in steps, it will be as follows:
     *
     * n = length of input string
     * for i = 0 to n - 1
     *   first_word = substring (input string from index [0, i] )
     *   second_word = substring (input string from index [i + 1, n - 1] )
     *   if dictionary has first_word
     *     if second_word is in dictionary OR second_word is of zero length, then return true
     *     recursively call this method with second_word as input and return true if it can be
     *     segmented
     *
     *     We can see that we may be computing the same substring multiple times, even if it doesn’t exist in the dictionary. This redundancy can be fixed by memoization, where we remember which substrings have already been solved.
     *
     * To achieve memoization, we can store the second string in a new set each time. This will reduce both time and memory complexities.
     * */

    boolean canSegment(String input, Set<String> diction) {
        for (int i = 1; i <= input.length(); i++) {
            String first = input.substring(0, i);

            if (diction.contains(first)) {
                String second = input.substring(i);
                if (input.isEmpty()) {
                    return true;
                }

                if (diction.contains(second)) {
                    return true;
                }

                if (canSegment(second, diction)) {
                    return true;
                }
            }
        }
        return false;
    }
}
