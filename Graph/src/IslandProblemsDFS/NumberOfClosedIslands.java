package IslandProblemsDFS;

public class NumberOfClosedIslands {
    /**
     * So how to judge "closed island"? In fact, it is very simple. If you exclude the islands that are on the sidelines in the previous question, isn’t the rest just “closed islands” ?
     * */
    int numIslands(char[][] grid) {
        int res = 0;
        int m = grid.length, n = grid[0].length;
        for (int j = 0; j < n; j++) {
            dfs2(grid, 0, j);
            dfs2(grid, m - 1, j);
        }
        for (int i = 0; i < m; i++) {
            dfs2(grid, i, 0);
            dfs2(grid, i, n - 1);
        }
        for (int i = 1; i < m - 1; i++) {
            for (int j = 1; j < n - 1; j++) {
                if (grid[i][j] == '1') {
                    res++;
                    dfs2(grid, i, j);
                }
            }
        }
        return res;
    }

    private void dfs2(char[][] grid, int i, int j) {
        //flood the island when we encounter it
        int m = grid.length, n = grid[0].length;
        if (i < 0 || i >= m || j < 0 || j >= n) {
            return;
        }
        if (grid[i][j] == '0') {
            return;
        }
        grid[i][j] = '0';
        dfs2(grid, i - 1, j);
        dfs2(grid, i, j + 1);
        dfs2(grid, i + 1, j);
        dfs2(grid, i, j - 1);
    }
}
