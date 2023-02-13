package Main;

public class TwoDimensionToOneDimension {
    /**
     * Look at its state transition equation. If all the calculation states dp[i][j]need to be dp[i][j]adjacent states, then the space compression technique can be used to convert the two-dimensional dparray into one-dimensional , reducing the space complexity from O(N^2) to O(N).
     *
     * What is the " dp[i][j]adjacent state", such as follow
     * */

    int longestPalindromeSub(String s) {
        int n = s.length();
        int[][] dp = new int[n][n];
        //base case
        for (int i = 0; i < n; i++) {
            dp[i][i] = 1;
        }

        for (int i = n - 2; i >= 0; i--) {
            for (int j = i  + 1; j < n; j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                } else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[0][n - 1];
    }

    /**it’s okay if you don’t understand the logic of this code, and it won’t hinder you from learning space compression at all.
     * You can see that dp[i][j]our actually only depends on dp[i+1][j-1], dp[i][j-1], dp[i+1][j]these three states:
     * This is called dp[i][j]adjacent to . Anyway, you dp[i][j]only need these three adjacent states to calculate . In fact, you don’t need such a large two-dimensional dp table at all, right? The core idea of ​​space compression is to "project" a two-dimensional array to a one-dimensional array :
     *
     * The idea is very intuitive, but there is also an obvious problem. In the figure dp[i][j-1]and dp[i+1][j-1]these two states are in the same column, and only one of them can be accommodated in a one-dimensional array, so dp[i][j]when one of them must be covered by the other What should I do?
     *
     * If you want to compress a two- dpdimensional array into one dimension, generally speaking, the first dimension, that is, ithis dimension, is removed, leaving only jthis dimension. The compressed one-dimensional dparray is thedp row of the previous two-dimensional arraydp[i][..] .
     * */

    /**
     * Let's transform the above code first, directly remove i, and turn the array into one-dimensional:
     *
     * for (int i = n - 2; i >= 0; i--) {
     *     for (int j = i + 1; j < n; j++) {
     *         // what does dp[j] mean now?
     *         if (s[i] == s[j])
     *             dp[j] = dp[j - 1] + 2;
     *         else
     *             dp[j] = max(dp[j], dp[j - 1]);
     *     }
     * }
     *
     * The one-dimensional dparray can only represent dpone row dp[i][..]of the two-dimensional array, so how can I get dp[i+1][j-1], dp[i][j-1], dp[i+1][j]these necessary values ​​for state transition? we have to think about two questions:
     *
     * 1. Before dp[j]assigning a new value to , what position does it dp[j]correspond to in the two-dimensional dparray ?
     *
     * 2. What position does it dp[j-1]correspond to in the two-dimensional dparray ?
     *
     * For question 1, before dp[j]assigning a new value to , dp[j]the value of is the value calculated by the last iteration of the outer for loop, which is the dp corresponding dp[i+1][j]position in the two-dimensional array .
     * For question 2, dp[j-1]the value of is the value calculated by the last iteration of the inner for loop, which is the dpcorresponding dp[i][j-1]position in the two-dimensional array .
     *
     * Then more than half of the problem has been solved, dponly dp[i+1][j-1]the state in the two-dimensional array can not be obtained directly from the one-dimensional dparray :
     *
     * for (int i = n - 2; i >= 0; i--) {
     *     for (int j = i + 1; j < n; j++) {
     *         if (s[i] == s[j])
     *             // dp[i][j] = dp[i+1][j-1] + 2;
     *             dp[j] = ?? + 2;
     *         else
     *             // dp[i][j] = max(dp[i+1][j], dp[i][j-1]);
     *             dp[j] = max(dp[j], dp[j - 1]);
     *     }
     * }
     *
     * Because the order of for loop traversing iand jis from left to right, from bottom to top, it can be found that when updating the one-dimensional dparray , dp[i+1][j-1]it will be dp[i][j-1]overwritten . The order in which these four positions are traversed is marked in the figure:
     * Then if we want to get it dp[i+1][j-1], we must use a temporary variable tempto , and keep the value of this variable until dp[i][j]the time of calculation . In order to achieve this goal, combined with the above figure, we can write the code like this:
     * */

    int longestPalindromeSubseq(String s) {
        int n = s.length();
        // base case：一维 dp 数组全部初始化为 0
        int[] dp = new int[n];

        for (int i = n - 2; i >= 0; i--) {
            int pre = 0;
            for (int j = i + 1; j < n; j++) {
                int temp = dp[j];
                // 状态转移方程
                if (s.charAt(i) == s.charAt(j))
                    dp[j] = pre + 2;
                else
                    dp[j] = Math.max(dp[j], dp[j - 1]);
                pre = temp;
            }
        }
        return dp[n - 1];
    }

}
