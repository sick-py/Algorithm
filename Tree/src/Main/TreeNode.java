package Main;

public class TreeNode {
    public int data;
    public TreeNode left;
    public TreeNode right;

    //some problems
    public TreeNode next;
    public TreeNode parent;
    public int count;

    public TreeNode(int a) {
        data = a;
        left = null;
        right = null;
        next = null;
        parent = null;
        count = 0;
    }
}
