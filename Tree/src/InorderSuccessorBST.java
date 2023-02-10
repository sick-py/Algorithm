public class InorderSuccessorBST {
    /**
     * Write a method to find the in-order successor of a BST node with a given value. Return -1 if the node with the given value does not exist.
     *
     * The in-order successor of a node in a BST is the node with the smallest key greater than the key of the current node. This is the same as the next node in an in-order traversal of the BST.
     *
     * Find the node with the same value as the given value. If no such node is found, return a node with a value of -1.
     * If the node with the given value has a right child, then the left-most child in the right child’s subtree is the in-order successor. This successor would also be the child with the minimum value in that subtree.
     *
     * If the node with the given value has no right child, then the in-order successor is the node with a minimum value, higher than the given value, in the parent chain of the node with the given value. This is updated as we move through the BST.
     *
     * If we find no such node in steps 2 and 3, the in-order successor does not exist. In other words, the given value is the highest value stored in the tree. Return NULL in this case.
     * Note: We only update or assign the successor when we move towards a left child node while looking for the input node.
     * */
    TreeNode treeMin(TreeNode root) {
        while (root.left != null) {
            root = root.left;
        }
        return root;
    }

    TreeNode find(TreeNode root, int val) {
        if (root == null) {
            return null;
        }
        // Initializing variable that will store any potential in-order successor
        // node in the parent chain
        TreeNode successor = null;

        while (root != null) {
            if (root.data < val) {
                root = root.right;
            } else if (root.data > val) {
                successor = root;
                root = root.left;
            } else {
                if (root.right != null) {
                    successor = treeMin(root.right);
                }
                break;
            }
        }
        if (root == null) {
            return null; //didn't found
        }
        return successor;
    }

    TreeNode find2(TreeNode root, int val) {
        if (root == null) {
            return null;
        }

        TreeNode successor = null;

        while (root != null) {
            if (root.data < val) {
                root = root.right;
            } else if (root.data > val) {
                successor = root;
                root = root.left;
            } else {
                if (root.right != null) {
                    successor = treeMin(root.right);
                }
                break;
            }
        }

        if (root == null) {
            return null;
        }

        return successor;
    }


}
