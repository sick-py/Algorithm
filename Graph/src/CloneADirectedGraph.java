import java.util.HashMap;

public class CloneADirectedGraph<X> {
    /**
     * Given the root node of a directed graph, clone this graph by creating its deep copy, such that the cloned graph has the same vertices and edges as the original graph.
     *
     * We use depth-first traversal and create a copy of each node while traversing the graph. To avoid getting stuck in cycles, we use a hashtable to store each visited node. And we do not revisit nodes that exist in the hashtable. The hashtable key is a node in the original graph, and its value is the corresponding node in the cloned graph.
     * */
    private Node<X> cloneRec(Node<X> root, DirectedGraph<X> graph, HashMap<Node<X>, Node<X>> nodesCompleted) {
        if (root == null) {
            return null;
        }

        Node<X> pNew = new Node<>(root.data);

        nodesCompleted.put(root, pNew);

        // Adding new vertex in the graph
        graph.addVertexNode(pNew);

        // Iterate over each neighbor of the current vertex/node
        for (Node<X> p : root.neighbors) {
            Node<X> x = nodesCompleted.get(p);
            if (x == null) {
                // If node is not visited call recursive function to create vertex/node
                pNew.neighbors.add(cloneRec(p, graph, nodesCompleted));
            } else {
                // If node is visited just add it to the neighbors of current vertex/node
                pNew.neighbors.add(x);
            }
        }
        return pNew;
    }

    public DirectedGraph<X> clone(DirectedGraph<X> graph) {
        HashMap<Node<X>, Node<X>> nodesCompleted = new HashMap<>();

        DirectedGraph<X> res = new DirectedGraph<>();

        if (graph.nodes.isEmpty()) {
            return res;
        }

        cloneRec(graph.nodes.get(0), res, nodesCompleted);
        return res;
    }

}
