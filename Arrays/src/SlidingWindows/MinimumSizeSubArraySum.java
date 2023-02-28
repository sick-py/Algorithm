package SlidingWindows;

public class MinimumSizeSubArraySum {
    /**
     *
     * */
    public int minSubArrayLen(int target, int[] nums) {
        int res = Integer.MAX_VALUE;
        int left = 0, right = 0;
        int sum = 0;

        while (right < nums.length) {
            sum += nums[right];
            right++;
            while (sum >= target) {
                res = Math.min(right - left, res);
                sum -= nums[left];
                left++;
            }
        }
        if (res == Integer.MAX_VALUE) {
            return 0;
        }
        return res;
    }
}
