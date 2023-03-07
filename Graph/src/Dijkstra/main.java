package Dijkstra;

import java.util.*;

class TreeNode{
    int val;
    TreeNode left, right;
    TreeNode[] children;
    TreeNode(int a) {
        val = a;
    }
}

public class main {
    /**
     * it is nothing more than an enhanced version of the BFS algorithm, which is derived from the hierarchical traversal of binary trees.
     *
     * */

    //binary Tree level traversal
    void levelTraverse(TreeNode root) {
        if (root == null) return;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int depth = 1;
        while (!queue.isEmpty()) {
            int sz = queue.size();
            for (int i = 0; i < sz; i++) {
                TreeNode cur = queue.poll();
                System.out.printf("node %d at %d level", cur.val, depth);
                if (cur.left != null) {
                    queue.offer(cur.left);
                }
                if (cur.right != null) {
                    queue.offer(cur.right);
                }
            }
            depth++;
        }
    }

    //Based on the binary tree traversal framework, we can extend the hierarchical traversal framework of the multi-pronged tree:
    void levelMultipleTree(TreeNode root) {
        if (root == null) return;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int depth = 1;
        while (!queue.isEmpty()) {
            int sz = queue.size();
            for (int i = 0; i < sz; i++) {
                TreeNode cur = queue.poll();
                System.out.printf("node %d at %d depth", cur.val, depth);

                for (TreeNode c : cur.children) {
                    queue.offer(c);
                }
            }
            depth++;
        }
    }

    //Based on the traversal framework of multi-fork trees, we can extend the algorithm framework of BFS (breadth-first search):

    void BFS(Node start) {
        Queue<Node> q = new LinkedList<>(); //core data structure
        Set<Node> visited = new HashSet<>(); //in case of circle

        q.offer(start);
        visited.add(start);

        int step = 0;
        while (!q.isEmpty()) {
            int sz = q.size();
            for (int i = 0; i < sz; i++) {
                Node cur = q.poll();
                System.out.printf("from %s to %s, the shortest distance is %d", start, cur, step);
                for (Node c : cur.children) {
                    if (visited.contains(c)) {
                        continue;
                    }
                    q.offer(c);
                    visited.add(c);
                }
            }
            step++;
        }
    }


    /**
     * However, when it comes to the "weighted graph" scenario, things are not so simple, because you can't default to the "weight" of each edge is 1, this weight can be any positive number (Dijkstra's algorithm requires no negative weight edges).
     *
     * In fact, Dijkstra and BFS algorithms are similar, but before explaining the Dijkstra algorithm framework, we first need to modify the previous framework as follows:
     * Find a way to get rid of the for loop inside the while loop.
     * Why? With the foreshadowing just now, it is not difficult to understand, what is the use of the cycle just now?
     * It is to let the binary tree traverse down layer by layer, so that the BFS algorithm spreads outward step by step, because this number of layers, or this number of steps, is useful in the previous scene.

     * But now we want to solve the shortest path problem in the "weighted graph", the "number of steps" has no reference meaning, and the "sum of the weights of the path" is meaningful, so this for cycle can be removed.
     *
     * How to remove? Take the hierarchical traversal of binary trees, in fact, you can directly remove the loop-related code:for
     * */
    void levelTraverseWithOutFor(TreeNode root) {
        if (root == null) {
            return;
        }
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);

        while (!q.isEmpty()) {
            TreeNode cur = q.poll();
            System.out.printf("I don't know which depth node %d is in", cur.val);
            if (cur.left != null) {
                q.offer(cur.left);
            }
            if (cur.right != null) {
                q.offer(cur.right);
            }
        }
    }

    /**
     * But the problem is, without for loops, you can't maintain depth variables.
     * If you want to maintain variables at the same time so that each node knows what layer it is on, you can think of other ways, such as creating a new class and recording the number of layers each node is on:
     * */
    class State {
        int depth;
        TreeNode node;

        State(TreeNode node, int depth) {
            this.depth = depth;
            this.node = node;
        }
    }

    void levelTraverseWithState(TreeNode root) {
        if (root == null) return;
        Queue<State> q = new LinkedList<>();
        q.offer(new State(root, 1));

        while (!q.isEmpty()) {
            State cur = q.poll();
            System.out.printf("node %s is in %d depth", cur.node, cur.depth);
            if (cur.node.left != null) {
                q.offer(new State(cur.node.left, cur.depth + 1));
            }
            if (cur.node.right != null) {
                q.offer(new State(cur.node.right, cur.depth + 1));
            }
        }
    }

    /**
     * Now let's get real in Dijkstra algorithm
     * The Dijkstra algorithm in a weighted graph is different from the ordinary BFS algorithm in an unweighted graph. In the Dijkstra algorithm, the path weight when you pass through a node for the first time is not necessarily the smallest, so for the same node, we may After many times, and each time distFromStartmay be different, such as the following picture:
     *
     *  will go through the node 5three times , each time distFromStartthe value is different, then I take distFromStartthe smallest one, isn’t it the shortest path weight from the starting point tostart the node ?
     *
     *  In fact, Dijkstra can be understood as a BFS algorithm with dp table (or memo), the pseudocode is as follows :
     * */
    class StateDijkstra {
        int id;
        int distFromStart;
        StateDijkstra(int id, int distFromStart) {
            this.id = id;
            this.distFromStart = distFromStart;
        }
    }

    int weight(int from, int to) {
        return 0;
    }


    int[] dijkstra(int start, List<Integer>[] graph) {
        int V = graph.length;
        int[] distTo = new int[V];

        Arrays.fill(distTo, Integer.MAX_VALUE);
        distTo[start] = 0;
        //minHeap
        Queue<StateDijkstra> pq = new PriorityQueue<>((a, b)->(a.distFromStart - b.distFromStart));

        pq.offer(new StateDijkstra(start, 0));

        while (!pq.isEmpty()) {
            StateDijkstra cur = pq.poll();
            int curId = cur.id;
            int curDist = cur.distFromStart;

            if (curDist > distTo[curId]) {
                //already have shorter path
                continue;
            }
            for (int nextNodeId : graph[curId]) {
                int disToNextNode = distTo[curId] + weight(curId, nextNodeId);
                if (distTo[curId] > disToNextNode) {
                    distTo[nextNodeId] = disToNextNode;
                    pq.offer(new StateDijkstra(nextNodeId, disToNextNode));
                }
            }
        }
        return distTo;
    }

    /** Get real 743
     *
     * */


}
