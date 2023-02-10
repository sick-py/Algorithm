public class DeleteZeroSubTree {
    /**
     * Given the root of a binary tree, delete any sub-trees whose nodes sum up to zero.
     *
     * For the solution to this challenge, we do a post-order traversal of the binary tree. Here are the steps we follow for our post-order traversal algorithm:
     *
     * Traverse the left sub-tree by recursively calling the post-order function.
     * Traverse the right sub-tree by recursively calling the post-order function.
     * If the current node’s left or right sub-tree reports a sum of zero, we delete that sub-tree.
     * If the root node returns zero, then we delete the entire tree.
     * */

    int delete(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int sumLeft = delete(root.left);
        int sumRight = delete(root.right);

        if (sumLeft == 0) {
            root.left = null;
        }

        if (sumRight == 0) {
            root.right = null;
        }

        return root.data + sumLeft + sumRight;
    }
}
