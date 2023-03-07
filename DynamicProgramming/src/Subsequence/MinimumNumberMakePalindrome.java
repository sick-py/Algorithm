package Subsequence;

public class MinimumNumberMakePalindrome {
    /**
     * First of all, the subsequence problem itself is more difficult than substrings and subarrays, because the former is a discontinuous sequence, while the latter two are continuous, even if you exhaustively enumerate, you may not be able to solve them, let alone solve related algorithm problems .
     *
     * Generally speaking, this kind of question asks you to find the longest subsequence , because the shortest subsequence is just one character, there is nothing to ask. Once the subsequence and the most value are involved, it is almost certain that the dynamic programming technique is examined, and the time complexity is generally O(n^2) .

     * Since dynamic programming is to be used, it is necessary to define an dp array and find the state transition relationship. The two thinking templates we are talking about are the definition thinking of dp arrays . Different problems may require different dparray definitions to solve.
     * 1. The first idea template is a one-dimensional dparray :
     * For example, the longest increasing subsequence and the largest subarray sum we have written are all of this idea.
     *
     * In this line of thinking the definition of dpan array is:
     * arr[0..i]In the subarraydp[i] , the length of the subsequence (longest increasing subsequence) we require is .
     *
     * 2. The template of the second idea is a two-dimensional dparray :
     * This kind of thinking is used relatively more, especially when it involves subsequences of two strings/arrays, such as the longest common subsequence and edit distance mentioned above ; this kind of thinking can also be used when only one string/array is involved scenarios, such as the palindrome subsequence problem discussed in this article.
     * 2.1 For scenarios involving two strings/arrays , dpthe definition of the array is as follows
     * In subarray arr1[0..i]and subarray arr2[0..j], the length of the subsequence we require is dp[i][j] .
     * 2.2 For scenarios involving only one string/array , dp the definition of the array is as follows:
     * array[i..j]In the subarraydp[i][j] , the length of the subsequence we require is .
     * Let's take a look at the longest palindrome subsequence problem, and explain in detail how to use dynamic programming in the second case. leetcode 516
     *
     * Say for input s = "aecda", the algorithm returns 3, because the longest palindromic subsequence is "aca", with a length of 3.
     *
     * Our definition of dpthe array is: In the substrings[i..j]dp[i][j] , the length of the longest palindromic subsequence is . It is important to keep this definition in mind to understand the algorithm.
     *
     * Why does this question define a two-dimensional dparray ? I mentioned in the longest increasing subsequence that finding a state transition requires inductive thinking. To put it bluntly, it is how to deduce the unknown part from the known result. And this definition can be summarized, and it is easy to find the state transition relationship.
     * Specifically, if we want to ask dp[i][j], assuming you know the result dp[i+1][j-1]of ( s[i+1..j-1]the length of the longest palindrome subsequence in ), can you find a way to calculate the value dp[i][j]of ( s[i..j]the length of the longest palindrome subsequence in )?
     *
     * We Can! It depends on s[i] s[j]the characters of and :
     * If they are equal , then they plus the longest palindromic subsequence s[i+1..j-1]in is s[i..j]the longest palindromic subsequence of :
     * If they are not equal , it means that they cannot appear s[i..j]in the longest palindrome subsequence at the same time, then adds[i+1..j-1] them to , and see which substring produces a longer palindrome subsequence, in fact there are 3 cases, but the [i + 1..j-1] is definitely smaller
     * The above two cases are written in code like this:
     *
     * Copy
     * if (s[i] == s[j])
     *     //
     *     dp[i][j] = dp[i + 1][j - 1] + 2;
     * else
     *     // s[i+1..j] 和 s[i..j-1] which one is longer?
     *     dp[i][j] = max(dp[i + 1][j], dp[i][j - 1]);
     *
     * Code implementation:
     * 1.Base case
     * First, clarify the base case. If there is only one character, obviously the length of the longest palindrome subsequence is 1, that is dp[i][j] = 1 (i == j).
     * In addition, look at the state transition equation just written. If you want to find, dp[i][j]you need to know the three positions dp[i+1][j-1], dp[i+1][j], dp[i][j-1]and then look at the base case we determined. After filling in dpthe array it looks like this:
     * In order to ensure each calculation dp[i][j], the position in the left, lower and right directions has been calculated, and can only be traversed obliquely or reversely :
     * I choose to traverse in reverse, the code is as follows:
     * */
    int longestPalindromeSubseq(String s) {
        int n = s.length();
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            dp[i][i] = 1;
        }

        //reversed traverse
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                }else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[0][n - 1];
    }

    /** leetcode 1312
     * Enter a string s, you can insert any character anywhere in the string. If you want to sturn into a palindrome, please calculate the minimum number of insertions?
     *
     * For a string s[i..j], at least dp[i][j]insertions to become a palindrome .
     *
     * According to the definition of dpthe array , the base case is dp[i][i] = 0, because a single character itself is a palindrome string and does not need to be inserted.
     * Then use mathematical induction, assuming that the value dp[i+1][j-1]of , think about how to derive dp[i][j]the value of :
     * if (s[i] == s[j]) {
     *     // no need to insert
     *     dp[i][j] = dp[i + 1][j - 1];
     * } else {
     *     // 把 s[i+1..j] 和 s[i..j-1] 变成回文串，选插入次数较少的
     *     // 然后还要再插入一个 s[i] 或 s[j]，使 s[i..j] 配成回文串
     *     dp[i][j] = min(dp[i + 1][j], dp[i][j - 1]) + 1;
     * }
     * */
    public int minInsertions0(String s) {
        return s.length() - longestPalindromeSubseq(s);
    }

    public int minInsertions(String s) {
        int n = s.length();
        int[][] dp = new int[n][n];
       for (int i = n - 1; i >= 0; i--) {
           for (int j = i + 1; j < n; j++) {
               if (s.charAt(i) == s.charAt(j)) {
                   dp[i][j] = dp[i + 1][j - 1];
               } else {
                   dp[i][j] = Math.min(dp[i + 1][j], dp[i][j - 1]) + 1;
               }
           }
       }
       return dp[0][n - 1];
    }
}
