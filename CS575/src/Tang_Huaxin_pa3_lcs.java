import java.util.Scanner;

public class Tang_Huaxin_pa3_lcs {

    //find the longest common sequence
    static String LCS(String s1, String s2) {
        int m = s1.length(), n = s2.length();
        int[][] dp = new int[m][n]; // java default value is 0, it's the base case
        char[][] D = new char[m][n]; //direction to get the LCS

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (s1.charAt(i) == s2.charAt(j)) { //dp transition
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    D[i][j] = 'D';
                } else {
                    if (dp[i - 1][j] >= dp[i][j - 1]) {
                        dp[i][j] = dp[i - 1][j];
                        D[i][j] = 'U';
                    } else {
                        dp[i][j] = dp[i][j - 1];
                        D[i][j] = 'L';
                    }
                }
            }
        }

        //follow the direction to get the string
        StringBuilder sb = new StringBuilder();
        int i = m - 1, j = n - 1;
        while (i >= 0 && j >= 0) {
            if (D[i][j] == 'U') {
                i--;
            } else if (D[i][j] == 'L') {
                j--;
            } else {
                sb.insert(0, s1.charAt(i)); //every time we add the char to the head, so we don't need to reverse
                i--;
                j--;
            }
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        String s1, s2;
        s1 = args[0];
        s2 = args[1];


        String res = LCS(s1, s2);
        System.out.printf("%s\n", res);
    }
}
