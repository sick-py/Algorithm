package Bacis;

import Main.TreeNode;

public class CheckIdenticalTree {
    /**
     * Given the roots of two binary trees, determine if the trees are identical or not.
     * Trees with the same data are not necessarily identical, as they may not be the same in terms of their structure. For example, if we look at the two trees below, they are not identical even though they have the same data:
     *
     * This problem can be effectively solved using a recursive solution.
     *
     * Here’s how we can recursively solve our problem:
     *
     * We do a depth-first traversal on both trees simultaneously and compare the node data at each level.
     * The base case of this recursion is to check if:
     * Both nodes that we compare are NULL. If yes, we return TRUE.
     * Only one of the nodes that we compare is NULL. If yes, we return FALSE.
     * Two trees A and B are identical if:
     * The data value in their root nodes is the same or NULL.
     * The left subtree of tree A is identical to the left subtree of tree B.
     * The right subtree of tree A is identical to the right subtree of tree B.
     * */
    boolean identical(TreeNode root1, TreeNode root2) {
        //base case 1
        if (root1 == null && root2 == null) {
            return true;
        }

        if (root1 != null && root2 != null) {
            return (root1.data == root2.data) && identical(root1.left, root2.left) && identical(root1.right, root2.right);
        }

        //base case 2
        return false;
    }


}
