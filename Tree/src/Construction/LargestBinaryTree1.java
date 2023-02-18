package Construction;

import Main.TreeNode;

public class LargestBinaryTree1 {
    /** Main idea
     * The construction problem of binary tree generally uses the idea of ​​"decomposition problem": construct the whole tree = root node + construct left subtree + construct right subtree.
     * */

    /**
     * the largest binary tree is the root is the biggest number, the left subtree is made of the numbers from left part(take the biggest number as the pivot,
     * Each binary tree node can be regarded as the root node of a subtree. For the root node, the first thing to do is of course to find a way to construct itself first, and then find a way to construct its own left and right subtrees.
     *
     * Therefore, we need to traverse the array to find the maximum value maxVal, so as to rootmake , and then maxVal recursively build the left and right arrays as root the left and right subtrees of .
     *
     * According to the example given in the topic, the input array is [3,2,1,6,0,5], for the root node of the whole tree, it is actually doing this:
     *
     * */

    TreeNode construct(int[] nums) {
        return rec(nums, 0, nums.length - 1);
    }

    TreeNode rec(int[] nums, int left, int right) {
        if (left > right) {
            return null;
        }
        //find the biggest
        int index = -1, maxVal = Integer.MIN_VALUE;
        for (int i = left; i <= right; i++) {
            if (maxVal < nums[i]) {
                index = i;
                maxVal = nums[i];
            }
        }

        TreeNode root = new TreeNode(maxVal);
        root.left = rec(nums, left, index - 1);
        root.right = rec(nums, index + 1, right);
        return root;
    }
}
