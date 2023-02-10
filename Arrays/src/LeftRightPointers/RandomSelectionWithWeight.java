package LeftRightPointers;

import java.util.Random;

public class RandomSelectionWithWeight {
    /**
     * Assuming that the weight array input to you is w = [1,3,2,1]that we want the probability to conform to the weight, then we can abstract it and draw such a colored line segment according to the weight, If I randomly throw a stone on the line segment, which color the stone falls on, I will choose the weight index corresponding to the color, so is the probability of each index being selected related to the weight
     *
     * take a closer look at what this colored line segment looks like, it's prefix array
     *
     * So next, how to simulate throwing stones on the line segment?
     * Of course, it is a random number, such as the above prefix and array preSum, the value range is [1, 7], then I generate a random number in this range target = 5, as if randomly throwing a stone in this line segment,
     * There is another problem, preSum has no element 5, we should choose the smallest element that is greater than 5, which is 6, which is the index 3 of the preSum array :
     *
     * At this point, the core idea of this question is finished, mainly divided into several steps:
     *
     * 1. wGenerate a array preSum.
     *
     * 2. Generate a random number whose value preSumis within , and use the binary search algorithm to find the smallest element index greater than or equal to this random number.
     *
     * 3. Finally, subtract one from this index (because the prefix and the array have a one-bit index offset), it can be used as the index of the weight array, that is, the final answer.
     *
     *
     * To know the topics involving opening and closing intervals, index offsets, and binary search, you need to be very precise about the details of the algorithm, otherwise there will be various bugs that are difficult to troubleshoot.
     *
     * For example, this preSum array ,what range do you think the random number should take? Closed interval [0, 7]or left closed right open [0, 7)?
     *
     * Neither, it should be [1,7], because the prefix and 0 in the array are essentially placeholders , let’s experience it carefully:
     * {1} {2, 3, 4} {5, 6} {7} find them in preSum
     *
     * Next, preSum to find target the smallest element index greater than or equal to in , what kind of binary search should be used? Search the left boundary or search the right boundary?
     * You should actually use a binary search that searches the left boundary:
     *
     * */

    private int[] preSum;
    private Random rand = new Random();

    public RandomSelectionWithWeight(int[] w) {
        int n = w.length;
        preSum = new int[n + 1];
        preSum[0] = 0;
        for (int i = 1; i < preSum.length; i++) {
            preSum[i] = preSum[i - 1] + w[i - 1];
        }
    }

    public int pick() {
        int n = preSum.length;
        //choose a number in [1, preSum[-1]]
        int target = rand.nextInt(preSum[n - 1]) + 1;
        return leftBound(preSum, target) - 1;
    }

    private int leftBound(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                right = mid - 1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }
}
