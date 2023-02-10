package Bacis;

import Main.TreeNode;

public class ExpandToLinkedList {
    /**
     * connect the node by pre-order
     * 1.can we do it by traverse?
     * it seems to be ok: perform preorder traversal on the entire tree, and construct a "linked list" while traversing, but it can't solve it in its place, it needs extra space
     *
     * 2.can we solve it by decompose?
     * We try to give the definition of the flatten function which will return the head of the flat linked list:
     * With this function definition, how to flatten a tree into a linked list according to the requirements of the topic?
     *
     * For a node x, the following processes can be performed:
     *
     * 1. First use flatten(x.left)and flatten(x.right)to x level the left and right subtrees of .
     * 2. Connect xthe right subtree of to under the left subtree, and then use the entire left subtree as the right subtree.
     * */
    TreeNode flatten(TreeNode root) {
        if (root == null) {
            return null;
        }

        TreeNode left = flatten(root.left);
        TreeNode right = flatten(root.right);
        root.left = null;
        root.right = left;

        TreeNode temp = root;
        while (temp.right != null) {
            temp = temp.right;
        }
        temp.right = right;
        return root;
    }
}
