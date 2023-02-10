package LCA;

public class LCAWithParents {
    /**
     * Since the node contains a pointer to the parent node, the root node of the binary tree does not need to be entered.
     *
     * This question is actually not a question of common ancestors, but a question of intersecting single-linked lists . If you imagine the parentpointer as a pointer of a single-linked list next, the question becomes:
     *
     * If you input the head nodes pand qof two singly linked lists, the two singly linked lists will definitely intersect, please return the intersection point.
     * */

    class Node {
        int val;
        Node left;
        Node right;
        Node parent;
    };

    Node lowestCommonAncestor(Node p, Node q) {
        // 施展链表双指针技巧
        Node a = p, b = q;
        while (a != b) {
            // a 走一步，如果走到根节点，转到 q 节点
            if (a == null) a = q;
            else           a = a.parent;
            // b 走一步，如果走到根节点，转到 p 节点
            if (b == null) b = p;
            else           b = b.parent;
        }
        return a;
    }
}
