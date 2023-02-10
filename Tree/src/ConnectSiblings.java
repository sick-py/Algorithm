public class ConnectSiblings {
    /**
     * The task is to connect all nodes at the same hierarchical level. Connect them from left to right, such that the next pointer of each node points to the node on its immediate right. The next pointer of the right-most node at each level should point to the first node of the next level in the tree.
     *
     * Each node in the given binary tree for this problem contains the next pointer, along with the left and right pointers. The next pointer is used to connect the same level nodes to each other and across levels.
     * */

    void populateNext(TreeNode root) {
        if (root == null) {
            return;
        }

        TreeNode curr = root, last = root;

        while (curr != null) {
            if (curr.left != null) {
                last.next = curr.left;
                last = curr.left;
            }

            if (curr.right != null) {
                last.next = curr.right;
                last = curr.right;
            }
            last.next = null;
            curr = curr.next;
        }
    }
}
