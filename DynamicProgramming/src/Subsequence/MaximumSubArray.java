package Subsequence;

public class MaximumSubArray {
    /**
     * Input an integer array numsfor you, please find a sub-array with the largest sum in it, and return the sum of this sub-array. The function signature is as follows:
     * Say input nums = [-3,1,3,-1,2,-4,2], the algorithm returns 5, because the sum [1,3,-1,2]of is 5.
     *
     * In fact, the first time I saw this question, the first thing I thought of was the sliding window algorithm, because as we said before, the sliding window algorithm is designed to deal with the substring/subarray problem. Isn’t it the subarray problem here?
     * 1. When should the window be enlarged?
     * 2. When should the window be reduced?
     * 3. When will the answer be updated?
     * because there is negative number, so it's not direct to answer these questions
     *
     * define:
     * nums[0..i]The "largest subarray sum" in is dp[i] .
     *
     * If defined in this way, the "maximum subarray sum" of the entire numsarray is dp[n-1]. How to find the state transition equation? According to the method of mathematical induction, assuming we know it dp[i-1], how to derive dp[i]it ?
     * So in the above situation, using mathematical induction, can you use dp[i]to deduce dp[i+1]?
     * In fact, it is not possible, because the sub-arrays must be continuous. According to our current dparray definition, there is no guarantee nums[0..i]that the largest sub-array in nums[i+1]is adjacent to , and there is no way dp[i]to deduce dp[i+1].
     *
     * So it is incorrect to say that we define the dparray in this way, and we cannot get a suitable state transition equation. For this type of subarray problem, we have to redefine the meaning of dpan array :
     *
     * The Maximum Subarray Sum ending with nums[i] is dp[i] .
     * */
    int maxSub(int[] nums) {
        int n = nums.length;
        if (n== 0) return 0;
        int[] dp = new int[n];
        dp[0] = nums[0];
        for (int i = 1; i < n; i++) {
            dp[i] = Math.max(nums[i], dp[i - 1] + nums[i]);
        }
        int res = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            res = Math.max(res, dp[i]);
        }
        return res;
    }

    int max(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        dp[0] = Math.max(0, nums[0]);
        for (int i = 1; i < n; i++) {
            dp[i] = Math.max(dp[i - 1] + nums[i], nums[i]);
        }

        int res = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            res = Math.max(res, dp[i]);
        }
        return res;
    }
}
