public class MaxSubArray {
    /**
     * To solve this problem, dynamic programming techniques can also be used, but the definition of dpthe array is quite special. According to our conventional dynamic programming ideas, dpthe array :
     * nums[0..i]The "largest subarray sum" in is dp[i] .
     * In fact, it is not possible, because the sub-arrays must be continuous. According to our current dparray definition, there is no guarantee nums[0..i]that the largest sub-array in nums[i+1]is adjacent to , and there is no way dp[i]to deduce dp[i+1].
     *
     * Therefore, it is incorrect to say that we define dpthe array in above way
     *
     * this way works
     * The Maximum Subarray Sum ending with nums[i] is dp[i].
     *
     * Under this definition, if you want to get the "maximum sub-array sum" of the entire numsarray , you cannot return it directly dp[n-1], but need to traverse the entire dparray :
     *
     * It can be done, there are two "choices", either to connect with the previous adjacent sub-arrays to form a larger sub-array, or start from itself
     * */
    int maxArray(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        dp[0] = nums[0];
        for (int i = 1; i < n; i++) {
            dp[i] = Math.max(dp[i - 1] + nums[i], nums[i]);
        }
        int res = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            res = Math.max(res, dp[i]);
        }
        return res;
    }

    /** Kadane's algorithm to solve maxArray
     * Kadane's algorithm is a greedy/dynamic programming algorithm that can be used on array problems to bring the time complexity down to O(n). It is used to calculate the maximum sum subarray ending at a particular position.
     * */
    int maxSubArray(int[] nums) {
        if (nums.length == 1) return nums[0];
        int currentSum = 0; //this is the definition of DP, currentsum is the max sum of the subArray end with current number
        int max = Integer.MIN_VALUE;

        for (int n : nums) {
            currentSum += n;
            max = Math.max(currentSum, max);
            if (currentSum < 0) {
                currentSum = 0; // don't add up the previous sum in next iteration, because <0 only make the sum smaller
            }
        }
        return max;
    }

    /**
     * if you get the max subarray in the array, the left subarray from end to the head is the minimum, reverse it, find the min
     * */
    int maxArrayCircle(int[] nums) {
        if (nums.length == 1) return nums[0];
        int curMax = 0, curMin = 0;
        int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;

        for (int n : nums) {
            curMax += n;
            max = Math.max(max, curMax);

            if (curMax < 0) {
                curMax = 0;
            }
            //System.out.printf("for int %d, curmax == %d\n", n, curMax);

            curMin += n;
            min = Math.min(curMin, min);
            if (curMin > 0) {
                curMin = 0;
            }
        }

        int sum = 0;
        for (int n : nums) {
            sum += n;
        }

        if (max < 0) { //in this case, all the elements are smaller than 0, edge case if all the values are < 0
            int res = Integer.MIN_VALUE;
            for (int n : nums) {
                res = Math.max(res, n);
            }
            return res;
        }

        int res = Math.max(max, sum - min);

        return res;
    }


}
