package BinarySearchTree;

import Main.TreeNode;

public class SortedArrayToBST {
    /**
     * The idea is to find the middle element of the array and make it the root of the tree, then perform the same operation on the left subarray for the root’s left child and the same operation on the right subarray for the root’s right child.
     * */
    public TreeNode sortedArrayToBST(int[] nums) {
    return build(nums, 0, nums.length - 1);
    }

    TreeNode build(int[] nums, int left, int right) {
        if (left > right) {
            return null;
        }
        int mid = left + (right - left) / 2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = build(nums, left, mid - 1);
        root.right = build(nums, mid + 1, right);
        return root;
    }
}
