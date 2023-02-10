import java.util.LinkedList;
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

}
