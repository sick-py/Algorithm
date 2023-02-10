import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PythagoreanTriples {
    /**
     * Given an integer array, find all Pythagorean triples (a^2 + b^2 = c^2) where a b c are the array elements.
     *
     * This solution is based on the 3SUM problem. Here is an overview of the approach where we try to find any elements matching the criteria
     * Sort the list of integers.
     * Iterate the list from start to end.
     * Select the current element as c and take its square, that is, c^2
     *  To find the other two elements, we traverse the list from the start and end simultaneously, till start < end.
     *  We can take advantage of the fact that the list is sorted. While traversing the list, we look for 3 numbers that sum up to 0 a2 + b2 - c2 = 0
     *  a2 =list[start] * list[start]
     *  b2 =list[end] * list[end]
     * */
    List<int[]> find(int[] arr) {
        int n = arr.length;
        List<int[]> triples = new ArrayList<>();

        Arrays.sort(arr);

        for (int i = 0; i < n; i++) {
            if (arr[i] == 0) continue;

            int c2 = arr[i] * arr[i];
            int j = 0, k = n - 1;
            while (j < k) {
                // If j become equal to i or value of j is equal to 0,
                //increment j and continue
                if (j == i || arr[j] == 0) {
                    j++;
                    continue;
                }

                if (k == i || arr[k] == 0) {
                    k--;
                    continue;
                }
                int a2 = arr[j] * arr[j];
                int b2 = arr[k] * arr[k];

                if (a2 + b2 == c2) {
                    triples.add(new int[] {arr[j], arr[k], arr[i]});
                }
                else if (a2 + b2 > c2) {
                    k--;
                } else {
                    j++;
                }
            }
        }
        return triples;
    }
}
