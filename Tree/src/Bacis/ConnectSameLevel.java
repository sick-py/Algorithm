package Bacis;

import Main.TreeNode;

public class ConnectSameLevel {
    /**
     * Given a binary tree, connect all nodes at the same hierarchical level. We need to connect them from left to right, such that the next pointer of each node points to the node on its immediate right. The next pointer of the right-most node at each level will be NULL.
     * <p>
     * For this problem, each node in the binary tree has one additional pointer (the next pointer), along with the left and right pointers.
     * <p>
     * 1. can we solve it by traverse?
     * What each node has to do is also very simple, just point its own nextpointer to the right node.
     * Maybe you will imitate the flip question and directly connect the left with right children,
     * but it has a big problem, because it can only connect two nodes of the same parent node, what we want to traverse is actually the "gap" between two adjacent nodes. In this way, a binary tree is abstracted into a ternary tree, and each node on the ternary tree is two adjacent nodes of the original binary tree .
     * Now, we only need to implement a traversefunction to traverse the ternary tree. What each "ternary tree node" needs to do is to connect the two internal binary tree nodes:
     */

    TreeNode connectRec(TreeNode root) {
        if (root == null) {
            return null;
        }
        traverse(root.left, root.right);
        return root;
    }

    private void traverse(TreeNode left, TreeNode right) {
        if (left == null || right == null) {
            return;
        }

        left.next = right;

        traverse(left.left, left.right);
        traverse(right.left, right.left);
        traverse(left.right, right.left);

    }

    /**
     * can this be solved by decompose?
     * Well, it seems that there is no particularly good idea, so this question cannot be solved using the thinking of "decomposing the problem".
     * */
}