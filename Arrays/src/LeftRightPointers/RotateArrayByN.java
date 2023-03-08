package LeftRightPointers;

import java.util.List;

public class RotateArrayByN {
    /**
     * We’re given an array of integers, nums. Rotate the array by n elements, where n is an integer:
     *
     * For positive values of n, perform a right rotation.
     * For negative values of n, perform a left rotation.
     * Make sure we make changes to the original array.
     * */

    void reverse(List<Integer> nums, int start, int end) {
        while (start < end) {
            int temp = nums.get(start);
            nums.set(start, nums.get(end));
            nums.set(end, temp);
            start++;
            end--;
        }
    }

    void rotate(List<Integer> nums, int k) {
        int len = nums.size();
        k %= len;
        if (k < 0) {
            k += len;
        }
        reverse(nums, 0, len - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, len - 1);
    }

    class review {
        void rotateHelp(int[] nums, int i, int j) {
            while (i < j) {
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
                i++;
                j--;
            }
        }

        public void rotate(int[] nums, int k) {
            int n = nums.length;
            k = k % n;
            rotateHelp(nums, 0, n - 1);
            rotateHelp(nums, 0, k - 1);
            rotateHelp(nums, k, n - 1);
        }
    }
}
