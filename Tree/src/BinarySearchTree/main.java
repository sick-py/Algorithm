package BinarySearchTree;

import Main.TreeNode;

public class main {
    /**
     * First of all, the characteristics of BST should be familiar to everyone:
     * 1. For each node of BST, the value of the left subtree node is smaller than the value of root, and the value of the right subtree node is larger than the value of root.
     * 2. For each node of BST node, its left subtree and right subtree are both BST.
     *
     * the inorder traversal results of BST are ordered (ascending) .
     *
     * A lot of data structures directly based on BST include AVL tree, red-black tree, etc., which have self-balancing properties and can provide logN-level addition, deletion and query. Improve efficiency; B+ tree, line segment tree and other structures are designed based on the idea of ​​BST.
     *
     * Let’s briefly summarize, BST-related problems,
     *
     * either use the characteristics of BST’s left small and right large to improve the efficiency of the algorithm,
     *
     * or use the characteristics of in-order traversal to meet the requirements of the topic, that’s all.
     * */

    /** search one
     *
     * */
    TreeNode search(TreeNode root, int k) {
        if (root == null) {
            return null;
        }

        if (root.data == k) {
            return root;
        }

        if (root.data < k) {
            return search(root.right, k);
        }

        if (root.data > k) {
            return search(root.left, k);
        }
        return null;
    }

    /** insert
     * The operation on the data structure is nothing more than traversal + access, traversal is "finding", and accessing is "changing". Specific to this problem, inserting a number is to find the insertion position first, and then perform the insertion operation.
     *
     * Once "change" is involved, it is similar to the construction of a binary tree. The function must return the TreeNode and receive the return value of the recursive call.
     *
     *
     * */
    TreeNode insert(TreeNode root, int val) {
        //find the empty place to insert the node
        if (root == null) return new TreeNode(val);

        if (root.data < val) {
            root.right = insert(root.right, val);
        }

        if (root.data > val) {
            root.left = insert(root.left, val);
        }

        //if root.data == val usually there is no duplicate node in BST
        return root;
    }

    /** delete
     *
     * This problem is a bit complicated, similar to the insert operation, first "find" and then "change", first write out the frame and then say:
     *
     * After finding the target node, for example, a node A, how to delete this node is a difficult point. Because the properties of BST cannot be destroyed while deleting nodes. There are three situations:
     * 0.A Exactly the end node, both child nodes are empty, then it can be done on the spot
     * 1.A There is only one non-empty child node, then it will let this child take its place.
     * 2.A There are two child nodes, in order not to destroy the nature of BST, A must find the largest node in the left subtree, or the smallest node in the right subtree to replace itself. We explain it in the second way.
     * */
    TreeNode delete(TreeNode root, int val) {
        if (root == null) return null;
        if (root.data == val) {
            //case 0, 1
            if (root.left == null) return root.right;
            if (root.right == null) return root.left;

            //case2
            TreeNode minNode = getMin(root.right);
            root.right = delete(root.right, minNode.data);
            minNode.left = root.left;
            minNode.right = root.right;
            root = minNode;
        } else if (root.data > val) {
            root.left = delete(root.left, val);
        } else if (root.data < val) {
            root.right = delete(root.right, val);
        }
        return root;
    }

    private TreeNode getMin(TreeNode root) {
        while (root.left != null) {
            root = root.left;
        }
        return root;
    }


}
