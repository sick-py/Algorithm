public class InorderSuccessorWithParents  {
    /**
     * Here are the steps for finding the in-order successor with parent pointers:
     *
     * Find the node with the same value as the given value. If no such node is found, return -1.
     *
     * If the node with the given value has a right child, then the left-most child in its right child’s subtree is the in-order successor.
     *
     * If the node with the given value has no right child, we go up the parent chain using parent pointers. We go upstream until we find a parent node with its left child in the same parent chain. This parent node is the in-order successor.
     *
     * If we find no such node in steps 2 and 3, the in-order successor does not exist. In other words, the given value is the highest value stored in the tree. Return NULL in this case.
     * Let’s run the above algorithm to find the in-order successor of the node with value 75:
     * */
    TreeNode treeMin(TreeNode root) {
        while (root.left != null) {
            root = root.left;
        }
        return root;
    }

    TreeNode parentStream(TreeNode root) {
        // Traversing upstream using parent pointers to find a parent with a left child
        // node in the same stream
        while (root.parent != null) {
            if (root.parent.left == root) {
                return root.parent;
            }
            root = root.parent;
        }

        return null;//if no finding
    }

    TreeNode help(TreeNode root) {
        if (root == null) {
            return null;
        }

        if (root.right != null) {
            return treeMin(root);
        }

        return parentStream(root);
    }

    TreeNode find(TreeNode root, int val) {
        while (root != null) {
            if (root.data < val) {
                root = root.right;
            } else if (root.data > val) {
                root = root.left;
            } else {
                return help(root);
            }
        }

        if (root == null) {
            return null;
        }

        return null;
    }
}
