package backpack;

public class SplitEqualSubset {
    /**
     * Input a non-empty array containing only positive integers nums. Please write an algorithm to judge whether the array can be divided into two subsets so that the sum of the elements of the two subsets is equal.
     *
     * You are given N items and W bag, each item has two attributes of weight and value. Among them, the weight of the if irst item is wt[i], and the value is val[i]. Now let you use this backpack to pack items, what is the maximum value that can be loaded?
     *
     * So for this problem, we can first sum the sets and get sum, and turn the problem into a knapsack problem:
     * Given N items and sum / 2 bag, each item weighs nums[i]. Now let you pack things, is there a way to pack that can just fill the backpack ?
     * */

    /**
     * The first step is to clarify two points, "state" and "selection" .
     * this foreword Classical Dynamic Programming: The Knapsack ProblemIt has been explained in detail, the state is "the capacity of the backpack" and "selectable items", and the choice is "put in the backpack" or "not put in the backpack".
     *
     * The second step is to clarify the definition of the dp array .
     * According to the routine of the knapsack problem, the following definition can be given:
     * dp[i][j] = x Indicates that, for the i previous item ( i counting from 1), j when , if xis true, it means that the backpack can be filled exactly, and if xis false, it means that the backpack cannot be filled exactly .
     *
     * For example, if dp[4][9] = true, the implication is: for a knapsack with a capacity of 9, there is a way to fill the knapsack exactly full if only the first 4 items are used.
     *
     * The third step is to think about the logic of state transition based on "choice" .
     * Recalling the meaning of the dp array dp[i][j]following state transition can be obtained according to the "selection" pair:
     * If you don't nums[i]count into the subset, or if you don't put i the item into the backpack , then whether the backpack can be filled exactly depends on the previous state dp[i-1][j]and inherits the previous result.
     * If nums[i]counted into the subset, or if you put ithis item into the knapsack , then whether the knapsack can be filled exactly depends on the state dp[i-1][j-nums[i-1]].
     *
     * PS: Since in the dp array definition istarts counting from 1, and the array index starts from 0, so the weight iof item should be nums[i-1], don't get confused.
     * */

    boolean canPartition(int[] nums) {
        int sum = 0;
        for (int num : nums) sum += num;
        if (sum % 2 != 0) return false;
        int n = nums.length;
        sum = sum / 2;
        boolean[][] dp = new boolean[n + 1][sum + 1];
        //base case
        for (int i = 0; i <= n; i++) {
            dp[i][0] = true;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= sum; j++) {
                if (j - nums[i - 1] < 0) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j] || dp[i - 1][j - nums[i - 1]];
                }
            }
        }
        return dp[n][sum];
    }

    /** Space compress
     * Going a step further, is it possible to optimize this code? Note that dp[i][j]all are dp[i-1][..]transferred through the previous line , and the previous data will no longer be used.
     * So we can Dimensionality reduction strike on dynamic programming, to compress the two-dimensional dparray into one-dimensional, saving space complexity:
     *
     * In fact, this code is exactly the same as the previous solution. It only operates on a row of dparrays , and ieach round of iteration dp[j]is actually equivalent dp[i-1][j], so only one-dimensional arrays are enough.
     *
     * The only thing to note is that jit should be traversed from back to front, because each item (or number) can only be used once, so as not to affect other results from previous results .
     * */
    boolean canP(int[] nums) {
        int sum = 0;
        for (int i : nums) sum += i;
        if (sum % 2 != 0) return false;
        sum = sum / 2;
        int n  = nums.length;
        boolean[] dp = new boolean[sum + 1];

        dp[0] = true;

        for (int i = 1; i <= n; i++) {
            for (int j = sum; j >= 1; j--) {
                if (j - nums[i - 1] < 0) {
                    dp[j] = dp[j];
                } else {
                    dp[j] = dp[j] || dp[j - nums[i - 1]];
                }
            }
        }
        return dp[sum];
    }

    class review {
        public boolean canPartition(int[] nums) {
            int sum = 0;
            for (int i : nums) {
                sum += i;
            }
            if (sum % 2 != 0) {
                return false;
            }
            sum /= 2;
            boolean[][] dp = new boolean[nums.length + 1][sum + 1];
            //base case //pay attention to definition
            for (int i = 0; i <= sum; i++) {
                dp[i][0] = true;
            }

            for (int i = 1; i < nums.length + 1; i++) {
                for (int j = 1; j <= sum; j++) {
                    if (j < nums[i - 1]) {
                        dp[i][j] = dp[i - 1][j];
                    } else {
                        dp[i][j] = dp[i - 1][j] || dp[i - 1][j - nums[i - 1]];
                    }
                }
            }
            return dp[nums.length][sum];
        }
    }
}