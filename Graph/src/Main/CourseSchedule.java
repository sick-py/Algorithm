package Main;

import java.sql.Struct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class CourseSchedule {
    /**
     * Then this article combines specific algorithm questions to talk about two graph theory algorithms: directed graph ring detection and topological sorting algorithm .
     *
     * These two algorithms can be solved by DFS or BFS. Relatively speaking, the BFS solution is more concise in terms of code implementation, but the DFS solution will help you further understand the profound meaning of recursively traversing the data structure, so this article In this article, I will first talk about the idea of ​​DFS traversal, and then talk about the idea of ​​BFS traversal.
     *
     * When seeing the dependency problem, the first thing that comes to mind is to transform the problem into a data structure like a "directed graph". As long as there is a cycle in the graph, it means that there is a circular dependency
     * For example, you must complete the course 1before you can take the course 3, then there is a 1directed edge pointing 3.
     * So we can generate a picture like this based on prerequisites the array :
     *
     * If it is found that there is a cycle in this directed graph, it means that there is a circular dependency between the courses, and there must be no way to complete all the courses; on the contrary, if there is no cycle, then all the courses must be completed .
     * Well, if we want to solve this problem, first we need to convert the input of the topic into a directed graph, and then judge whether there is a cycle in the graph.
     * */

    List<Integer>[] buildGraph(int numCourses, int[][] prerequisites) {
        List<Integer>[] graph = new LinkedList[numCourses];
        for (int i = 0; i < numCourses; i++) {
            graph[i] = new LinkedList<>();
        }

        for (int[] edge : prerequisites) {
            int from = edge[1], to = edge[0];
            graph[from].add(to);
        }
        return graph;
    }


    /**
     * Note that not all nodes in the graph are connected, so use a for loop to use all nodes as the starting point to call the DFS search algorithm once.
     *
     * In this way, all the nodes in the picture can be traversed. If you print visitedthe array , it should all be true.
     * */
    boolean[] visited;
    boolean[] onPath;
    boolean hasCycle = false;

    void traverse(List<Integer>[] graph, int s) {
        if (onPath[s] == true) {
            hasCycle = true;
        }
        if (visited[s] || hasCycle) {
            return;
        }

        visited[s] = true;
        onPath[s] = true;
        for (int t : graph[s]) {
            traverse(graph, t);
        }
        onPath[s] = false;
    }


    public boolean canFinish(int numCourses, int[][] prerequisites) {
        List<Integer>[] graph = buildGraph(numCourses, prerequisites);
        visited = new boolean[numCourses];
        onPath = new boolean[numCourses];
        for (int i = 0; i < numCourses; i++) {
            traverse(graph, i);
        }
        return !hasCycle;
    }

    /**
     * topological sorting
     * 210
     * This question is an advanced version of the previous question. It is not just for you to judge whether you can complete all the courses, but to further let you return to a reasonable order of classes, ensuring that when you start taking each course, the pre-requisite courses have been completed. over.
     *
     * Intuitively speaking, it is to let you "flatten" a picture, and in this "flattened" picture, all the arrows are in the same direction. For example, all the arrows in the above picture are facing right.
     *
     * Obviously, if there is a cycle in a directed graph, it is impossible to perform topological sorting, because it is impossible to make all arrows in the same direction; conversely, if a graph is a "directed acyclic graph", then it must be possible to sort topological sort.
     * But what does our question have to do with topological sorting?
     * In fact, it is not difficult to see that if the courses are abstracted into nodes and the dependencies between courses are abstracted into directed edges, then the topological sorting result of this graph is the order of classes .
     *
     * In fact, it is very simple. Reversing the result of postorder traversal is the result of topological sorting .
     *
     * You can indeed see such a solution, because the definition of the edge when he built the graph is different from mine. The direction of the arrow in the diagram I built is the "depended on" relationship, such as node 1pointing 2, which means that the node 1is 2dependent on the node, that is, 1it can only 2.
     *
     * If you do the reverse and define the directed edge as a "dependency" relationship, then all the edges in the entire graph are reversed, and the result of the post-order traversal can not be reversed. Specifically, it is to graph[from].add(to);change graph[to].add(from);so that it will not be reversed.
     *
     *
     * */
    List<Integer> postOrder = new ArrayList<>();
    int[] findOrder(int numCourses, int[][] prerequisites){
        List<Integer>[] graph = buildGraph(numCourses, prerequisites);
        visited = new boolean[numCourses];
        onPath = new boolean[numCourses];
        for (int i = 0; i < numCourses; i++) {
            traverse0(graph, i);
        }
        if (hasCycle) {
            return new int[] {};
        }
        Collections.reverse(postOrder);
        int[] res = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            res[i] = postOrder.get(i);
        }
        return res;
    }

    private void traverse0(List<Integer>[] graph, int i) {
        if (onPath[i]) {
            hasCycle = true;
        }
        if (hasCycle || visited[i]) {
            return;
        }
        visited[i] = true;
        onPath[i] = true;
        for (int t : graph[i]) {
            traverse0(graph, t);
        }
        onPath[i] = false;
        postOrder.add(i);
    }

    /**
     * When is the post-order traversal of a binary tree? After traversing the left and right subtrees, the code of the post-order traversal position will be executed. In other words, when the nodes of the left and right subtrees are loaded into the result list, the root node will be loaded.
     *
     * This feature of post-order traversal is very important. The reason why topological sorting is based on post-order traversal is because a task must wait until all tasks it depends on are completed before it can start executing .
     *
     * You understand the binary tree as a directed graph, the direction of the edge is from the parent node to the child node, then it is as follows:
     * */


}
