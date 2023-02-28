
import javax.swing.tree.TreeNode;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class HouseRobber {
    /**
     * robbery 1 198
     * There is a row of houses on the street, nums represented , and each element represents nums[i] the amount of cash in the house. iNow you are a professional thief, you want to steal as much cash as possible from these houses, however, adjacent houses cannot be stolen at the same time , otherwise the alarm will be triggered and you will be left alone.
     *
     * For example nums=[2,1,7,9,3,1], input, the algorithm returns 12, the thief can steal nums[0], nums[3], nums[5]three houses, and the sum of cash obtained is 2 + 9 + 1 = 12, which is the optimal choice.
     *
     * The topic is easy to understand, and the characteristics of dynamic programming are obvious. We have summarized in the previous article "Detailed Explanation of Dynamic Programming". To solve the dynamic programming problem is to find "state" and "choice", that's all .
     *
     * Imagine that you are the professional robber, walking through this row of houses from left to right, and there are two choices in front of each house : to rob or not to rob.
     * If you rob this house, then you must not be able to rob the next adjacent house, and you can only choose from the next house.
     *
     * If you don't grab this house, then you can go to the next house and continue to make choices.
     *
     * When you have passed the last house, you don't have to grab it, and the money you can grab is obviously 0 ( base case ).
     *
     * The above logic is very simple. In fact, the "state" and "choice" have been clarified: the index of the house in front of you is the state, and whether to grab or not is the choice .
     *
     * */
    int rob(int[] nums) {
        memo = new int[nums.length];
        Arrays.fill(memo, -1);
        return dp(nums, 0);
    }

    private int dp(int[] nums, int start) {
        if (start >= nums.length) {
            return 0;
        }
        int res = Math.max(
                dp(nums, start + 1),
                dp(nums, start + 2) + nums[start]
        );
        return res;
    }
    //and we can use memo to develop it
    int[] memo;
    //return nums[start, ...] max robbery
    int dp0(int[] nums, int start) {
        if (start >= nums.length) return 0;
        if (memo[start] != -1) {
            return memo[start];
        }

        memo[start] = Math.max(
                dp0(nums, start + 1),
                dp0(nums, start + 2) + nums[start]
        );
        return memo[start];
    }

    /** Robbery 2 213
     * Robbers still cannot rob adjacent houses, and the input is still an array, but tell you that these houses are not in a row, but in a circle .
     *
     * That is to say, now the first house and the last house are also equivalent to being adjacent and cannot be robbed at the same time. For example, if you input an array nums=[2,3,2], the result returned by the algorithm should be 3 instead of 4, because the beginning and the end cannot be robbed at the same time.
     *
     * First of all, the first and last rooms cannot be robbed at the same time, so there are only three different situations: either none of them are robbed; or the first room is robbed and the last room is not robbed;
     * That's simple, in these three situations, which one has the greatest result is the final answer! However, in fact, we don’t need to compare the first situations, we just need to compare the second situations and the third situations, because the choice of the house in these two situations is larger than that in the other situation, and the money in the house is a non-negative number, so there is more choice , the optimal decision result will definitely be bigger .
     * */
    int rob2(int[] nums) {
        int n = nums.length;
        if (n == 1) {
            return nums[0];
        }
        memo = new int[n - 1];
        Arrays.fill(memo, -1);
        return Math.max(dp2(nums, 0, n - 2),
                dp2(nums, 1, n - 1));
    }

    private int dp2(int[] nums, int start, int end) {
        if (start > end) {
            return 0;
        }
        if (memo[start] != -1) {
            return memo[start];
        }

        memo[start] = Math.max(
                dp2(nums, start + 1, end),
                dp2(nums, start + 2, end) + nums[start]
        );
        return memo[start];
    }

    /** House robber 3 337
     * Retweet question 337 "House robbery III" tried to change the trick again. The robber found that the house he was facing now was not a row or a circle, but a binary tree! The house is on the node of the binary tree, and the two connected houses cannot be robbed at the same time.
     *
     * The overall thinking has not changed at all, it is still the choice of grabbing or not grabbing, and go for the option with greater profits. We can even write the code directly according to this routine:
     * */
    class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int a) {
            val = a;
        }
    }
    Map<TreeNode, Integer> memory = new HashMap<>();
    int rob2(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (memory.containsKey(root)) {
            return memory.get(root);
        }

        //rob
        int robIt = root.val +
                (root.left == null ? 0 : rob2(root.left.left) + rob2(root.left.right)) +
                (root.right == null ? 0 : rob2(root.right.left) + rob2(root.right.right));

        //don't rob
        int noRob = rob2(root.left) + rob2(root.right);

        memory.put(root, Math.max(robIt, noRob));
        return memory.get(root);
    }
    class review{
        int[] memo;
        public int rob(int[] nums) {
            memo = new int[nums.length];
            Arrays.fill(memo, -1);
            return dp1(nums, 0);
        }

        private int dp1(int[] nums, int start) {
            if (start >= nums.length) {
                return 0;
            }
            if (memo[start] != -1) {
                return memo[start];
            }

            memo[start] = Math.max(
                    dp1(nums, start + 1),
                    dp1(nums, start + 2) + nums[start]
            );
            return memo[start];
        }
    }
}
