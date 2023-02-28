package IslandProblemsDFS;

import java.util.HashSet;

public class NumberOfDifferentIslands {
    /**
     * There are four islands, but the bottom left and top right islands have the same shape, so there are three different islands, and the algorithm returns 3.
     * Obviously, we have to find a way to convert the "islands" in the two-dimensional matrix into a type such as a string, and then use a data structure such as HashSet to deduplicate, and finally get the number of different islands.
     *
     * If you want to convert the island into a string, to put it bluntly, it is serialization, and to put it bluntly, serialization is traversal. Serialization and deserialization of binary treeI talked about the conversion between binary trees and strings, and it is similar here.
     *
     * First of all, for islands with the same shape, if they start from the same starting point, dfsthe order of function traversal must be the same .
     * Because the traversal order is hard-coded in your recursive function, it will not change dynamically:
     *
     * Assuming their traversal order is:
     * Down, Right, Up, Undo Up, Undo Right, Undo Down
     * If I use to 1, 2, 3, 4represent , and to -1, -2, -3, -4represent up, down, left, and right undo, then their traversal order can be expressed as follows:
     * 2, 4, 1, -1, -4, -2
     *
     * You see, this is equivalent to the result of island serialization. As long as this string of numbers is generated for comparison every time you use dfsto traverse the island, you can calculate how many different islands there are .
     *
     * PS: Careful readers ask, why can the record "undo" operation uniquely represent the traversal order? Is it possible to not record the undo operation? Actually not.
     *
     * For example, "down, right, undo right, undo down" and "down, undo down, right, undo right" are obviously two different traversal orders, but if the undo operation is not recorded, then they are both "down, right". ", it becomes the same traversal order, which is obviously wrong.
     *
     * So we need to modify dfsthe function and add some function parameters to record the traversal order:
     * */

    void dfs(int[][] grid, int i, int j, StringBuilder sb, int dir) {
        int m = grid.length, n = grid[0].length;
        if (i < 0 || i >= m || j < 0 || j >= n) {
            return;
        }

        if (grid[i][j] == 0) {
            return;
        }

        grid[i][j] = 0;
        sb.append(dir).append(',');

        dfs(grid, i - 1, j, sb, 1);//up
        dfs(grid, i + 1, j, sb, 2); // down
        dfs(grid, i, j - 1, sb, 3); // left
        dfs(grid, i, j + 1, sb, 4); // right

        sb.append(-dir).append(',');
    }

    int count(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        HashSet<String> set = new HashSet<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    StringBuilder sb = new StringBuilder();
                    dfs(grid, i, j, sb, 666); //whatever first direction is ok
                    set.add(sb.toString());
                }
            }
        }
        return set.size();
    }
}
