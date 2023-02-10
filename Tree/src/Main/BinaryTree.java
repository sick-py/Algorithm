package Main;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BinaryTree {
    /**
     * a lot of basic operations here
     * */
    public TreeNode root;

    public BinaryTree() {
        root = null;
    }

    public BinaryTree(int a) {
        root = new TreeNode(a);
    }

    public BinaryTree(List<Integer> dataList) {
        root = null;
        for (Integer d : dataList) {
            insert(d);
        }
    }

    /* insertBT inserts a given key into the binary tree
	   insertBT is used for normal binary tree level by level insertion */
    public void insertBT(int key) {
        Queue<TreeNode> q = new LinkedList<>();
        TreeNode temp = root;
        q.add(root);

        while (!q.isEmpty()) {
            temp = q.peek();
            q.remove();

            if (temp.left == null) {
                temp.left = new TreeNode(key);
                break;
            } else {
                q.add(temp.left);
            }

            if (temp.right == null) {
                temp.right = new TreeNode(key);
                break;
            } else {
                q.add(temp.right);
            }
        }
    }

    /* this inserts an integer into the binary search tree */
    public void insert(int data) {
        TreeNode newNode = new TreeNode(data);
        if (root == null) {
            root = newNode;
        } else {
            TreeNode parent = null, temp = root;
            while (temp != null) {
                parent = temp;
                if (data <= temp.data) {
                    temp = temp.left;
                } else {
                    temp = temp.right;
                }
            }
            if (data <= parent.data) {
                parent.left = newNode;
            } else {
                parent.right = newNode;
            }
        }
    }

    public TreeNode findBST(TreeNode root, int data) {
        if (root == null) {
            return null;
        }

        if (root.data == data) {
            return root;
        } else if (root.data < data) {
            return findBST(root.right, data);
        } else {
            return findBST(root.left, data);
        }
    }

    //populateParentsRec(this.root, null);
    public void populateParents(TreeNode node, TreeNode parent) {
        if (node != null) {
            node.parent = parent;
            populateParents(node.left, node);
            populateParents(node.right, node);
        }
    }

    public int getSubNodesCount(TreeNode root) {
        if (root == null) {
            return 0;
        } else {
            return 1 + getSubNodesCount(root.left) + getSubNodesCount(root.right);
        }
    }

    public TreeNode deepCopy(TreeNode root) {
        if (root != null) {
            TreeNode newNode = new TreeNode(root.data);
            newNode.left = deepCopy(root.left);
            newNode.right = deepCopy(root.right);
            return newNode;
        } else {
            return null;
        }
    }



}
