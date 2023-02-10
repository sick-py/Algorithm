package BinarySearchTree;

import Main.TreeNode;

public class TheKthSmallest {

    int res = 0;
    int rank = 0;

    void traverse(TreeNode root, int k) {
        if (root == null) {
            return;
        }

        traverse(root.left, k);
        rank++;
        if (rank == k) {
            res = root.data;
            return;
        }
        traverse(root.right, k);
    }
    /**
     * This question is finished, but I still need to say a few more words, because this solution is not the most efficient solution, but only applies to this question.
     *
     * If we follow the method we just mentioned and use the property of "BST in-order traversal is the result of ascending order sorting", each time we look for the ksmallest element, we need to do in-order traversal once. The worst time complexity is O(N)the Nnumber of BST nodes .
     *
     * You must know that the nature of BST is very powerful. For an improved self-balancing BST like red-black tree, adding, deleting, checking and modifying are O(logN)all complexities. If you count as the ksmallest element, the time complexity is even higher O(N), which is a bit inefficient.
     *
     * Therefore, the best algorithm for calculating the ksmallest element is definitely logarithmic complexity, but this depends on how much information is recorded by the BST node.
     *
     * So back to this question, if you want to find the ksmallest element, or find kthe element with a rank of , if you want to achieve logarithmic complexity, the key is that each node must know its own rank.
     * For example, if you ask me to find k , and the current node knows its rank m, then I can compare k and m:
     * 1. If m == k, obviously k, , just return the current node.
     * 2. If k < m, then it means that k the element is in the left subtree, so you can go to the left subtree to search for the k element.
     * 3. If k > m, then it means that kthe element is in the right subtree, so you can go to the right subtree to search for the k - m - 1element.
     * This reduces the time complexity O(logN)
     *
     * So, how to let each node know its own ranking?
     * This is what we said before, the need to maintain additional information in the binary tree nodes. Each node needs to record how many nodes there are in the binary tree rooted at itself.
     *
     * Of course, the sizefield needs to be maintained correctly when adding or deleting elements.
     * */
}
