package IslandProblemsDFS;

public class NumberOfSubIslands {
    /**
     * When is an island grid2in isB a sub-island of an island in ?grid1A
     * An island in B is a sub-island of an island in A when all land in B island is land in island A
     * Conversely, if there is a piece of land in A, and the corresponding position in A island is sea water, then the island in B is not a sub-island of the island .
     * Then, we only need to traverse all the islands in B, and exclude those islands that cannot be sub-islands, and the rest are sub-islands.
     * */
    int countSubIslands(int[][] gridA, int[][] gridB) {
        int m = gridA.length, n = gridA[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (gridB[i][j] == 1 && gridA[i][j] == 0) {
                    dfs(gridB, i, j);
                }
            }
        }

        //now all the left lands are sub islands
        int res = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (gridB[i][j] == 1) {
                    res++;
                    dfs(gridB, i, j);
                }
            }
        }
        return res;
    }

    private void dfs(int[][] grid, int i, int j) {
        int m = grid.length, n = grid[0].length;
        if (i < 0 || j < 0 || i >= m || j >= n) {
            return;
        }
        if (grid[i][j] == 0) {
            return;
        }

        grid[i][j] = 0;
        dfs(grid, i + 1, j);
        dfs(grid, i, j + 1);
        dfs(grid, i - 1, j);
        dfs(grid, i, j - 1);
    }
}
