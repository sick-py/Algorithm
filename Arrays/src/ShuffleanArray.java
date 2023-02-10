import java.util.Random;

public class ShuffleanArray {
    /**
     * Given an integer array nums, write an algorithm to shuffle the array randomly. All permutations of the array should be equally likely.
     *
     * Implement three functions of the Solution class:
     *
     * Initializes the object with the integer array nums.
     * Resets the array to its original configuration and returns it.
     * Returns a random shuffling of the array.
     *
     * A useful analogy is to think of the indices of the array as if they were picked from a bag at random, while at the same time maintaining a current index into the input array. Every time we pick an index at random from the bag, we swap the element at that position in the array with the element at the current index.
     *
     * The direct implementation of this approach requires us to keep track of which indices were already picked from the bag to avoid picking them repeatedly. This solution can impose a cost, one possibility is given below:
     *
     * We maintain two parts of the array:
     * 1.The already shuffled part
     * 2.The un-shuffled part
     * The un-shuffled part is at the beginning, and the shuffled part is the end of the array. We draw an element from the un-shuffled part at every iteration and swap it with the current element, effectively removing the drawn element and adding the replaced element to the bag.
     *
     * This ensures that we do not draw the same element repeatedly from the bag. Then, we reduce the size of the bag to account for the reduction in the size of the un-shuffled part. We continue this way and generate the whole shuffled array and then return it.
     * */
    private int[] original;
    Random random;

    public  ShuffleanArray(int[] nums) {
        original = nums;
        random = new Random(System.currentTimeMillis());
    }

    public int[] reset() {
        return original;
    }

    private void swapAt(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public int[] shuffle() {
        int[] shuffled = new int[original.length];
        System.arraycopy(original, 0, shuffled, 0, original.length);
        int leftSize = original.length;
        for (int i = original.length - 1; i >= 0; i--) {
            //draw from the bag at random
            int j = random.nextInt(200000) % leftSize;

            swapAt(shuffled, i, j);
            leftSize--;
        }
        return shuffled;
    }
}
