package WordsChaining;

import java.util.List;

public class graphChain {
    List<VertexChain> graph;

    public graphChain(List<VertexChain> g) {
        super();
        graph = g;
    }

    // This method returns TRUE if the graph has a cycle containing
    // all the nodes, returns FALSE otherwise
    boolean canCircle(VertexChain node, VertexChain src) {
        node.visited = true;

        // Base case
        // return TRUE if all nodes have been visited and there
        // exists an edge from the last node being visited to
        // the starting node
        List<VertexChain> adj = node.adjVertices;
        if (allVisited()) {
            for (int i = 0; i < adj.size(); i++) {
                if (adj.get(i) == src) {
                    return true;
                }
            }
        }

        //recursive
        for (int i = 0; i < adj.size(); i++) {
            if (adj.get(i).visited == false) {
                node = adj.get(i);
                if (canCircle(node, src)) {
                    return true;
                }
            }
        }

        return false;
    }

    boolean canChainWords(int listSize) {
        // Empty list and single word cannot form a chain
        if (listSize < 2) {
            return false;
        }

        if (this.graph.size() > 0) {
            if (this.outEqualsIn()) {
                return this.canCircle(this.graph.get(0), this.graph.get(0));
            }
        }

        return false;
    }

    void createGraph(List<String> words) {
        for (int i = 0; i < words.size(); i++) {
            String word = words.get(i);
            char startChar = word.charAt(0), endChar = word.charAt(word.length() - 1);

            VertexChain start = VertexChainExists(startChar);

            if (start == null) {
                start = new VertexChain(startChar, false);
                this.graph.add(start);
            }

            VertexChain end = VertexChainExists(endChar);

            if (end == null) {
                end = new VertexChain(endChar, false);
                this.graph.add(end);
            }

            // Add an edge from start vertex to end vertex
            addEdge(start, end);
        }
    }

    private void addEdge(VertexChain start, VertexChain end) {
        start.adjVertices.add(end);
        end.inVertices.add(start);
    }

    private VertexChain VertexChainExists(char value) {
        for (int i = 0; i < this.graph.size(); i++) {
            if (this.graph.get(i).value == value) {
                return this.graph.get(i);
            }
        }
        return null;
    }

    // This method returns TRUE if out degree of each vertex is equal
    // to its in degree, returns FALSE otherwise
    private boolean outEqualsIn() {
        for (int i = 0; i < this.graph.size(); i++) {
            int out = this.graph.get(i).adjVertices.size();
            int inn = this.graph.get(i).inVertices.size();
            if (out != inn) {
                return false;
            }
        }
        return true;
    }

    private boolean allVisited() {
        for (int i = 0; i < this.graph.size(); i++) {
            if (this.graph.get(i).visited == false) {
                return false;
            }
        }
        return true;
    }
}
