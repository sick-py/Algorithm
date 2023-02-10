package BinarySearchTree;

import Main.TreeNode;

public class NthHighestNumberInBST {
    /**
     * The in-order traversal of a BST is always sorted in ascending order. So, to find the nth highest node, we need to scan the tree in descending order by performing a reverse in-order traversal.
     *
     * The in-order traversal is typically LPR, meaning Left child, Parent node, and then Right child.
     * Reverse in-order traversal is RPL, meaning Right child, Parent node, and then Left child.
     * Here is the algorithm for this solution:
     *
     * Perform a reverse in-order traversal of the BST in RPL.
     * While traversing, keep a current count of the nodes visited so far.
     * Once this current count reaches n, we return that node.
     * */

    public static int count  = 0;
    TreeNode findRec(TreeNode root, int k) {
        if (root == null) {
            return null;
        }

        TreeNode res = findRec(root.right, k);

        if (res != null) {
            return res;
        }

        count++;
        if (k == count) {
            return root;
        }

        res = findRec(root.left, k);
        if (res != null) {
            return res;
        }

        return null;
    }

    TreeNode find(TreeNode root, int k) {
        count = 0;
        if (root == null) {
            return null;
        }

        return findRec(root, k);
    }

    TreeNode rec(TreeNode root, int k) {
        if (root == null) {
            return null;
        }

        TreeNode res = rec(root.right, k);
        if (res != null) {
            return res;
        }

        count++;
        if (k == count) {
            return root;
        }

        res = rec(root.left, k);
        if (res != null) {
            return res;
        }

        return null;
    }

}
