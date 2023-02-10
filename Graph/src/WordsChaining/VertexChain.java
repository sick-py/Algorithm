package WordsChaining;

import java.util.ArrayList;
import java.util.List;

public class VertexChain {
    public char value;
    public boolean visited;
    public List<VertexChain> adjVertices;
    public List<VertexChain> inVertices;

    public VertexChain(char value, boolean visited) {
        this.value = value;
        this.visited = visited;
        this.adjVertices = new ArrayList<VertexChain>();
        this.inVertices = new ArrayList<VertexChain>();
    }
}
