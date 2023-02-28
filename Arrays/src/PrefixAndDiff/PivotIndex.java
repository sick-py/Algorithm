package PrefixAndDiff;

public class PivotIndex {
    int sum(int i, int j, int[] preSum) {
        return preSum[j + 1] - preSum[i];
    }
    public int pivotIndex(int[] nums) {
        int[] preSum = new int[nums.length + 1];
        for (int i = 1; i < preSum.length; i++) {
            preSum[i] = preSum[i - 1] + nums[i - 1];
        }

        if (sum(1, nums.length - 1, preSum) == 0) {
            return 0;
        }
        for (int i = 1; i < nums.length; i++) {
            if (sum(0, i - 1, preSum) == sum(i + 1, nums.length - 1, preSum)) {
                return i;
            }
        }
        return -1;
    }
}
