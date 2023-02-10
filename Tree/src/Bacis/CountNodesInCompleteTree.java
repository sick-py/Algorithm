package Bacis;

import Main.TreeNode;

public class CountNodesInCompleteTree {
    /**
     * If you are asked to count the number of nodes in an ordinary binary tree, it is very simple, just add a little code to the traversal framework of the binary tree.
     *
     * However, click on question 222 "The number of nodes in a complete binary tree" to give you a complete binary tree and let you calculate the number of nodes in it, will you? What is the time complexity of the algorithm?
     *
     * The time complexity of this algorithm should be O(logN*logN). If the algorithm in your mind is not so efficient, then this article is written for you.
     *
     * The complete binary tree we are talking about is as shown in the figure below, each layer is arranged compactly and left:
     *
     * The perfect binary tree we are talking about is as shown in the figure below. It is a special kind of complete binary tree. Each layer is full, like a stable triangle:
     *
     *
     * */

    int countOrdinary(TreeNode root) {
        if (root == null) {
            return 0;
        }

        return 1 + countOrdinary(root.left) + countOrdinary(root.right);
    }

    int countPerfect(TreeNode root) {
        int h = 0;
        while (root != null) {
            root = root.left;
            h++;
        }

        return (int)Math.pow(2, h) - 1;
    }

    int countComplete(TreeNode root) {
        if (root == null) {
            return 0;
        }
        TreeNode l = root, r = root;
        int hl = 0, hr = 0;
        while (l != null) {
            l = l.left;
            hl++;
        }
        while (r != null) {
            r = r.right;
            hr++;
        }

        if (hl == hr) {
            return (int)Math.pow(2, hl) - 1;
        }

        return 1 + countComplete(root.left) + countComplete(root.right);
    }
}
