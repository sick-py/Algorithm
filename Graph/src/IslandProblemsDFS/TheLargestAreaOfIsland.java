package IslandProblemsDFS;

public class TheLargestAreaOfIsland {
    /**
     * The general idea of ​​this question is exactly the same as before, except that when dfsthe function submerges the island, it should also find a way to record the area of ​​the island .
     *
     * We can set the return value for dfsthe function to record the number of land submerged each time, just look at the solution:
     * */
    int maxArea(int[][] grid) {
        int res = 0;
        int m = grid.length, n = grid[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    res = Math.max(res, dfs(grid, i, j));
                }
            }
        }
        return res;
    }

    private int dfs(int[][] grid, int i, int j) {
        int m = grid.length, n = grid[0].length;
        if (i < 0 || j < 0 || i >= m || j >= n) {
            return 0;
        }
        if (grid[i][j] == 0) {
            return 0;
        }

        grid[i][j] = 0;
        return dfs(grid, i + 1, j) +
                dfs(grid, i - 1, j) +
                dfs(grid, i, j - 1) +
                dfs(grid, i, j+ 1) +
                1;
    }
}
