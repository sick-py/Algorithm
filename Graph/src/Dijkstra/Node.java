package Dijkstra;

import java.util.LinkedList;
import java.util.List;

public class Node {
    int val;
    List<Node> children;
    Node(int a) {
        val = a;
        children = new LinkedList<>();
    }

}
