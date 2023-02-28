import java.util.Arrays;

public class UniquePath {
    int[][] memo;
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length, n = obstacleGrid[0].length;
        if (obstacleGrid[m - 1][n - 1] == 1) {
            return 0;
        }
        memo = new int[m][n];
        for (int[] l : memo) {
            Arrays.fill(l, -1);
        }
        return dp(obstacleGrid, m - 1, n - 1);
    }

    private int dp(int[][] obstacleGrid, int i, int j) {
        int m = obstacleGrid.length, n = obstacleGrid[0].length;
        if (i < 0 || i >= m || j < 0 || j >= n || obstacleGrid[i][j] == 1) {
            return 0;
        }
        if (i == 0 && j == 0) {
            return 1;
        }
        if (memo[i][j] != -1) {
            return memo[i][j];
        }

        memo[i][j] = dp(obstacleGrid, i - 1, j) +
                dp(obstacleGrid, i, j - 1);
        return memo[i][j];
    }

}
