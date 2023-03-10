package Bacis;

import Main.TreeNode;

import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class LevelOrder {
    /**
     *
     * */
    void print(TreeNode root) {
        if (root == null) {
            return;
        }

        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);

        while (!q.isEmpty()) {
            int n = q.size();
            TreeNode curr;
            for (int i = 0; i < n; i++) {
                curr = q.poll();
                if (curr != null) {
                    System.out.println(curr.data);
                    q.add(curr.left);
                    q.add(curr.right);
                }
            }
        }
    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new LinkedList<>();
        if (root == null) {
            return res;
        }
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);

        while (!q.isEmpty()) {
            int sz = q.size();
            List<Integer> level = new LinkedList<>();
            for (int i = 0; i < sz; i++) {
                TreeNode cur = q.poll();
                level.add(cur.data);
                if (cur.left != null) {
                    q.add(cur.left);
                }
                if (cur.right != null) {
                    q.add(cur.right);
                }
            }
            res.add(level);
        }
        return res;
    }
}
