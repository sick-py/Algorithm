package Subsequence;

public class main {
    /***
     * First of all, the subsequence problem itself is more difficult than substrings and subarrays, because the former is a discontinuous sequence, while the latter two are continuous, even if you exhaustively do not necessarily know it, let alone solve related algorithm problems .
     * Moreover, the subsequence problem is likely to involve two strings, such as the longest common subsequence mentioned above. If you don’t have certain processing experience, it’s really not easy to figure it out. So this article is going to pick up the routine of the sub-sequence problem. In fact, there are two templates. As long as you think about the relevant problems in these two ways, you will be sure.
     *
     * Generally speaking, this kind of question asks you to find the longest subsequence , because the shortest subsequence is just one character, there is nothing to ask. Once the subsequence and the most value are involved, it is almost certain that the dynamic programming technique is examined, and the time complexity is generally O(n^2) .
     * The reason is simple, if you think about a string, how many possible subsequences are there? At least it is exponential. In this case, without dynamic programming skills, what else do you want?
     * Since dynamic programming is to be used, it is necessary to define dpan array and find the state transition relationship. The two thinking templates we are talking about are the definition thinking of dparrays . Different problems may require different dparray definitions to solve.
     */

    /** one-dimensional dp array:
     * arr[0..i]In the subarray dp[i] , the length of the subsequence (longest increasing subsequence) we require is .
     *
     * int n = array.length;
     * int[] dp = new int[n];
     *
     * for (int i = 1; i < n; i++) {
     *     for (int j = 0; j < i; j++) {
     *         dp[i] = most(dp[i], dp[j] + ...)
     *     }
     * }
     *
     * For example, the longest increasing subsequence and the largest subarray sum we have written are all of this idea.
     * */

    /** Two-dimensional dp array:
     * int n = arr.length;
     * int[][] dp = new dp[n][n];
     *
     * for (int i = 0; i < n; i++) {
     *     for (int j = 0; j < n; j++) {
     *         if (arr[i] == arr[j])
     *             dp[i][j] = dp[i][j] + ...
     *         else
     *             dp[i][j] = most(...)
     *     }
     * }
     * This kind of thinking is used relatively more, especially when it involves subsequences of two strings/arrays, such as the longest common subsequence and edit distance mentioned above ; this kind of thinking can also be used when only one string/array is involved scenarios, such as the palindrome subsequence problem discussed in this article.
     * */

    /** 2.1 For scenarios involving two strings/arrays , dpthe definition of the array is as follows:
     In subarray arr1[0..i]and subarray arr2[0..j], the length of the subsequence we require isdp[i][j] .
     2.2 For scenarios involving only one string/array , dpthe definition of the array is as follows:
     array[i..j]In the subarraydp[i][j] , the length of the subsequence we require is .

     * The longest palindromic subsequence
     * Say for input s = "aecda", the algorithm returns 3, because the longest palindromic subsequence is "aca", with a length of 3.
     * Our definition of the dparray is: In the s[i..j]substringdp[i][j] , the length of the longest palindromic subsequence is . It is important to keep this definition in mind to understand the algorithm.
     *
     * Say for input s = "aecda", the algorithm returns 3, because the longest palindromic subsequence is "aca", with a length of 3.
     * Our definition of the dparray is: In the s[i..j]substringdp[i][j] , the length of the longest palindromic subsequence is . It is important to keep this definition in mind to understand the algorithm.
     *
     * Specifically, if we want to ask dp[i][j], assuming you know dp[i+1][j-1]the ( s[i+1..j-1]the length of the longest palindrome subsequence in ), can you find a way to calculate dp[i][j]the value of ( s[i..j]the length of the longest palindrome subsequence in )?
     * Can! It depends s[i]on s[j]the characters of and :
     * If they are equal , then they s[i+1..j-1]plus the longest palindromic subsequence in is s[i..j]the longest palindromic subsequence of :
     * If they are not equal , it means that they cannot appear s[i..j]in the longest palindrome subsequence at the same time, then adds[i+1..j-1] them to , and see which substring produces a longer palindrome subsequence:
     *
     * if (s[i] == s[j])
     *     dp[i][j] = dp[i + 1][j - 1] + 2;
     * else
     *     // s[i+1..j] and s[i..j-1] who is longer
     *     dp[i][j] = max(dp[i + 1][j], dp[i][j - 1]);
     * So far, the state transition equation has been written out. According to the definition of the dp array, what we require is the length ofdp[0][n - 1] the entire longest palindrome subsequence.s
     * First, clarify the base case. If there is only one character, obviously the length of the longest palindrome subsequence is 1, that is dp[i][j] = 1 (i == j).
     * Because imust be less than or equal to j, so for i > jthose positions, there is no subsequence at all, and should be initialized to 0.
     * In addition, look at the state transition equation just written. If you want to find, you dp[i][j]need to know the three positions dp[i+1][j-1], dp[i+1][j], dp[i][j-1]and then look at the base case we determined. After filling in the dparray like this:
     * In order to ensure each calculation dp[i][j], the position in the left, lower and right directions has been calculated, and can only be traversed obliquely or reversely :
     * */
    int longP(String s) {
        int n = s.length();
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            dp[i][i] = 1;
        }

        for (int i = n - 1; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                } else {
                    dp[i][j] = Math.max(
                            dp[i + 1][j],
                            dp[i][j - 1]
                    );
                }
            }
        }
        return dp[0][n - 1];
    }

    /** For example, Likou question 1312 "Calculate the minimum number of insertions to make a string a palindrome":

     Enter a string s, you can insert any character anywhere in the string. If you want to sturn into a palindrome, please calculate the minimum number of insertions?
     *
     * For example, input s = "abcea", the algorithm returns 2, because you can sinsert 2 characters into a palindrome "abeceba"or "aebcbea". If entered s = "aba", the algorithm returns 0, because sis already a palindrome, no need to insert any characters.
     *
     * This is also a subsequence problem of a single string, so we can also use a two-dimensional dparray , where dp[i][j]is defined as follows:
     *
     * For a string s[i..j], dp[i][j]at insertions are required to become a palindrome .
     *
     * According to the definition of the dparray , the base case is dp[i][i] = 0, because a single character itself is a palindrome string and does not need to be inserted.
     *
     * Then use mathematical induction, assuming that dp[i+1][j-1]the , think about how to derive dp[i][j]the value of :
     * In fact, it is very similar to the state transition equation of the longest palindrome subsequence problem. There are also two cases here:
     *
     * if (s[i] == s[j]) {
     *     // no need
     *     dp[i][j] = dp[i + 1][j - 1];
     * } else {
     *     // turn s[i+1..j] and s[i..j-1] choose the minimum
     *     // and insert a s[i] or s[j]，to transfer s[i..j]
     *     dp[i][j] = min(dp[i + 1][j], dp[i][j - 1]) + 1;
     * }
     * */

    int min(String s) {
        int n = s.length();
        int[][] dp = new int[n][n];

        for (int i = n - 1; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i + 1][j - 1];
                } else {
                    dp[i][j] = Math.min(
                            dp[i + 1][j],
                            dp[i][j - 1]
                    ) + 1;
                }
            }
        }
        return dp[0][n - 1];
    }

    /**
     * we can also use the previous longp and minus the res, n - res
     * */
}
