public class MinimumSizeSubarraySum {
    /** Summary:
     * it's a slide window, left and i(right)
     *
     *
     * Given an array of positive integers, nums, along with a positive integer, target. Find the length of the shortest contiguous subarray whose sum is greater than or equal to the target. If there is no such subarray, return 0 instead.
     *
     * We use the result variable to store the size of the minimum subarray.
     * We use a left pointer to keep track of the left end of the subarray. Initially, we’ll set it to 0.
     * We loop over the input array, and in each loop, we add up the array element in the variable sum.
     * Now, suppose the sum of our current sub-array exceeds or equals the target. In that case, we try to find a smaller sub-array that meets the same condition (sum exceeds or equals the target). We advance the left pointer of the sub-array and check the sum from that point to the right-side boundary of the sub-array.
     * We repeat the process till we reach the end of the array, and if we find a minimum subarray, we return its length. Otherwise, we return 0.
     **/
    int min(int s, int[] nums) {
        int res = Integer.MAX_VALUE;

        int left = 0,  sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];

            while (sum >= s) {
                int currentSize = (i + 1) - left;
                res = Math.min(res, currentSize);
                sum -= nums[left];
                left++;
            }
        }

        if (res != Integer.MAX_VALUE) {
            return  res;
        } else {
            return 0;
        }
    }
}
