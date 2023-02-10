package MinimumSpanningTree;

import java.util.ArrayList;
/**
 * Find the minimum spanning tree (MST) of a connected, undirected graph with weighted edges.
 *
 * A spanning tree of a connected, undirected graph is a subgraph that is a tree and connects all the vertices. One graph can have many different spanning trees. A graph with
 * �
 * n
 *  vertices has a spanning tree with
 * �
 * −
 * 1
 * n−1
 *  edges.
 *
 * A weight can be assigned to each edge of the graph. The weight of a spanning tree is the sum of weights of all the edges of the spanning tree. A minimum spanning tree (MST) for a connected, weighted, and an undirected graph is a spanning tree with a weight less than or equal to the weight of every other spanning tree.
 *
 * We find the minimum spanning tree of a graph using Prim’s algorithm. This algorithm builds the tree one vertex at a time, starting from any arbitrary vertex. At each step, it adds the minimum weight edge from the constructed tree to a vertex from the remaining vertices of the input graph.
 *
 * The algorithm is as follows:
 *
 * 1. Initialize the MST with an arbitrary vertex from the graph
 * 2. Find the edge with the minimum weight from the constructed graph
 *    to the vertices not yet added to the graph
 * 3. Add that edge and vertex to the MST
 * 4. Repeat steps 2-3 until all the vertices have been added to the MST
 * The time complexity to find the minimum weight edge is
 * �
 * (
 * �
 * 2
 * )
 * O(n
 * 2
 *  )
 * . However, it can be improved further by using heaps to find the minimum weight edge.
 * */

public class Main {
    Graph findMST(Graph graph) {
        Graph mst = new Graph(new ArrayList<>(), new ArrayList<>());
        int vertexCount = 0;

        Vertex current = graph.vertices.get(0);
        current.visited = true;
        vertexCount++;

        // Iterate over all vertices because MST should contain all vertices
        while (vertexCount < graph.vertices.size()) {
            Edge smallest = null;

            // Iterate over edges
            // Pick an edge which is adjacent to the MST and not visited and has minimum weight
            for (int i = 0; i < graph.edges.size(); i++) {
                if (graph.edges.get(i).visited = false) {
                    if (graph.edges.get(i).src.visited == true && graph.edges.get(i).dest.visited == false) {
                        if (smallest == null || graph.edges.get(i).weight < smallest.weight) {
                            smallest = graph.edges.get(i);
                        }
                    }
                }
            }

            vertexCount++;
            // Construct the remaining MST using the smallest weight edge
            // populate the edges of mst
            if (smallest != null) {
                smallest.visited = true;
                smallest.dest.visited = true;
                mst.edges.add(new Edge(smallest.weight, smallest.visited, smallest.src, smallest.dest));

                // populate the vertices of mst'
                if (mst.vertexExists(smallest.src.id) == null) {
                    mst.vertices.add(new Vertex(smallest.src.id, smallest.visited));
                }
                if (mst.vertexExists(smallest.dest.id) == null) {
                    mst.vertices.add(new Vertex(smallest.dest.id, smallest.dest.visited));
                }
            }
        }
        return mst;
    }
}
