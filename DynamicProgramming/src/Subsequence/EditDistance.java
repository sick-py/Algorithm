package Subsequence;

import java.util.Arrays;

public class EditDistance {
    /**
     * The edit distance problem is to give us two strings s1 and s2, only three operations can be used, let us s1change s2to find the least number of operations. It needs to be clear that whether it is s1changed s2or vice versa, the result is the same, so the following will be s1changed into s2an example.
     *
     * to solve the dynamic programming problem of two strings, two pointers i, j are to point to the end of the two strings, and then move forward step by step to reduce the scale of the problem .
     * According to the above GIF, it can be found that there are not only three operations, but there is actually a fourth operation, which is to do nothing (skip). For example in this case:
     *
     * There is another situation that is easy to handle, that jis s2, when is finished, if has inot finished s1, then only the delete operation can be used to s1shorten to s2. For example in this case:
     * Similarly, i if you haven't s1 finished walking when you finish walking , you can only use the insert operation to insert all the remaining characters . As we will see later, these two cases are the base cases of the algorithm
     *
     * The base case is i finished s1 or j finished s2, which can directly return the remaining length of another string.
     * For each pair of characters s1[i]and s2[j], four operations are possible:
     *
     * if s1[i] == s2[j]:
     *     （skip）
     *     i, j move forward
     * else:
     *     choices：
     *         （insert）
     *         （delete）
     *         （replace）
     *
     * how to choose this "three options"? It's very simple, try it all, and choose whichever operation finally gets the smallest edit distance. Recursive skills are needed here, first look at the violent solution code:
     * */

    int minDistance0(String s1, String s2) {
        int m = s1.length(), n = s2.length();
        return dp(s1, m - 1, s2, m - 2);

    }

    private int dp(String s1, int i, String s2, int j) {
        if (i == -1) return j + 1;
        if (j == -1) return i + 1;

        if (s1.charAt(i) == s2.charAt(j)) {
            return dp(s1, i - 1, s2, j - 1);
        }
        return min(
                dp(s1, i, s2, j - 1) + 1,//insert
                dp(s1, i - 1, s2, j) + 1,//delete
                dp(s1, i - 1, s2, j - 1) + 1//replace
        );
    }

    static int min(int a, int b, int c) {
        return Math.min(a, Math.min(b, c));
    }
    /** memo
     *
     * */
    int[][] memo;

    int dp1(String s1, int i, String s2, int j) {
        if (i == -1) return j + 1;
        if (j == -1) return i + 1;
        if (memo[i][j] != -1) {
            return memo[i][j];
        }

        if (s1.charAt(i) == s2.charAt(j)) {
            memo[i][j] = dp1(s1, i - 1, s2, j - 1);
        } else {
            memo[i][j] = min(
                    dp1(s1, i, s2, j - 1) + 1,
                    dp1(s1, i - 1, s2, j) + 1,
                    dp1(s1, i - 1, s2, j - 1) + 1
            );
        }
        return memo[i][j];
    }

    /** DP solution
     * First, clarify the meaning of the dp array.
     * int dp(String s1, int i, String s2, int j)
     * // return s1[0..i] and s2[0..j] min edit distance
     *
     * dp[i+1][j+1]
     * // store s1[0..i] and s2[0..j] min edit distance
     * dp The base case of the function is i, j equal to 0, so dp the array will be offset by one.
     *
     * dpSince dpthe meaning of the array and the recursive function are the same, you can directly apply the previous ideas to write code. The only difference is that the DP table is solved from the bottom up, and the recursive solution is solved from the top down :
     * */

    static int minDistance(String s1, String s2) {
        int m = s1.length(), n = s2.length();
        int[][] dp = new int[m + 1][n + 1];
        // base case
        for (int i = 1; i <= m; i++)
            dp[i][0] = i;
        for (int j = 1; j <= n; j++)
            dp[0][j] = j;
        // 自底向上求解
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (s1.charAt(i-1) == s2.charAt(j-1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = min(
                            dp[i - 1][j] + 1,
                            dp[i][j - 1] + 1,
                            dp[i - 1][j - 1] + 1
                    );
                }
            }
        }

        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                System.out.printf("%d ", dp[i][j]);
            }
            System.out.printf("\n");
        }
        // 储存着整个 s1 和 s2 的最小编辑距离
        return dp[m][n];
    }

    public static void main(String[] args) {
        String s1 = "cats", s2 = "fast";
        minDistance(s1, s2);
    }

    /** how to backtrack the path
     * Generally speaking, the dynamic programming problem of dealing with two strings is handled according to the ideas in this article, and the DP table is established. Why, because it is easy to find out the relationship between state transitions, such as the DP table of the edit distance:
     *
     * You may also ask, only the minimum edit distance is found here, so what is the specific operation ? In the example of modifying the official account article you gave before, only a minimum editing distance is definitely not enough, and you need to know how to modify it.
     // int[][] dp;
     Node[][] dp;

     class Node {
     int val;
     int choice;
     // 0 do nothing
     // 1 insert
     // 2 delete
     // 3 replace
     }

     valThe attribute is the value of the previous dp array, and the choiceattribute represents the operation. When making the optimal choice, record the operation by the way, and then deduce the specific operation from the result.

     dp[m][n]Is n’t our final result , where the valminimum edit distance is choicestored, and the last operation, such as an insert operation, can be moved to the left by one space:
     By repeating this process, you can go back to the starting point step by step dp[0][0], forming a path, and editing according to the operations on this path is the best solution.

     * */

    class review {
        int[][] memory;
        int Edit(String s1, String s2) {
            int m = s1.length(), n = s2.length();
            memory = new int[m][n];
            Arrays.fill(memory, -1);
            return dp(s1, m - 1, s2, n - 1);
        }
        int dp(String s1, int i, String s2, int j) {
            if (i == -1) {
                return j + 1;
            }
            if (j == -1) {
                return i + 1;
            }

            if (memory[i][j] != -1) {
                return memory[i][j];
            }

            if (s1.charAt(i) == s2.charAt(j)) {
                memory[i][j] = dp(s1, i - 1, s2, j - 1);
            } else {
                memory[i][j] = min(dp(s1, i, s2, j - 1),
                        dp(s1, i - 1, s2, j),
                        dp(s1, i - 1, s2, j - 1)) + 1;
            }

            return memo[i][j];
        }
    }


}
