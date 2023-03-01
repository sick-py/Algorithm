package BinarySearchTree;


import Main.TreeNode;

import java.util.Stack;

public class BSTIterator {

    Stack<TreeNode> stack;

    public BSTIterator(TreeNode root) {
        stack = new Stack<>();
        TreeNode cur = root;
        while (cur != null) {
            stack.push(cur);
            cur = cur.left;
        }
    }

    //return the top element, and maintain the top is the
    public int next() {
        TreeNode cur = stack.pop();

        if (cur.right != null) {
            stack.push(cur.right);
            while (cur.right.left != null) {
                stack.push(cur.right.left);
                cur.right.left = cur.right.left.left;
            }
        }
        return cur.data;
    }

    public boolean hasNext() {
        return !stack.isEmpty();
    }
}
