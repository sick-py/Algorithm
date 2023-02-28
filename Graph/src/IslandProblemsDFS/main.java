package IslandProblemsDFS;

public class main {
    /**
     * The core test point of the island series of questions is to use the DFS/BFS algorithm to traverse the two-dimensional array .
     *
     * This article mainly explains how to use the DFS algorithm to kill the island series of topics, but the core idea of ​​​​using the BFS algorithm is exactly the same, nothing more than rewriting DFS into BFS.
     *
     * So how to use DFS search in 2D matrix? If you regard each position in the two-dimensional matrix as a node, and the four positions above, below, left, and right of this node are adjacent nodes, then the entire matrix can be abstracted into a network "graph" structure.
     *
     * ALL DFS WAYs
     * */
    void dfs(int[][] grid, int i, int j, boolean[][] visited) {
        int m = grid.length, n = grid[0].length;
        if (i < 0 || j < 0 || i >= m || j >= n) {
            // out of boundary
            return;
        }
        if (visited[i][j]) {
            // have already visited (i, j)
            return;
        }
        // get into the node
        visited[i][j] = true;
        dfs(grid, i - 1, j, visited); // up
        dfs(grid, i + 1, j, visited); // down
        dfs(grid, i, j - 1, visited); // left
        dfs(grid, i, j + 1, visited); // right
    }

    int[][] directions = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; //up down left right
    void dfs1(int[][] grid, int i, int j, boolean[][] visited) {
        int m = grid.length, n = grid[0].length;
        if (i >= m || i < 0 || j >= n || j < 0) {
            return;
        }
        if (visited[i][j]) {
            return;
        }

        visited[i][j] = true;
        for (int[] d : directions) {
            int nextI = i + d[0];
            int nextJ = j + d[1];
            dfs1(grid, nextI, nextJ, visited);
        }
    }

    /** Number of islands
     *
     * */
    int numIslands(char[][] grid) {
        int res = 0;
        int m = grid.length, n = grid[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
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

    class review {
        public int numIslands(char[][] grid) {
            int m = grid.length, n = grid[0].length;
            int res = 0;
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (grid[i][j] == '1') {
                        dfsL(grid, i, j);
                        res++;
                        System.out.println(res);
                    }
                }
            }
            return res;
        }

        private void dfsL(char[][] grid, int i, int j) {
            int m = grid.length, n = grid[0].length;
            if (i < 0 || j < 0 || i >= m || j >= n) {
                return;
            }
            if (grid[i][j] == '0') {
                return;
            }
            grid[i][j] = '0';
            dfsL(grid, i + 1, j);
            dfsL(grid, i - 1, j);
            dfsL(grid, i, j + 1);
            dfsL(grid, i, j - 1);
        }

        public int maxAreaOfIsland(int[][] grid) {
            int m = grid.length, n = grid[0].length;
            int res = 0;
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (grid[i][j] == 1) {
                        res = Math.max(res, dfsM(grid, i, j));
                    }
                }
            }
            return res;
        }

        private int dfsM(int[][] grid, int i, int j) {
            int m = grid.length, n = grid[0].length;
            if (i < 0 || j < 0 || i >= m || j >= n) {
                return 0;
            }
            if (grid[i][j] == 0) {
                return 0;
            }
            grid[i][j] = 0;
            return dfsM(grid, i + 1, j) +
                    dfsM(grid, i - 1, j) +
                    dfsM(grid, i, j + 1) +
                    dfsM(grid, i, j - 1) +
                    + 1;
        }
    }


}
