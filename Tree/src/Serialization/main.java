package Serialization;

import Main.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class main {
    /**
     * serializeThe method may serialize it into a string 2,1,#,6,#,#,3,#,#, where # represents a null pointer, then inputting this string into the deserialize method can still restore the binary tree.
     *
     * In other words, these two methods will be used in pairs, you just need to ensure that they are self-consistent.
     *
     * Imagine that the binary tree knot should be a two-dimensional structure, and the serialized string is a linear one-dimensional structure. The so-called serialization is nothing more than "flattening" structured data, and the essence is to examine the traversal method of the binary tree .
     *
     * What are the traversal methods of binary tree? The recursive traversal methods include pre-order traversal, in-order traversal, and post-order traversal; the iterative method is generally hierarchical traversal. In this article, I will try all these methods to realize the serialize method and deserialize method.
     *
     * take preOrder as an example
     * */
    String SEP = ",", NULL = "#";


    String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        rec(root, sb);
        return sb.toString();
    }

    private void rec(TreeNode root, StringBuilder sb) {
        if (root == null) {
            sb.append(NULL).append(SEP);
            return;
        }

        sb.append(root.data).append(SEP);
        rec(root.left, sb);
        rec(root.right, sb);
    }

    /**
     * Brother Dong will take you to brush the binary tree (construction), at least two of the pre-order, mid-order and post-order traversals must cooperate to restore the binary tree. That is because the previous traversal result does not record the information of the null pointer. The node list contains the information of the null pointer, so the nodebinary tree can be restored only by using the list.
     *
     * Then, the deserialization process is the same, first determine the root node root, and then follow the rules of pre-order traversal to recursively generate the left and right subtrees :
     * */

    public TreeNode deserialize(String data) {
        LinkedList<String> nodes = new LinkedList<>();
        for (String s : data.split(SEP)) {
            nodes.addLast(s);
        }
        return rec1(nodes);
    }

    private TreeNode rec1(LinkedList<String> nodes) {
        if (nodes.isEmpty()) {
            return null;
        }
        String first = nodes.removeFirst();
        if (first.equals(NULL)) {
            return null;
        }

        TreeNode root = new TreeNode(Integer.parseInt(first));
        root.left = rec1(nodes);
        root.right = rec1(nodes);
        return root;
    }

    /**
     * take the post order as another example
     *
     * */
    String postSer(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        recp(root, sb);
        return sb.toString();
    }

    private void recp(TreeNode root, StringBuilder sb) {
        if (root == null) {
            sb.append(NULL).append(SEP);
            return;
        }

        recp(root.left, sb);
        recp(root.right, sb);
        sb.append(root.data).append(SEP);
    }

    TreeNode deSer(String data) {
        LinkedList<String> nodes = new LinkedList<>();
        for (String s : data.split(SEP)) {
            nodes.addLast(s);
        }

        return deRec(nodes);
    }

    private TreeNode deRec(LinkedList<String> nodes) {
        if (nodes.isEmpty()) {
            return null;
        }

        String last = nodes.removeLast();
        if (last.equals(NULL)) {
            return null;
        }
        TreeNode root = new TreeNode(Integer.parseInt(last));
        //pat attention here
        root.right = deRec(nodes);
        root.left = deRec(nodes);
        return root;
    }

    /**
     * level order
     * use queen to level traverse, use i to recorde the index
     * */

    String levelSer(TreeNode root) {
        if (root == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);

        while (!q.isEmpty()) {
            TreeNode cur = q.poll();
            if (cur == null) {
                sb.append(NULL).append(SEP);
                continue;
            }
            sb.append(cur.data).append(SEP);
            q.offer(cur.left);
            q.offer(cur.right);
        }
        return sb.toString();
    }

    TreeNode levelDe(String data) {
        if (data.isEmpty()) {
            return null;
        }
        String[] nodes = data.split(SEP);
        TreeNode root = new TreeNode(Integer.parseInt(nodes[0]));
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);

        int i = 1;
        while (i < nodes.length) {
            //nodes in queue are all parents
            TreeNode parent = q.poll();
            String left = nodes[i++]; //take out i and i + 1
            if (!left.equals(NULL)) {
                parent.left = new TreeNode(Integer.parseInt(left));
                q.offer(parent.left);
            } else {
                parent.left = null;
            }

            String right = nodes[i++];
            if (!right.equals(NULL)) {
                root.right = new TreeNode(Integer.parseInt(right));
                q.offer(parent.right);
            } else {
                parent.right = null;
            }
        }
        return root;
    }
}
