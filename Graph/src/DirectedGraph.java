import java.util.ArrayList;
import java.util.List;

public class DirectedGraph<X> {
    List<Node<X>> nodes;

    public DirectedGraph() {
        nodes = new ArrayList<>();
    }

    void addVertex(X data) {
        Node<X> node = new Node<>(data);
        nodes.add(node);
    }

    Node<X> find(X data) {
        for (int i = 0; i < nodes.size(); i++) {
            Node<X> node = nodes.get(i);
            if (node.data == data) {
                return node;
            }
        }
        return null;
    }

    int count() {
        return nodes.size();
    }

    void addEdge(X start, X end) {
        Node<X> startNode = find(start);
        Node<X> endNode = find(end);
        if (startNode != null && endNode != null) {
            startNode.neighbors.add(endNode);
        } else {
            System.out.println("no these nodes");
        }
    }

    void print() {
        for (Node<X> node : nodes) {
            System.out.print(node.data + " -> { ");
            for (Node<X> n : node.neighbors) {
                System.out.print(n.data + " ");
            }
            System.out.print("}\n");
        }
    }

    void deleteEdge(X node, X neighbor) {
        for (Node<X> v : nodes) {
            if (v.data == node) {
                for (Node<X> n : v.neighbors) {
                    if (n.data == neighbor) {
                        v.neighbors.remove(n);
                        break;
                    }
                }
                break;
            }
        }
    }

    public void addVertexNode(Node<X> pNew) {
        nodes.add(pNew);
    }
}
