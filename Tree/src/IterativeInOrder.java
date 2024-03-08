import java.util.Stack;

public class IterativeInOrder {
    /**
     * The in-order traversal of a binary tree starts from the root. As we traverse the binary tree in-order iteratively, a stack is helpful to track the nodes.
     *
     * In the case of a BST, our in-order traversal solution prints nodes in sorted ascending order.
     *
     * To traverse the tree in-order, we first visit the left node “L”, followed by the current node “N”, and finally, the right node “R”. We repeat this process for every node during the traversal.
     *
     * In short, we print our nodes in the “L N R” sequence, where the current node “N” comes in the middle.
     *
     * Here are the steps you need to follow:
     *
     * We must initialize a stack data structure and point the current node pointer to the root.
     * Now, push the current node onto the stack and point the current node to its left child.
     * Repeat step 2 until the current node points to a null value.
     * If the current node is null and the stack is not empty, perform the following operations:
     * Print the top stack element.
     * Pop the top stack element and point the current node to the right child of the popped element.
     * If the current node is null and the stack is empty, we terminate our algorithm. Let’s run the above algorithm on a sample tree.
     * */

    void iterative(TreeNode root) {
        if (root == null) {
            return;
        }
        Stack<TreeNode> st = new Stack<>();
        TreeNode curr = root;

        while (!st.isEmpty() || curr != null) {
            if (curr != null) {
                st.push(curr);
                curr = curr.left;
            } else {
                System.out.println(st.peek().data);
                curr = st.pop().right;
                if (!(st.isEmpty() && curr == null)) {
                    System.out.println(", ");
                }
            }
        }
    }


}
