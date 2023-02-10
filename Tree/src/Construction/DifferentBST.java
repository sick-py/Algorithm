package Construction;

import Main.TreeNode;

import java.util.LinkedList;
import java.util.List;

public class DifferentBST {
    /**
     * The first question is the 96th question "Different Binary Search Trees". Enter a positive integer nfor you, please calculate and store{1,2,3...,n}these values.
     * For example, for input n = 3, the algorithm returns 5, because there are 5 different BST structure storages as follows {1,2,3}:
     *
     * As I said before in the first issue of Brushing Binary Trees by Hand, the key to the binary tree algorithm is to clarify what the root node needs to do. In fact, BST is a special binary tree, and the core idea is the same.
     * For example, such as input to the algorithm n = 5, that is to say, use{1,2,3,4,5}these numbers to construct the BST.
     * First of all, how many situations are there in total for the root node of this BST?
     * Obviously there are 5 cases, right, because each number can be used as a root node.
     *
     * For example, if we fix it 3as the root node, how many different BSTs can there be under this premise?
     * According to the characteristics of BST, the value of the left subtree of the root node is smaller than the value of the root node, and the value of the right subtree is larger than the value of the root node.
     * So if it is fixed 3as the root node, the left subtree node is {1,2}the combination of and the right subtree is {4,5}the combination.
     * The product of the number of combinations in the left subtree and the number of combinations in the right subtree is the number3 of BSTs used as the root node.
     * */
    //return the numbers of BST with nodes between [left, right]
    int count0(int left, int right) {
        //base case
        //Pay attention to the base case. Obviously, when the lo > hiclosed interval [lo, hi]must be an empty interval, it corresponds to the empty node null. Although it is an empty node, it is also a case, so it must return 1 instead of 0.
        if (left > right) return  1;

        int res = 0;
        for (int i = left; i <= right; i++) {
            int leftCount = count(left, i - 1);
            int rightCount = count(i + 1, right);
            res += leftCount * rightCount;
        }
        return res;
    }

    //The previous questions related to dynamic programming have talked about the method of eliminating overlapping sub-problems many times, which is nothing more than adding a memo:
    // 备忘录
    int[][] memo;

    int numTrees(int n) {
        // 备忘录的值初始化为 0
        memo = new int[n + 1][n + 1];
        return count(1, n);
    }

    int count(int lo, int hi) {
        if (lo > hi) return 1;
        // 查备忘录
        if (memo[lo][hi] != 0) {
            return memo[lo][hi];
        }

        int res = 0;
        for (int mid = lo; mid <= hi; mid++) {
            int left = count(lo, mid - 1);
            int right = count(mid + 1, hi);
            res += left * right;
        }
        // 将结果存入备忘录
        memo[lo][hi] = res;

        return res;
    }

    /**
     * So, if you are given an advanced question, not only let you calculate several different BSTs, but ask you to construct all effective BSTs, how to implement this algorithm?
     *
     * This question is the 95th question "Different Binary Search Tree II", which allows you to build all BSTs. The function signature is as follows:
     * */
    public List<TreeNode> generateTrees(int n) {
        if (n == 0) return new LinkedList<>();
        // 构造闭区间 [1, n] 组成的 BST
        return build(1, n);
    }

    /* 构造闭区间 [lo, hi] 组成的 BST */
    List<TreeNode> build(int lo, int hi) {
        List<TreeNode> res = new LinkedList<>();
        // base case
        if (lo > hi) {
            res.add(null);
            return res;
        }

        // 1、穷举 root 节点的所有可能。
        for (int i = lo; i <= hi; i++) {
            // 2、递归构造出左右子树的所有有效 BST。
            List<TreeNode> leftTree = build(lo, i - 1);
            List<TreeNode> rightTree = build(i + 1, hi);
            // 3、给 root 节点穷举所有左右子树的组合。
            for (TreeNode left : leftTree) {
                for (TreeNode right : rightTree) {
                    // i 作为根节点 root 的值
                    TreeNode root = new TreeNode(i);
                    root.left = left;
                    root.right = right;
                    res.add(root);
                }
            }
        }

        return res;
    }
}
