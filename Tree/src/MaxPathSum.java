public class MaxPathSum {
    /**
     * Given the root of a binary tree, return the maximum sum of any non-empty path.
     *
     * A path in a binary tree is defined as:
     *
     * “A sequence of nodes such that each pair of adjacent nodes must have an edge connecting them. A node can only be included in a path at most once. Moreover, including the root in the path is not compulsory.”
     *
     * We can calculate the path sum by adding up all nodes’ values in the path. To solve this problem, we’ll calculate the maximum path sum given the root of a binary tree so that there won’t be any greater path than it in the tree.
     *
     * We’ll start by simplifying the problem. We look at the implementation of the maxContrib(node) function, which takes a node as an argument and computes a maximum contribution that this node and one or none of its subtrees can add.
     *
     * If we are sure that the root is also included in the maximum path, the problem can be solved using maxContrib(root), and then we can add both the maxContrib of the left child if it’s greater than zero and the same for the right child. Unfortunately, this isn’t always the case, and the maximum path can exclude root. Therefore, we need to make a few additions to the solution to check whether to continue with the current path or start a new path with the current node as the highest node at each step.
     *
     * First, we need to initialize a maxSum as a global variable of negative infinity. We start by calling maxContrib with root as its parameter. Second, we need to implement the maxContrib(node) function with a check to decide whether to continue with the old path or start a new path. Here’s how we implement it:
     *
     * This is a recursive function, so the base case states that the maxContrib is 0 if the node is null.
     *
     * Call maxContrib recursively for the node’s children to calculate the max gain for the left and right subtrees. We also need to check if this value is greater than zero to avoid adding any negative values.
     *
     * Now, check whether to continue with the old path or start a new path. To start a new path, the sum of this path is node.val + max(leftSubtree, rightSubtree). Update maxSum if it’s better to start a new path.
     *
     * We return the maximum gain to the node, and one or none of its subtrees can be added to the current path: node.val + Math.max(leftSubtree, rightSubtree).
     * */

    int maxSum = Integer.MIN_VALUE;

    //maxContrib(node) function, which takes a node as an argument and computes a maximum contribution that this node and one or none of its subtrees can add.
    int maxContrib(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int left = 0, right = 0;

        if (maxContrib(root.left) > 0) {
            left = maxContrib(root.left);
        }
        if (maxContrib(root.right) > 0) {
            right = maxContrib(root.right);
        }

        // the price to start a new path where `root` is a highest root
        int price = root.data + left + right;
        maxSum = Math.max(maxSum, price);

        return root.data + Math.max(left, right);
    }

    int max(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int left = 0, right = 0;

        if (max(root.left) > 0) {
            left = max(root.left);
        }

        if (max(root.right) > 0) {
            right = max(root.right);
        }

        int p = root.data + left + right;
        maxSum = Math.max(p, maxSum);

        return root.data + Math.max(left, right);
    }
}
