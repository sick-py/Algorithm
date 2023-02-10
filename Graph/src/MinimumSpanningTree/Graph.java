package MinimumSpanningTree;

import java.util.ArrayList;
import java.util.List;

public class Graph {
    public List<Vertex> vertices;
    public List<Edge> edges;

    public Graph(List<Vertex> g1, List<Edge> e1) {
        vertices = g1;
        edges = e1;
    }

    Vertex vertexExists(int id) {
        for (int i = 0; i < vertices.size(); i++) {
            if (vertices.get(i).id == id) {
                return vertices.get(i);
            }
        }
        return null;
    }

    void generateGraph(int vs, List<ArrayList<Integer>> edges) {
        for (int i = 0; i < vs; i++) {
            Vertex v = new Vertex(i + 1, false);
            vertices.add(v);
        }

        for (int i = 0; i < edges.size(); i++) {
            Vertex src = vertexExists(edges.get(i).get(0)); // edges[i][0] is source
            Vertex dest = vertexExists(edges.get(i).get(1)); // edges[i][1] is destination
            Edge e = new Edge(edges.get(i).get(2), false, src, dest); // edges[i][2] is weight
            this.edges.add(e); // adding edges
        }
    }

    void printGraph() {
        for (int i = 0; i < this.edges.size(); i++) {
            System.out.println("\t" + this.edges.get(i).src.id + "--(" + this.edges.get(i).weight + ")--"
                    + this.edges.get(i).dest.id);
        }
    }

    int getTotalCost() {
        int totalCost = 0;
        for (Edge edge : this.edges) {
            totalCost += edge.weight;
        }
        return totalCost;
    }
}
