package Main;

import javax.swing.tree.TreeNode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Graph {
    /**
     * In fact, we rarely use this Vertexclass to implement graphs, but use the adjacency list and adjacency matrix that are often said.
     * */
    List<Integer>[] graph;
    public class Vertex {
        int data;
        Vertex[] neighbors;

        /**
         * /* multiple tree
         *

         class TreeNode {
         *
         int val;
         *
         TreeNode[] children;
         *
         }
         * */
    }

    /**
     * The adjacency matrix is ​​a two-dimensional Boolean array, we call it right matrix, if the node xand yare connected, then matrix[x][y]set as true(represented by the green square in the above figure true). If you want to find xthe , matrix[x][..]just scan around.
     *
     * So, why are there these two ways of storing graphs? It must be because they each have their own strengths and weaknesses .
     * */


    /**
     * For the adjacency list, the advantage is that it takes up less space.
     *
     * You can see that there are so many empty positions in the adjacency matrix, and more storage space is definitely needed.
     *
     * However, the adjacency list cannot quickly determine whether two nodes are adjacent.
     *
     * Therefore, which method to use to realize the graph depends on the specific situation.
     *
     * PS: In conventional algorithm problems, the adjacency list will be used more frequently, mainly because it is simpler to operate, but this does not mean that the adjacency matrix should be underestimated. Matrix is ​​a powerful mathematical tool, and some hidden properties of graphs can be revealed with the help of subtle matrix operations. However, this article is not going to introduce mathematical content, so interested readers can search and learn by themselves.
     * */


    boolean[][] matrix;
    /**
     * although the graph can play more algorithms and solve more complex problems, in essence, the graph can be considered as an extension of the multi-fork tree.
     *
     * Graph-related questions rarely appear in written interviews. Even if there are, most of them are simple traversal questions. Basically, the traversal of multi-fork trees can be completely copied.
     *
     * A graph is composed of nodes and edges
     *
     * Finally, we define a unique concept of degree in graph theory. In an undirected graph, "degree" is the number of edges connected to each node.
     *
     * Since the edges of the directed graph have directions, the "degree" of each node in the directed graph is subdivided into indegree and outdegree , as shown in the following figure:
     * */

    /** Traverse the graph
     * various data structures were invented for nothing more than traversal and access, so "traversal" is the basis of all data structures .
     *
     * The biggest difference between a graph and a multi-fork tree is that a graph may contain loops. If you start traversing from a certain node in the graph, you may walk around and return to this node, but this does not happen in a tree. Starting from a node must go to the leaf node, and it is absolutely impossible to return to itself.
     *
     * So, if the graph contains cycles, the traversal frame is assisted by an visited array :
     *
     * The nodes marked as true in are represented in gray, and the nodes onPathmarked as true in are represented in green . It is analogous to the snake game, visitedrecording the grids the snake has passed, while onPathonly recording snake body. In the traversal process of the graph, it onPathis used to judge whether it is a loop. It is analogous to the scene when a greedy snake bites itself (forms a loop). Now you can understand the difference between the two.
     *
     * If you are asked to deal with path-related issues, this onPathvariable will definitely be used, such astopological sortThere is use in it.
     *
     */
    boolean[] visited;
    boolean[] onPath;

    void traverse(Graph graph, int s) {
        if (visited[s]) return;
        visited[s] = true;
        onPath[s] = true;

        for (int neighbor : graph.graph[s]) {
            traverse(graph, neighbor);
        }

        onPath[s] = false;
    }
/***
    * In addition, you should have noticed that the operation of this onPath array is very similar to the previousBacktracking Algorithm Core RoutineThe difference between "selection" and "deselection" lies in the location: the "selection" and "deselection" of the backtracking algorithm are inside the for loop, while the operations on the onPath array are outside the for loop.
   * Why is there this difference? This is the previous article Backtracking Algorithm Core RoutineThe difference between the backtracking algorithm and the DFS algorithm mentioned in : the backtracking algorithm does not focus on nodes, but on branches. If you don’t believe me, look at the backtracking tree drawn above, we need to make selections and deselections on the “branches”:
    // DFS
    void traverse(TreeNode root) {
        if (root == null) return;
        printf("get into node %s", root);
        for (TreeNode child : root.children) {
            traverse(child);
        }
        printf("leave node %s", root);
    }

    // backtrack focus on the branch
    void backtrack(TreeNode root) {
        if (root == null) return;
        for (TreeNode child : root.children) {
            // 做选择
            printf("from %s tp %s", root, child);
            backtrack(child);
            // 撤销选择
            printf("from %s to %s", child, root);
        }
    }

 * If you execute backtrack code, you will find that the root node is missing:
 * So for the traversal of the "graph" here, we should use the DFS algorithm, that is, put onPaththe operation outside the for loop, otherwise the traversal of the record starting point will be missed.
 *
 * Having said so many onPatharrays , let’s talk about visitedarrays, its purpose is obvious. Since the graph may contain loops, the visitedarray is to prevent recursive repeated traversal of the same node from entering an infinite loop.
 *
 * Of course, if the title tells you that there are no loops in the graph, you can omit all the visitedarrays , which is basically the traversal of the multi-fork tree.
 */
class review {
    class Node {
        public int val;
        public List<Node> neighbors;
        public Node() {
            val = 0;
            neighbors = new ArrayList<Node>();
        }
        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<Node>();
        }
        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }
    public Node cloneGraph(Node node) {
        if (node == null) return null;
        Node copy = new Node(node.val);
        Node[] visited = new Node[101];
        Arrays.fill(visited, null);
        dfs(node, copy, visited);
        return copy;
    }

    private void dfs(Node node, Node copy, Node[] visited) {
        visited[copy.val] = copy;

        for (Node n : node.neighbors) {
            if (visited[n.val] == null) {
                Node newNode = new Node(n.val);
                copy.neighbors.add(newNode);
                dfs(n, newNode, visited);
            } else {
                copy.neighbors.add(visited[n.val]);
            }
        }
    }

}


}
