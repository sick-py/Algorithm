package BFS;


import javax.swing.tree.TreeNode;
import java.util.*;
import Main.Node;

public class main {
    /**
     * let’s give an example of a common scenario where BFS appears. Well, the essence of the poblem is to let you find the shortest distance from the start point startto . This example sounds boring, but the BFS algorithm problem targetIn fact, they are all doing this thing
     * This broad description can have various variants, such as walking a maze, some grids are walls that cannot be walked, what is the shortest distance from the starting point to the ending point? What if this labyrinth has a "portal gate" that can be teleported instantly?
     *
     * Another example is two words. You are required to change one of them into the other through some substitutions. You can only replace one character at a time. How many times do you need to replace at least?
     *
     * Another example is the Lianliankan game. The condition for eliminating two squares is not only the same pattern, but also to ensure that the shortest connection between the two squares cannot exceed two inflection points. When you play Lianliankan and click on two coordinates, how does the game judge how many inflection points there are on the shortest line between them?
     *
     * Another example……
     *
     * There are some bells and whistles, these questions are not any tricks, they are essentially a "map" that allows you to go from a starting point to an end point and ask the shortest path. This is the essence of BFS. Once the framework is clear, just write silently.
     *
     * Framework
     * */
    int BFS(Node start, Node target) {
        Queue<Node> q = new LinkedList<>();
        Set<Node> visited = new HashSet<>();
        q.offer(start);
        visited.add(start);
        int step = 0;
        while (!q.isEmpty()) {
            int sz = q.size();
            for (int i = 0; i < sz; i++) {
                Node cur = q.poll();
                if (cur == target) {
                    return step;
                }
                Node x = cur;
                //for (Main.Node x : cur.adj()) {
                    if (!visited.contains(x)) { /**
                     the node in the queue is always right, so before adding it, we need to check
                     and offer and add is always together
                     */
                        q.offer(x);
                        visited.add(x);
                    }
            }
            step++;
        }
        return -1;
    }

    /**
     * Why can BFS find the shortest distance, but DFS can't ?
     *
     * First of all, you look at the logic of BFS. depthEvery time an increase is made, all nodes in the queue take a step forward, which ensures that when the end point is reached for the first time, the number of steps taken is the least.
     *
     * Can't DFS find the shortest path? In fact, it is also possible, but the time complexity is relatively high. You think, DFS actually relies on the recursive stack to record the path traveled. If you want to find the shortest path, you must explore all the branches in the binary tree to compare the length of the shortest path, right? And BFS uses the queue to "go hand in hand" one step at a time, and can find the shortest distance without traversing the entire tree.
     *
     * Visually speaking, DFS is a line, BFS is a surface; DFS is a single fight, and BFS is a collective action. This should be easier to understand.
     * */

    /**
     * 2. Since BFS is so good, why does DFS still exist ?
     *
     * BFS can find the shortest distance, but has high space complexity, while DFS has low space complexity.
     *
     * Still take the example we dealt with the binary tree problem just now. Suppose the binary tree given to you is a full binary tree with the number of nodes. NFor the DFS algorithm, the space complexity is nothing more than a recursive stack. In the worst case, it is at most the height of the tree, or That is O(logN).
     *
     * But if you think about the BFS algorithm, the nodes at the first level of the binary tree are stored in the queue every time. In this case , the space complexity in the worst case should be the number of nodes at the bottom of the tree, that is, expressed in Big N/2O.O(N)
     *
     * From this point of view, BFS still has a price. Generally speaking, BFS is used when looking for the shortest path, and DFS is used more at other times (mainly because recursive code is easy to write).
     *
     * Well, now that you know enough about BFS, let's take a more difficult question and deepen your understanding of the framework.
     * */

    /**
     * Let’s start with a simple question and practice the BFS framework, to determine the minimum height of a binary tree
     * How to set it in the framework of BFS? First of all, let's startclarify targetwhat the starting point and the ending point are, and how to judge that the end point has been reached?
     *
     * Obviously, the starting point is rootthe root node , and the end point is the "leaf node" closest to the root node . A leaf node is a node nullwith :
     * */

    class TreeNode {
        TreeNode left, right;
        boolean isLeaf() {
            return true;
        }
    }
    int minDepth(TreeNode root) {
        if (root == null) return 0;
        Queue<TreeNode> q = new LinkedList<>();
        //tree has no circle, so no set
        q.offer(root);
        int res = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                TreeNode cur = q.poll();
                if (cur.isLeaf()) {
                    return res;
                }
                if (cur.left != null) {
                    q.offer(cur.left);
                }
                if (cur.right != null) {
                    q.offer(cur.right);
                }
            }
            res++;
        }
        return res;
    }

    public int shortestPathBinaryMatrix(int[][] grid) {
        Queue<int[]> q = new LinkedList<>();
        Set<int[]> visited = new HashSet<>();
        int step = 0;
        int n = grid.length - 1;
        if (grid[0][0] == 1 || grid[n][n] == 1) {
            return -1;
        }
        q.offer(new int[] {0, 0});
        visited.add(new int[] {0, 0});
        int[][] directions = new int[][] {
                {-1, -1}, {-1, 0}, {-1, 1}, {0, 1}, {0, -1}, {1, -1}, {1, 0}, {1, 1}
        };

        while (!q.isEmpty()) {
            int sz = q.size();
            step++;
            for (int i = 0; i < sz; i++) {
                int[] cur = q.poll();
                int x = cur[0], y = cur[1];
                if (cur[0] == n && cur[1] == n) {
                    return step; //here we only talk about the res
                }

                for (int[] d : directions) {
                    int nexti = x + d[0];
                    int nextj = y + d[1];
                    int[] next = new int[] {nexti, nextj};
                    if (nexti < 0 || nextj < 0 || nexti > n || nextj > n || visited.contains(next) || grid[nexti][nextj] == 1) {
                        //add all the other except case here
                        continue;
                    }

                    q.offer(next);
                    visited.add(next);

                }
            }

        }
        return -1;
    }

}
