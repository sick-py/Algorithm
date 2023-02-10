import java.util.Stack;

public class IteratorForTree {
    /**
     * Given a binary tree, write an iterator that takes in the root of a binary tree and iterates through its nodes in an in-order fashion.
     *
     * Our implementation should include two critical methods of any iterator type:
     *
     * hasNext(): This function returns whether an in-order node exists next in line.
     * getNext(): This function returns the next in-order node of the binary tree.
     * The method inorderUsingIterator in the “Try it yourself” section demonstrates how these two methods may be used to test our implementation.
     *
     * For this problem, we will need a stack to maintain the in-order state of the binary tree. We will use our stack to:
     *
     * Get the next tree node from the iterator by calling the getNext() method.
     * Check whether the iterator has the next tree node by calling the hasNext() method.
     * Here are a few basic steps to construct our iterator and set up the correct stack:
     *
     * Implement hasNext(), so it returns true if the stack representing the in-order traversal of our tree is not empty.
     * Implement getNext():
     * It returns the next element in the in-order traversal, that is, the top-most element of the stack.
     * If the stack is empty, it returns NULL.
     * We also need to set the stack in the correct state for the next getNext() call. For this, we look at the right child of the top element in the stack:
     * If it’s non-null, we push all the nodes from this node to the left-most node of its subtree onto the stack. We perform this step before returning the next node in step 2.
     * If it’s null, we leave the stack as it is.
     * */
    Stack<TreeNode> st = new Stack<>();

    public IteratorForTree(TreeNode root) {
        populateStack(root);
    }

    private void populateStack(TreeNode root) {
        while (root != null) {
            // We keep pushing node into the stack until we reach the left-most node
            st.push(root);
            root = root.left;
        }
    }

    public boolean hasNext() {
        return !st.isEmpty();
    }

    public TreeNode getNext() {
        if (st.isEmpty()) {
            return null;
        }

        TreeNode res = st.pop();

        // We now populate the stack again from root till the left-most node in the
        // sub-tree of the right child of the node we just extracted
        TreeNode temp = res.right;
        populateStack(temp);

        return res;
    }

}
