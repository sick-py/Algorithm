package Main;

import java.util.LinkedList;
import java.util.List;

public class AllPossiblePath {
    /**
     * Title Input a directed acyclic graph , this graph ncontains nodes, labeled as 0, 1, 2,..., n - 1, please calculate all the paths from 0 to n - 1 .
     *
     * The input is actually a graph represented by the "adjacency list", which graph[i]stores all the neighbor nodes iof node.
     *
     * For example graph = [[1,2],[3],[3],[]], the input represents the following picture:
     * The algorithm should return [[0,1,3],[0,2,3]],
     * */
    List<List<Integer>> res = new LinkedList<>();
    List<List<Integer>> allPath(int[][] graph) {
        LinkedList<Integer> path = new LinkedList<>();
        traverse(graph, 0, path);
        return res;
    }

    private void traverse(int[][] graph, int s, LinkedList<Integer> path) {
        //add s to path
        path.addLast(s);

        int n = graph.length;
        if (s == n - 1) {
            //reach the termination node n - 1
            res.add(new LinkedList<>(path));
            //we can return here, but before return we need to maintain the path
            //path.removeLast()
            //return
            //or we can don't because it's an acyclic graph
        }
        for (int v : graph[s]) {
            traverse(graph, v, path);
        }
        path.removeLast();
    }
    /**
     * This problem is solved in this way, pay attention to the language characteristics of Java, because Java function parameters pass object references, so reswhen adding to path, you need to copy a new list, otherwise the resfinal list in is empty.
     * */
}
