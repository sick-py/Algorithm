package MinimumSpanningTree;

public class Edge {
    public int weight;
    public boolean visited;
    public Vertex src, dest;

    public Edge(int a, boolean v, Vertex s, Vertex d) {
        weight = a;
        visited = v;
        src = s;
        dest = d;
    }
}
