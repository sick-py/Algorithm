import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;

public class ArrangeTheLast {
    /**
     * Given an array of integers, find the largest number that can be made by creating all possible permutations of these integers.
     *
     * As the largest number formed can be very large, Return a string instead of an integer.
     *
     * To solve this problem, we need to apply some sorting technique. Since we need the largest number, we must ensure that the largest numbers occupy the most significant digits.
     *
     * We need to return a string as the created number can be very large. We start by converting all the elements of the array from integers to strings.
     *
     * Now, we apply a sorting technique so we could create the largest number out of a sorted array.
     * One may want to sort these numbers in descending order and then return the resultant string formed by this sorted array. But that is not always valid.
     *
     * Let’s suppose we have the following array:
     * 71, 5, 21, 52
     *
     * 71, 52, 21, 5
     *
     * but the largest is 7155221
     *
     * During the sort, for each pairwise comparison, we compare the strings formed by concatenating the two strings in the pair in both orders. This enables the determination of the larger value of the two possible pairwise concatenations:
     * To prove that we can form a proper order by these comparisons, let’s assume that for two integers m and n
     *  our comparator tells us that in the sorted order, mn
     * . This means that concatenate(m, n) > concatenate(n, m)
     * .
     * For the sort to produce an incorrect result, there must be some o n
     * . That is, there is some o for which concatenate(n, o) > concatenate(o, n)
     * and concatenate(o,m)>concatenate(m,o)
     * .
     * This results in a contradiction as concatenate(m, n) > concatenate(n, m)
     *  and concatenate(n, o) > concatenate(o, n)
     *  implies that concatenate(m, o) > concatenate(o, m)
     *
     *  It means that our comparator maintains transitivity, so our sort is correct.
     *
     *  Once we have sorted the array, we check if the first index of our sorted arrays contains 0 or not. If it does, we return 0 as our sorted array is one of these types of arrays, such as {0,0}, {0,0,0}, {0,0,0,0} and so on. If it contains a non-zero element, we create a string out of this sorted array and return it.
     *
     *  Convert the input numbers from integer to string.
     * Sort these numbers based on the comparator.
     * If our sorted array starts with 0
     * , return 0
     * . Otherwise, return the string.
     *  .
     * */

    private static class compareLarge implements Comparator<String> {
        public int compare(String a, String b) {
            String o1 = a + b;
            String o2 = b + a;
            return o2.compareTo(o1);
        }
    }

    public static String largest(int[] nums) {
        String[] numStr = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            numStr[i] = String.valueOf(nums[i]);
        }

        Arrays.sort(numStr, new compareLarge());
        // We return 0 if we get strings like:
        // "00", "000".
        if (numStr[0].equals("0")) {
            return "0";
        }

        String res = new String();
        for (String s : numStr) {
            res += s;
        }
        return res;
    }
}
