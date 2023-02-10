import java.util.Stack;

public class PrintTreePerimeter {
    /**
     * Given the root node of a binary tree, print the nodes that form its perimeter (boundary). We must print the perimeter of the binary tree in three phases (order is important):
     *
     * Left boundary
     * Leaf nodes
     * Right boundary
     *
     * Solution1:
     *
     * We print the perimeter of the binary tree in three phases, the left boundary, leaf nodes, and the right boundary. The left and right boundaries will be printed iteratively while the leaf nodes will be printed recursively. Here is how the algorithm looks:
     *
     * Print the root node.
     * Print the left boundary in top-down order.
     * Print the leaf nodes in left-right order. We’ll be traversing from one leaf node to the next using post-order traversal.
     * Print the right boundary in bottom-up order.
     * We push the node values in a stack here.
     * Once we hit the leaf node, we pop all stack elements while printing them.
     * */

    void printLeft(TreeNode root, StringBuilder res) {
        while (root != null) {
            int curr = root.data;

            if (root.left != null) {
                root = root.left;
            } else if (root.right != null) {
                root = root.right;
            } else {
                //break loop on leaf
                break;
            }

            res.append(String.valueOf(curr) + ", ");
        }
    }

    void printRight(TreeNode root, StringBuilder res) {
        Stack<Integer> rValues = new Stack<>();
        while (root != null) {
            int curr = root.data;

            if (root.right != null) {
                root = root.right;
            } else if (root.left != null) {
                root = root.left;
            } else {
                break;
            }

            rValues.push(curr);
        }

        while (!rValues.isEmpty()) {
            res.append(String.valueOf(rValues.pop()) + ", ");
        }
    }

    void printLeaf(TreeNode root, StringBuilder res) {
        if (root != null) {
            // Recursively traversing to leaf nodes
            printLeaf(root.left, res);
            printLeaf(root.right, res);

            // Append node to result if it's a leaf node
            if (root.left == null && root.right == null) {
                res.append(String.valueOf(root.data) + ", ");
            }
        }
    }

    void display(TreeNode root) {
        StringBuilder res = new StringBuilder();
        if (root != null) {
            res.append(String.valueOf(root.data) + ", ");
            printLeft(root.left, res);

            if (root.left != null || root.right != null) {
                // We don't need to print if root is also the leaf node.
                printLeaf(root, res);
            }

            printRight(root.right, res);
        }

        // Removing trailing comma and space from our result
        if (res.length() > 2) {
            res.deleteCharAt(res.length() - 1);
            res.deleteCharAt(res.length() - 1);
        }
        System.out.println(res.toString());
    }

}
