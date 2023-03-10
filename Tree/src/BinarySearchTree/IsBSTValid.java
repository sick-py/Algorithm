package BinarySearchTree;

import Main.TreeNode;

public class IsBSTValid {
    /**
     * Given a binary tree, figure out whether it’s a valid binary search tree.
     *
     * In a BST, each node’s key value is smaller than the key value of the right subtree nodes and greater than the key values of the left subtree nodes.
     *
     * Note that there are pitfalls here. According to the characteristics of BST, the left side is small and the right side is large, if each node wants to judge whether it is a legal BST node, what it needs to do is to compare itself with its left and right children?
     * But there is an error in this algorithm. Each node of the BST should be smaller than all the nodes in the right subtree. The following binary tree is obviously not a BST, because there is a node 6 in the right subtree of node 10, but our algorithm will judge it For legal BST:
     *
     * A more efficient method involves doing an in-order traversal using recursion. We will use this approach for this solution.
     *
     * On each recursive call:
     * Check and return true if the root node passed is null.
     * Check whether the current node’s value is within the given minimum and maximum bounds. If the above condition fails, we return false, indicating that the tree is not a BST.
     * In subsequent recursive calls, when we move to the left subtree, set the maximum bound as the value of the current node, and when we move to the right subtree, set the minimum bound as the value of the current node.
     * */

    //isRec(root, Integer.Min, Integer.Max)
    boolean isValid(TreeNode root, int min, int max) {
        if (root == null) {
            return true;
        }

        if (root.data < min || root.data > max) {
            return false;
        }

        return isValid(root.left, min, root.data) &&
                isValid(root.right, root.data, max);
    }

    class review {
        public boolean isValidBST(TreeNode root) {
            return helper(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
        }

        private boolean helper(TreeNode root, int minValue, int maxValue) {
            if (root == null) {
                return true;
            }
            if (root.data < minValue || root.data > maxValue) {
                return false;
            }

            if (helper(root.left, minValue, root.data) && helper(root.right, root.data, maxValue)) {
                return true;
            }

            return false;
        }
    }
}
