package LCA;

import Main.TreeNode;

public class main {
    /**
     * this is the process of git merge.
     *
     * let's find the LCA from shallow to deep
     *
     * 0.find an element
     * You are given a root node of a binary tree without repeated elements and a target value , please write a function to find the val
     * */
    TreeNode find0(TreeNode root, int val) {
        if (root == null) {
            return null;
        }

        if (root.data == val) {
            return root;
        }

        TreeNode left = find0(root.left, val);
        if (left != null) {
            return left;
        }

        TreeNode right = find0(root.right, val);
        if (right != null) {
            return right;
        }
        return null;
    }

    /**
     * This code can also achieve the goal, but the actual operation efficiency will be lower. The reason is very simple. If you can find the target node in the left subtree, is there any need to find it in the right subtree? no need. But this code will still go to the right subtree to find a circle, so the efficiency is relatively poor.
     *
     * Going a step further, I moved root.valthe judgment of from the preorder position to the postorder position:
     * */
    TreeNode find1(TreeNode root, int val) {
        if (root == null) {
            return null;
        }
        // preOrder
        if (root.data == val) {
            return root;
        }

        TreeNode left = find1(root.left, val);
        TreeNode right = find1(root.right, val);

        return left != null ? left : right;
    }

    /**
     * This code is equivalent to you first go to the left and right subtrees to find, and then check root, you can still achieve the goal, but the efficiency will be further reduced. Because this way of writing will inevitably traverse every node of the binary tree .
     *
     * For the previous solution, you check rootat the preorder position. If the value of the input binary tree root node happens to be the target value val, then the function ends directly, and other nodes do not need to be searched at all.
     *
     * But if you judge in the post-order position, then even if the root node is the target node, you have to go to the left and right subtrees to traverse all the nodes to judge.
     * */
    TreeNode find2(TreeNode root, int val) {
        if (root == null) {
            return null;
        }

        TreeNode left = find2(root.left, val);
        TreeNode right = find2(root.right, val);

        // postOrder
        if (root.data == val) {
            return root;
        }

        return left != null ? left : right;
    }

    /**
     * Finally, let me change the title again. Now asking you to find a node whose value is val1 or val2 , the function signature is as follows:
     * */
    TreeNode findTwo(TreeNode root, int val1, int val2) {
        if (root == null) {
            return null;
        }

        if (root.data == val1 || root.data == val2) {
            return root;
        }

        TreeNode left = findTwo(root.left, val1, val2);
        if (left != null) {
            return left;
        }

        return findTwo(root.right, val1, val2);
    }

    /** Now let's get real
     * Given your input a binary tree without duplicate values , and two nodes  existing in the tree , please calculate the nearest common ancestor node of p and q.
     *
     * Two cases:
     * 0.one of them is the LCA
     * 1.they have common parent
     *
     * The nearest common ancestor of two nodes is actually the intersection point of the "extension line" from these two nodes to the root node, so for any node, how can it know whether it pis qthe nearest common ancestor of and ?
     * */

    /**
     * Because the topic says that pand qmust exist in the binary tree (this is very important), so even if we encounter qand return directly without traversing at all, we can still conclude pthat qunder qis the LCAnode .
     * */
    TreeNode findLCA(TreeNode root, int val1, int val2) {
        if (root == null) {
            return null;
        }

        if (root.data == val1 && root.data == val2) {
            return root; // preOrder case 0
        }

        TreeNode left = findLCA(root.left, val1, val2);
        TreeNode right = findLCA(root.right, val1, val2);

        //postOrder for case 1
        if (left != null && right != null) {
            return root; //In the post-order position of the find function , if leftboth and right are found to be non-empty, it means that the current node is a LCAnode, which solves the first case:
        }

        return left != null ? left : right;
    }

    /**
     *  However, it should be noted that the titles of these two questions clearly tell us that these nodes must exist in the binary tree. If there is no such prerequisite, the code needs to be modified .
     *
     * But for this question, pand qdo not necessarily exist in the tree, so you can't return directly when you encounter a target value, but you should perform a complete search on the binary tree (traversing every node), if found por qdoes not exist in the tree , then it does not LCAexist .
     *
     * Recalling the writing methods of several find functions , which writing method can completely search the binary tree
     *
     * Then solving this problem is similar, we only need to put the judgment logic of the pre-order position in the post-order position:
     * */
    TreeNode findAll(TreeNode root, int val) {
        if (root == null) {
            return null;
        }

        TreeNode left = findAll(root.left, val);
        TreeNode right = findAll(root.right, val);

        if (root.data == val) {
            return root;
        }

        return left != null ? left : right;
    }

    boolean foundP = false, foundQ = false;
    TreeNode findMay(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode res = findMayRec(root, p.data, q.data);
        if (!foundP || !foundQ) {
            return null;
        }

        return res;
    }

    private TreeNode findMayRec(TreeNode root, int val1, int val2) {
        if (root == null) {
            return null;
        }
        TreeNode left = findMayRec(root.left, val1, val2);
        TreeNode right = findMayRec(root.right, val1, val2);

        //if this node is LCA
        if (left != null && right != null) {
            return root;
        }

        //if the node is target number
        if (root.data == val1 || root.data == val2) {
            if (root.data == val1) foundP = true;
            else foundQ = true;
            return root;
        }

        return left != null ? left : right;
    }
}
