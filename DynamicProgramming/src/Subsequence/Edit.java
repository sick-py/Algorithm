package Subsequence;

public class Edit {
    static int min(int a, int b, int c) {
        return Math.min(a, Math.min(b, c));
    }
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

}
