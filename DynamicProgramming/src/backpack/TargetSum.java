package backpack;

import java.util.HashMap;

public class TargetSum {
    /**
     * We often said that the backtracking algorithm is somewhat similar to the recursive algorithm. If you really can’t figure out the state transition equation for some problems, trying to use the backtracking algorithm to solve it violently is also a smart strategy, which is better than not being able to write a solution.
     * So, what is the relationship between backtracking algorithm and dynamic programming? They both involve recursion, algorithm templates look alike, both involve making "choices", really like father and son.
     *
     * Give you an input of a non-negative integer array numsand a target value, now you can add a positive sign or a negative sign to target each element , please calculate how many combinations of signs can make the sum of the elements in is
     * For example, input nums = [1,3,1,4,2], target = 5, the algorithm returns 3, because there are the following 3 combinations that can make targetequal to 5:
     * -1+3+1+4-2=5
     * -1+3+1+4-2=5
     * +1-3+1+4+2=5
     * */

    /** Backtrack
     * The core of any algorithm is exhaustion, and the backtracking algorithm is a violent exhaustion algorithm.
     * The key is to figure out what "choice" is, and for this question, isn't "choice" obvious? For each number nums[i], we can choose to give a positive sign +or- a negative sign , and then use the backtracking template to enumerate all possible results, count how many combinations can be made up, target isn't it enough?
     *
     * The above backtracking algorithm can solve this problem, and the time complexity is O(2^N), which N is numsthe size of .
     * found that this backtracking algorithm is a binary tree traversal problem:
     * */

    int res = 0;
    int find0(int[] nums, int target) {
        if (nums.length == 0) return 0;
        backtack(nums, 0, target);
        return res;
    }

    private void backtack(int[] nums, int i, int remain) {
        if (i == nums.length) {
            if (remain == 0) {
                res++;
            }
            return;
        }

        //choose -
        remain += nums[i];
        backtack(nums, i + 1, remain);
        remain -= nums[i];

        //choose +
        remain -= nums[i];
        backtack(nums, i + 1, remain);
        remain += nums[i];
    }

    /** eliminate overlapping
     * How to spot overlapping subproblems? See if there may be duplicate "states". For recursive functions, the parameter that will change among the function parameters is "state", and for the backtrackfunction , the parameters that will change are i and remain, Therefore, the (i, remain)state can be optimized using the memoization technique:
     *
     *  we can convert the "state" into a string as the key of the hash table. This is a common trick.
     *
     * This solution eliminates many overlapping sub-problems through the memo, and the efficiency has been improved to a certain extent, but is this over?
     * */
    int find1(int[] nums, int target) {
        if (nums.length == 0) return 0;
        return dp(nums, 0, target);
    }

    //memory
    HashMap<String, Integer> memo = new HashMap<>();
    private int dp(int[] nums, int i, int remain) {
        if (i == nums.length) {
            if (remain == 0) return 1;
            return 0;
        }

        String key = i + "," + remain;
        if (memo.containsKey(key)) {
            return memo.get(key);
        }

        int res = dp(nums, i + 1, remain - nums[i]) + dp(nums, i - 1, remain + nums[i]);
        memo.put(key, res);
        return res;
    }

    /**
     * In fact, this problem can be transformed into a subset partition problem, and the subset partition problem is a typical knapsack problem.
     * First of all, if we nums divide into two subsets A and B, which respectively represent +the number allocated and the number allocated -, then they target have the following relationship with:
     *
     * sum(A) - sum(B) = target
     * sum(A) = target + sum(B)
     * sum(A) + sum(A) = target + sum(B) + sum(A)
     * 2 * sum(A) = target + sum(nums)
     *
     * In summary, it can be deduced sum(A) = (target + sum(nums)) / 2that the original problem is transformed into: How many subsets exist in A, so that the sum of the elements in A is (target + sum(nums)) / 2 ?
     *
     * in  the second step which is to clarify the definition of the dparray .
     * According to this definition, obviously dp[0][..] = 0, because there are no items, there is no way to pack the backpack at all; but dp[0][0]should be an exception, because if the maximum load of the backpack is 0, "nothing" can be regarded as a packing method, ie dp[0][0] = 1.
     * */

    int find2(int[] nums, int target) {
        int sum = 0;
        for (int i : nums) sum += i;
        if (sum < Math.abs(target) || (sum + target) % 2 == 1) {
            return 0;
        }
        return backpack(nums, (sum + target) / 2);
    }

    private int backpack(int[] nums, int sum) {
        int n = nums.length;
        int[][] dp = new int[n + 1][sum + 1];
        dp[0][0] = 1;

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= sum; j++) {
                if (j - nums[i - 1] < 0) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j] + dp[i - 1][j - nums[i - 1]];
                }
            }
        }
        return dp[n][sum];
    }

    class review {
        int backpack0(int[] nums, int sum) {
            int n = nums.length;
            int[][] dp = new int[n  + 1][sum + 1];
            dp[0][0] = 1;

            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= sum; j++) {
                    if (j - nums[i - 1] < 0) {
                        dp[i][j] = dp[i - 1][j];
                    } else {
                        dp[i][j] = dp[i - 1][j] + dp[i - 1][j - nums[i - 1]];
                    }
                }
            }
            return dp[n][sum];
        }


    }
}
