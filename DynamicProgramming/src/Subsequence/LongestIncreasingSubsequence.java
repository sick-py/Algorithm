package Subsequence;

import java.util.Arrays;
import java.util.Comparator;

public class LongestIncreasingSubsequence {
    /**
     *Input an unordered integer array, please find the length of the longest strictly increasing subsequence, the function signature is as follows:
     * Say for example the input nums=[10,9,2,5,3,7,101,18], where the longest increasing subsequence is  [2,3,7,101], so the output of the algorithm should be 4.
     *
     * Just take the problem of the longest increasing subsequence as an example and you will understand. However, we must first define the meaning of the dp array, that is, what does dp[i]the value of represent?
     * Our definition is this: dp[i]Indicates the length of the longest increasing subsequence ending with nums[i]this number.
     *
     * According to this definition, we can introduce the base case: the dp[i]initial value is 1, because the longest increasing subsequence nums[i]ending with should at least contain itself.
     * nums: 1, 4, 3, 4, 2, 3
     * dp: 1, 2, 2, 3, 2, ?
     *
     * According to our definition of the dparray , now we want to find dp[5]the value of , that is, we want to nums[5]find the longest increasing subsequence ending with .
     *
     * nums[5] = 3, since it is an increasing subsequence, we only need to find the previous subsequences whose endings are smaller than 3, and then connect 3 to the end of these subsequences to form a new increasing subsequence, and the length of this new subsequence is increased by one.
     * */

    int LIS(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] < nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }

        int res = 0;
        for (int i = 0; i < n; i ++) {
            res = Math.max(res, dp[i]);
        }
        return res;
    }

    class review {
        int Longest(int[] nums) {
            int n = nums.length;
            int[] dp = new int[n];
            Arrays.fill(dp, 1); //here
            for (int i = 1; i < n; i++) {
                for (int j = 0; j < i; j++) {
                    if (nums[j] < nums[i]) {
                        dp[i] = Math.max(dp[i], dp[j] + 1);
                    }
                }
            }

            int res = 0;
            for (int i = 0; i < n; i++) {
                res = Math.max(dp[i], res);
            }
            return res;
        }
    }
    /** expand to two dimensions  Matryoshka envelope problem
     * give you a 2 dimensions envelopes, envelops[i] = [wi, hi] indicate the width and height of the envelope, at max how many envelopes can put together
     *
     * This question is actually a variant of the longest increasing subsequence, because each legal nesting is a large nest of small ones, which is equivalent to finding a longest increasing subsequence in a two-dimensional plane. Set of envelopes .
     *
     * First sort the width win ascending order, and if you encounter wthe same situation, sort in hdescending ; then use all h as an array, and calculate the length of LIS on this array is the answer .
     *
     * First, sort the width wfrom small to large to ensure that wthis dimension can be nested with each other, so we only need to focus on the height. hThis dimension can be nested with each other.
     *
     * Secondly, two identical w envelopes cannot contain each other, so for envelopes with the same width, sort the height hin descending order to ensure that there are no multiple widentical envelopes in LIS (because the title says that the same length and width cannot be nested).
     * */
    public int max(int[][] envelopes) {
        int n = envelopes.length;
        Arrays.sort(envelopes, new Comparator<int[]>() {
            public int compare(int[] a, int[] b) {
                return a[0] == b[0] ? b[1] - a[1] : a[0] - b[0];
            }
        });

        int[] height = new int[n];
        for (int i = 0; i < n; i++) {
            height[i] = envelopes[i][1];
        }
        return lis(height);
    }

    //dp[i] the longest increasing subsequent from list end with i
    private int lis(int[] height) {
        int n = height.length;
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (height[j] > height[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }

        int res = 0;
        for (int i = 0; i < n; i++) {
            res = Math.max(res, dp[i]);
        }
        return res;
    }
}
