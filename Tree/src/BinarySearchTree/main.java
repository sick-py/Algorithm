package BinarySearchTree;

import Main.TreeNode;

import java.util.*;

public class main {
    /**
     * First of all, the characteristics of BST should be familiar to everyone:
     * 1. For each node of BST, the value of the left subtree node is smaller than the value of root, and the value of the right subtree node is larger than the value of root.
     * 2. For each node of BST, its left subtree and right subtree are both BST.
     *
     * the inorder traversal results of BST are ordered (ascending) .
     *
     * A lot of data structures directly based on BST include AVL tree, red-black tree, etc., which have self-balancing properties and can provide logN-level addition, deletion and query. Improve efficiency; B+ tree, line segment tree and other structures are designed based on the idea of ​​BST.
     *
     * Let’s briefly summarize, BST-related problems,
     *
     * either use the characteristics of BST’s left small and right large to improve the efficiency of the algorithm,
     *
     * or use the characteristics of in-order traversal to meet the requirements of the topic, that’s all.
     * */

    /** search one
     *
     * */
    TreeNode search(TreeNode root, int k) {
        if (root == null) {
            return null;
        }

        if (root.data == k) {
            return root;
        }

        if (root.data < k) {
            return search(root.right, k);
        }

        if (root.data > k) {
            return search(root.left, k);
        }
        return null;
    }

    /** insert
     * The operation on the data structure is nothing more than traversal + access, traversal is "finding", and accessing is "changing". Specific to this problem, inserting a number is to find the insertion position first, and then perform the insertion operation.
     *
     * Once "change" is involved, it is similar to the construction of a binary tree. The function must return the TreeNode and receive the return value of the recursive call.
     *
     *
     * */
    TreeNode insert(TreeNode root, int val) {
        //find the empty place to insert the node
        if (root == null) return new TreeNode(val);

        if (root.data < val) {
            root.right = insert(root.right, val);
        }

        if (root.data > val) {
            root.left = insert(root.left, val);
        }

        //if root.data == val usually there is no duplicate node in BST
        return root;
    }

    /** delete
     *
     * This problem is a bit complicated, similar to the insert operation, first "find" and then "change", first write out the frame and then say:
     *
     * After finding the target node, for example, a node A, how to delete this node is a difficult point. Because the properties of BST cannot be destroyed while deleting nodes. There are three situations:
     * 0.A Exactly the end node, both child nodes are empty, then it can be done on the spot
     * 1.A There is only one non-empty child node, then it will let this child take its place.
     * 2.A There are two child nodes, in order not to destroy the nature of BST, A must find the largest node in the left subtree, or the smallest node in the right subtree to replace itself. We explain it in the second way.
     * */
    TreeNode delete(TreeNode root, int val) {
        if (root == null) return null;

        if (root.data == val) {
            //case 0, 1
            if (root.left == null) return root.right;
            if (root.right == null) return root.left;

            //case2
            TreeNode minNode = getMin(root.right);
            root.right = delete(root.right, minNode.data);
            minNode.left = root.left;
            minNode.right = root.right;
            root = minNode;
        } else if (root.data > val) {
            root.left = delete(root.left, val);
        } else if (root.data < val) {
            root.right = delete(root.right, val);
        }
        return root;
    }

    private TreeNode getMin(TreeNode root) {
        while (root.left != null) {
            root = root.left;
        }
        return root;
    }

    class review{
        public class TreeNode {
      int val;
      TreeNode left;
     TreeNode right;
      TreeNode() {}
      TreeNode(int val) { this.val = val; }
      TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
          this.left = left;
          this.right = right;
            }
        }

        public TreeNode insertIntoBST(TreeNode root, int val) {
            if (root == null) return new TreeNode(val);
            if (root.val > val) {
                 root.left = insertIntoBST(root.left, val);//pay attention to
            }
            else {
                 root.right = insertIntoBST(root.right, val);
            }

            return root;//pay attention to
        }

        public TreeNode deleteNode(TreeNode root, int key) {
            if (root == null) {
                return null;
            }

            if (root.val > key) {
                root.left = deleteNode(root.left, key);
            } else if (root.val < key) {
                root.right = deleteNode(root.right, key);
            } else if (root.val == key){//find the node
                if (root.left == null) {
                    return root.right;
                } else if (root.right == null) {
                    return root.left;
                } else {
                    TreeNode min = root;
                    while (min.left != null) {
                        min = min.left;
                    }

                    root.right = deleteNode(root.right, min.val); //pay attention here, you need to do this first
                    min.left = root.left;
                    min.right = root.right;
                    root = min;
                }
            }

            return root;
        }

        List<Integer> res = new LinkedList<>();
        public List<Integer> inorderTraversal(TreeNode root) {
            inOrder(root);
            return res;
        }

        private void inOrder(TreeNode root) {
            if (root == null) {
                return;
            }

            inOrder(root.left);
            res.add(root.val);
            inOrder(root.right);
        }

        int resK = 0;
        int rank = 0;
        public int kthSmallest(TreeNode root, int k) {
            traverse(root, k);
            return resK;
        }

        private void traverse(TreeNode root, int k) {
            if (root == null) {
                return;
            }

            traverse(root.left, k);
            rank++;
            if (rank == k) {
                resK = root.val;
            }
            traverse(root.right, k);
        }

        public List<List<Integer>> levelOrder(TreeNode root) {


            List<List<Integer>> res = new LinkedList<>();
            if (root == null) {
                return res;
            }
            Queue<TreeNode> q = new ArrayDeque<>();
            q.add(root);

            while (!q.isEmpty()) {
                int size = q.size();
                List<Integer> r = new LinkedList<>();
                for (int i = 0; i < size; i++) {
                    TreeNode node = q.poll();
                    r.add(node.val);
                    if (node.left != null) {
                        q.add(node.left);
                    }
                    if (node.right != null) {
                        q.add(node.right);
                    }
                }
                res.add(r);
            }
            return res;
        }

        public List<Integer> rightSideView(TreeNode root) {

            List<Integer> res = new LinkedList<>();
            if (root == null) {
                return res;
            }
            Queue<TreeNode> q = new ArrayDeque<>();
            q.add(root);

            while (!q.isEmpty()) {
                int size = q.size();
                for (int i = 0; i < size - 1; i++) {
                    TreeNode node = q.poll();
                    if (node.left != null) {
                        q.add(node.left);
                    }
                    if (node.right != null) {
                        q.add(node.right);
                    }
                }
                TreeNode node = q.poll();
                res.add(node.val);
                if (node.left != null) {
                    q.add(node.left);
                }
                if (node.right != null) {
                    q.add(node.right);
                }
            }
            return res;
        }


    }


}
