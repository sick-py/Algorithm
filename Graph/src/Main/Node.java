package Main;

import java.util.ArrayList;
import java.util.List;

public class Node<X> {
    X data;
    List<Node<X>> neighbors;

    public Node(X a) {
        data = a;
        neighbors = new ArrayList<>();
    }
}
