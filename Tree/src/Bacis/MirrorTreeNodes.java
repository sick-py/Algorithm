package Bacis;

import Main.TreeNode;

public class MirrorTreeNodes {
    /**
     * Given the root node of a binary tree, swap the left and right children for each node, such that the binary tree becomes a mirror image of itself.
     *
     * 1. can we solve it by traverse?
     * Yes, I write a traversefunction to traverse each node and reverse the left and right child nodes of each node.
     *
     * Extract a node separately, what do you need it to do? Let it exchange its left and right child nodes.
     *
     * When do you need to do it? It seems that the front, middle and back order positions are fine.
     * */
    void traverse(TreeNode root) {
        if (root == null) {
            return;
        }

        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        traverse(root.left);
        traverse(root.right);
    }
     /**
      * 2.can we do it by decompose
      * I can use invertTree(x.left)to first xflip the left subtree of , then invertTree(x.right)flip xthe right subtree of , and xfinally swap the left and right subtrees of , which just completes the xflip of the entire binary tree rooted at , that is, completes invertTree(x)the definition of
      * */

     TreeNode flip(TreeNode root) {
         if (root == null) {
             return null;
         }

         TreeNode left = flip(root.left);
         TreeNode right = flip(root.right);
         root.left = right;
         root.right = left;
         return root;
     }


}
