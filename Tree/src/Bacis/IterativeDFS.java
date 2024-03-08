package Bacis;

import Main.TreeNode;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class IterativeDFS {
    /**
     * To do iterative DFS of binary tree in Java, you need to use a stack data structure to store the nodes that need to be visited. You can implement three types of DFS traversal: preorder, inorder and postorder.
     *
     * Preorder traversal visits the root node first, then the left subtree and then the right subtree. Inorder traversal visits the left subtree first, then the root node and then the right subtree. Postorder traversal visits the left subtree first, then the right subtree and then the root node.
     *
     * Here is an example of iterative preorder traversal using stack1:
     * */
    void preOrder(TreeNode root) {
        if (root == null) {
            return;
        }

        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        /** Pop all items one by one. Do following for every popped item
         a) print it
         b) push its right child
         c) push its left child
         Note that right child is pushed first so that left is processed first */
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            System.out.println(node.data);

            if (node.right != null) {
                stack.push(node.right);
            }

            if (node.left != null) {
                stack.push(node.left);
            }
        }
    }

    void inOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;

        // if current node is null and stack is also empty, we're done
        // Or we traverse this cur node in while loop by inOrder
        while (cur != null || !stack.isEmpty()) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            cur = stack.pop();
            System.out.println(cur.data);

            /* we have visited the node and its
               left subtree.  Now, it's right
               subtree's turn */
            cur = cur.right;
        }
        stack.push(root);

        while (!stack.isEmpty()) {
            if (root.left != null) {
                stack.push(root.left);
            }
        }
    }

    //it's the reverse of the preorder and swap the left, right
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new LinkedList<>();
        if (root == null) {
            return res;
        }

        Stack<TreeNode> stack = new Stack<>();
        Stack<TreeNode> outStack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode cur = stack.pop();
            outStack.push(cur);
            if (cur.left != null) {
                stack.push(cur.left);
            }
            if (cur.right != null) {
                stack.push(cur.right);
            }
        }
        while (!outStack.isEmpty()) {
            res.add(outStack.pop().data);
        }
        return res;
    }

    class review {
        public List<Integer> preorderTraversal(TreeNode root) {
            List<Integer> res = new LinkedList<>();
            if (root == null) {
                return res;
            }

            Stack<TreeNode> stack = new Stack<>();
            stack.push(root);

            while (!stack.isEmpty()) {
                TreeNode cur = stack.pop();
                res.add(cur.data);
                if (cur.right != null) {
                    stack.push(cur.right);
                }
                if (cur.left != null) {
                    stack.push(cur.left);
                }
            }
            return res;
        }
    }
}
