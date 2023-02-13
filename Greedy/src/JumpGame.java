import java.util.Arrays;

public class JumpGame {
    /**
     * How far can you jump at most through the jumping rules in the question ? Returns true if the last grid can be crossed, false otherwise.
     *
     * */
    boolean canJump(int[] nums) {
        int n = nums.length;
        int farthest = 0;
        for (int i = 0; i < n - 1; i++) {
            farthest = Math.max(farthest, i + nums[i]);
            if (farthest <= i) {
                return false;
            }
        }
        return farthest >= n - 1;
    }

    /**
     * Jump2
     *
     * The question now is, to ensure that you can jump to the last frame, how many times do you have to jump at least before you can jump over .
     *
     * Let's talk about the idea of ​​dynamic programming first. Using top-down recursive dynamic programming, you can define a dpfunction :
     * Jumping from index p to the last cell requires at least dp(nums, p) steps
     *
     * */
    int[] memo;
    int jump(int[] nums) {
        int n = nums.length;
        memo = new int[n];
        //because at most n steps, it is equal to INTMAX
        Arrays.fill(memo, n);

        return dp(nums, 0);
    }

    private int dp(int[] nums, int p) {
        int n = nums.length;
        if (p >= n - 1) {
            return 0;
        }

        if (memo[p] != n) {
            return memo[p];
        }
        int step = nums[p];
        for (int i = 0; i <= step; i++) {
            int sub = dp(nums, p + i);
            memo[p] = Math.min(memo[p], sub + 1);
        }
        return memo[p];
    }
}
