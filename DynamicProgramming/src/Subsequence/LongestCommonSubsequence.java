package Subsequence;

import java.util.Arrays;

public class LongestCommonSubsequence {
    /** refine a big problem to a point, and use recursion/iteration extend to whole problem
     *
     *  don’t know how you feel about doing algorithmic questions. I have concluded that the skill of doing algorithmic questions is to refine the big problem to a point, first study how to solve the problem on this small point, and then use recursion/iteration extended to the whole problem.
     *
     * For example, our previous article Take you hand in hand to brush the third phase of the binary tree, To solve the problem of the binary tree, we will refine the whole problem to a certain node, imagine ourselves standing on a certain node, what needs to be done, and then set the binary tree recursive framework.
     *
     * The same is true for dynamic programming series problems, especially those related to subsequences. This article starts from the "longest common subsequence problem", summarizes the three subsequence problems , solves this problem and carefully explains the routine of this subsequence problem, and you can feel this way of thinking.
     * */

    /**
     * Give you two strings s1 and s2, please find the longest common subsequence between them, and return the length of this subsequence. The function signature is as follows:
     * The definition of this dp function is dp(s1, i, s2, j) to s1[i..]calculate s2[j..]the length of the longest common subsequence.
     *
     * According to this definition, the answer we want is dp(s1, 0, s2, 0), and the base case is i == len(s1)or j == len(s2), because at this time s1[i..]or s2[j..]is equivalent to an empty string, and the length of the longest common subsequence is obviously 0:
     *
     * Next, let's not look s1at s2the two strings of and , but to be specific to each character and think about what each character should do .
     * We only look at s1[i]and s2[j], if s1[i] == s2[j], it means that this character must be in lcs,
     * s1[i] != s2[j]what should I do if it happens?
     * s1[i] != s2[j]means, s1[i]and s2[j]have at least one character that is not lcsin :
     * */



    int[][] memo;

    int longestCommon(String s1, String s2) {
        int m = s1.length(), n = s2.length();

        memo = new int[m][n];
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }
        return dp(s1, 0, s2, 0);
    }

    private int dp(String s1, int i, String s2, int j) {
        if (i == s1.length() || j == s2.length()) {
            return 0;
        }

        if (memo[i][j] != -1) {
            return memo[i][j];
        }

        if (s1.charAt(i) == s2.charAt(j)) {
            memo[i][j] = 1 + dp(s1, i - 1, s2, j - 1);//s[i] == s[j] and in the lcs
        } else {
            memo[i][j] = max(
                    dp(s1, i + 1, s2, j), //s1[i] is not in lcs
                    dp(s1, i, s2, j + 1), //s2[j] is not in lcs
                    dp(s1, i + 1, s2, j + 1)
            );//all not in lcs
        }
        return memo[i][j];
    }
    int max(int a, int b, int c) {
        return Math.max(a, Math.max(b, c));
    }

    /**
     * dp[i][j] the length of LCS of s1(0 .. i) and s2(0 .. j)
     *
     * */
    public int longestCommonSubsequence(String text1, String text2) {
        int n1 = text1.length(), n2 = text2.length();
        int[][] dp = new int[n1 + 1][n2 + 1];
        for (int i = 1; i <= n1; i++) {
            for (int j = 1; j <= n2; j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        for (int i = 0; i <= n1; i++) {
            for (int j = 0; j <= n2; j++) {
                System.out.printf(" %d ", dp[i][j]);
            }
            System.out.printf("\n");
        }
        return dp[n1][n2];
    }

    /** String delete operation
     * Given two words s1and s2, s1return s2the minimum number of steps required to make and equal. Each step can delete one character in any string.
     * The function signature is as follows:
     *
     * int minDistance(String s1, String s2);
     * For example, input s1 = "sea" s2 = "eat", the algorithm returns 2, the first step will "sea"become "ea", and the second step will "eat"become "ea".
     *
     * Question Let us calculate the minimum number of deletions to make the two strings the same, then we can think about what will these two strings be deleted in the end?
     *
     * Isn't the result of deletion the longest common subsequence of the two!
     *
     * Then, to calculate the number of deletions, it can be derived from the length of the longest common subsequence:
     * */
    int minDel(String s1, String s2) {
        int lcs = longestCommon(s1, s2);
        return s1.length() - lcs + s2.length() - lcs;
    }

    /** The minimum ASCII deletion sum of two strings
     * For example, input s1 = "sea", s2 = "eat", the algorithm returns 231.
     *
     * "sea"Because deleting "s"in "eat"and deleting "t"in can make the two strings equal, and the sum of the ASCII codes of the deleted characters is the smallest, ie s(115) + t(116) = 231.
     *
     * This question cannot directly reuse the function of calculating the longest common subsequence, but you can write the solution code directly by slightly modifying the base case and state transition part according to the previous idea :
     * */

    int mini(String s1, String s2) {
        int m = s1.length(), n = s2.length();
        memo = new int[m][n];
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }
        return dp1(s1, 0, s2, 0);
    }

    //delete s1[i..] and s2[j..] into the same string, and return The smallest sum of ASCII codes is dp(s1, i, s2, j).
    private int dp1(String s1, int i, String s2, int j) {
        int res = 0;
        if (i == s1.length()) {
            //if s1 is at the end, all the char in s2 should be delete
            for (; j < s2.length(); j++) {
                res += s2.charAt(j);
            }
            return res;
        }

        if (j == s2.length()) {
            for (; i < s1.length(); i++) {
                res += s1.charAt(i);
            }
            return res;
        }

        if (memo[i][j] != -1) {
            return memo[i][j];
        }

        if (s1.charAt(i) == s2.charAt(j)) {
            memo[i][j] = dp1(s1, i + 1, s2, j + 1);
        } else {
            memo[i][j] = min(
                    //there are at least one
                    s1.charAt(i) + dp1(s1, i + 1, s2, j),
                    s2.charAt(j) + dp1(s1, i, s2, j + 1),
                    s1.charAt(i) + s2.charAt(j) + dp1(s1, i + 1, s2, j + 1)
            );
        }
        return memo[i][j];
    }

    int min(int a, int b, int c) {
        return Math.min(a, Math.min(b, c));
    }


    class review {
        int[][] memo;
        public int longestCommonSubsequence(String text1, String text2) {
            int m = text1.length(), n = text2.length();
            memo = new int[m][n];
            for (int[] l : memo) {
                Arrays.fill(l, -1);
            }
            return dp2(text1, text2, 0, 0);
        }

        //return the lcs of t1[i, ..m - 1] and t2[j,...n - 1]
        private int dp2(String text1, String text2, int i, int j) {
            int m = text1.length(), n = text2.length();
            if (i >= m || j >= n) {
                return 0;
            }
            if (memo[i][j] != -1) {
                return memo[i][j];
            }

            if (text1.charAt(i) == text2.charAt(j)) {
                memo[i][j] = 1 + dp2(text1, text2, i + 1, j + 1);
                return memo[i][j];
            } else {
                memo[i][j] = max(
                        dp2(text1, text2, i + 1, j),
                        dp2(text1, text2, i, j + 1),
                        dp2(text1, text2, i + 1, j + 1)
                );
            }
            return memo[i][j];
        }
    }
}
