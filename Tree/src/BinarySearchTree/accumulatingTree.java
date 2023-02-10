package BinarySearchTree;

import Main.TreeNode;

public class accumulatingTree {
    /**
     * We need to convert the BST into a cumulative tree, the function signature is as follows:
     * The title should not be difficult to understand. For example, if the node 5 in the figure is converted into a cumulative tree, the nodes larger than 5 are 6, 7, 8, plus 5 itself, so the value of this node on the cumulative tree should be 5+6+ 7+8=26.
     *
     * Each node of BST is small on the left and large on the right. This seems to be useful information. Since the cumulative sum is the sum of all elements greater than or equal to the current value, then each node calculates the sum of the right subtree, isn’t it all right?
     * This is not acceptable. For a node, it is true that the right subtree is an element larger than it, but the problem is that its parent node may also be an element larger than it? This cannot be determined, and we do not have a pointer to reach the parent node, so the general idea of ​​​​a binary tree cannot be used here.
     *
     * In fact, the correct solution is very simple, or use the in-order traversal feature of BST .
     * Just now we said that the in-order traversal code of BST can print the values ​​of nodes in ascending order: So what if I want to print the values ​​of the nodes in descending order
     * It's very simple, just change the recursive order:right mid left
     * */

    int sum = 0;
    void traverse(TreeNode root) {
        if (root == null) {
            return;
        }
        traverse(root.right);
        sum += root.data;
        root.data = sum;
        traverse(root.left);
    }
}
