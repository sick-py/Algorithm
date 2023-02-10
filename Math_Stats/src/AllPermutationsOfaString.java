import java.util.ArrayList;
import java.util.List;

public class AllPermutationsOfaString {
    /**Given a string, print all possible permutations of the string.
     *
     * */
    void swap(char[] input, int i, int j) {
        char temp = input[i];
        input[i] = input[j];
        input[j] = temp;
    }

    void permutation(char[] input, int currentIndex, List<String> res) {
        if (input.length == 0) {
            String addInput = "";
            res.add(addInput);
            return;
        }

        if (currentIndex == input.length - 1) {
            String addInput = new String(input);
            res.add(addInput);
            return;
        }

        for (int i = currentIndex; i < input.length; i++) {
            swap(input, currentIndex, i);
            permutation(input, currentIndex + 1, res);
            swap(input, currentIndex, i);
        }
    }

    List<String> per(String input) {
        List<String> res = new ArrayList<>();
        permutation(input.toCharArray(), 0, res);
        return res;
    }
}
