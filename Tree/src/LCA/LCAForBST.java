package LCA;

import Main.TreeNode;

public class LCAForBST {
    /**
     * Given your input a binary search tree without duplicate values , and two nodes and existing in the tree , please calculate the nearest common ancestor node of and .pqpq
     *
     * Copying the previous solution code can definitely solve this problem, but it does not use the nature of BST's "small left and big right", obviously the efficiency is not the highest.
     *
     * But for BST, there is no need to traverse the subtree honestly. Due to the nature of BST, the left is small and the right is large, comparing the value of the current node with val1and val2can determine whether the current node isLCA :
     *
     * Assume val1 < val2, then val1 <= root.val <= val2it means that the current node is LCA; if root.val is smaller than val1 , you need to go to the right subtree with a larger value to find LCA; if root.valis larger than val2, you need to go to the left subtree with a smaller value to find LCA.
     *
     * when // val1 <= root.val <= val2 this root is LCA
     * */

    TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // 保证 val1 较小，val2 较大
        int val1 = Math.min(p.data, q.data);
        int val2 = Math.max(p.data, q.data);
        return find(root, val1, val2);
    }

    // 在 BST 中寻找 val1 和 val2 的最近公共祖先节点
    TreeNode find(TreeNode root, int val1, int val2) {
        if (root == null) {
            return null;
        }
        if (root.data > val2) {
            // 当前节点太大，去左子树找
            return find(root.left, val1, val2);
        }
        if (root.data < val1) {
            // 当前节点太小，去右子树找
            return find(root.right, val1, val2);
        }
        // val1 <= root.val <= val2
        // 则当前节点就是最近公共祖先
        return root;
    }
}
