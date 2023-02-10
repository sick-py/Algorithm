package LCA;

import Main.TreeNode;

import java.util.HashSet;

public class multipleNodesLCA {
    /**
     * check out question 1676 "The nearest common ancestor IV of the binary tree":
     *
     * Still give you a binary tree without duplicate values, but this time instead of giving you pand qtwo nodes, you are given a list containing several nodes nodes(these nodes all exist in the binary tree), let you calculate The nearest common ancestor of these nodes.
     * */
    TreeNode LCA(TreeNode root, TreeNode[] nodes) {
        HashSet<Integer> values = new HashSet<>();
        for (TreeNode node : nodes) {
            values.add(node.data);
        }

        return find(root, values);
    }

    private TreeNode find(TreeNode root, HashSet<Integer> values) {
        if (root == null) {
            return null;
        }

        if (values.contains(root.data)) {
            return root;
        }

        TreeNode left = find(root.left, values);
        TreeNode right = find(root.right, values);

        if (left != null && right != null) {
            return root;
        }

        return left != null ? left : right;
    }

}
