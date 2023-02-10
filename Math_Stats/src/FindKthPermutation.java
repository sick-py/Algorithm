import java.util.ArrayList;
import java.util.List;

public class FindKthPermutation {
    /**
     * Let’s discuss a few basics first. We know that n! is the number of permutations for a set of size n
     * . Another obvious and important concept is that if we choose an element for the first position, then the total permutations of the remaining elements are (n−1)!

     * For example, if we are given the elements {1, 2, 3, 4}, and we pick 1 as our first element, then for the remaining elements, we have the following permutations: it's equal to 6
     *
     * We can do better, if we look closely at the diagram above. If we are given k
     *  and we somehow guess which block it’s going to lie in, that will help us find at least the first element.
     * Similarly, within that block, if we can identify a sub-block where
     * �
     * k
     *  resides, it will help us find the second element. We can do this recursively until we run out of options. Here is a visual representation of this approach if k=8
     * */
    void findK(List<Character> v, int k, StringBuilder res, List<Integer> factor) {
        if (v.isEmpty()) return;
        int n = v.size();

        // factorial is number of permutations starting with first digit,
        // selected is the number of digits in permutation
        int selected = (k - 1) / factor.get(n - 1);

        res.append(v.get(selected));
        v.remove(selected);
        k = k - (factor.get(n - 1) * selected);

        findK(v, k, res, factor);
    }

    String getPermutation(int n, int k) {
        List<Character> v = new ArrayList<>();

        char c = '1';
        for (int i = 1; i <= n; i++) {
            v.add(c);
            c++;
        }

        //find factor and store it
        List<Integer> factor = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (i == 0 || i == 1) {
                factor.add(1);
            } else {
                factor.add(i * factor.get(i - 1));
            }
        }

        StringBuilder res = new StringBuilder();
        findK(v, k, res, factor);
        return res.toString();
    }
}
