import java.util.Random;

public class ShuffleArray {
    int[] original;
    Random random;
    public ShuffleArray(int[] nums) {
        original = nums;
        random = new Random(System.currentTimeMillis());
    }

    public int[] reset() {
        return original;
    }

    public int[] shuffle() {
        int[] shuffled = new int[original.length];
        System.arraycopy(original, 0, shuffled, 0, original.length);
        int left = original.length;
        for (int i = left - 1; i >= 0; i--) {
            //draw from the bag at random
            int j = random.nextInt(200000) % left;

            swap(shuffled, i, j);
            left--;
        }
        return shuffled;
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
