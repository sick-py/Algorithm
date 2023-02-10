public class SquareArray {
    /**
     * We can use two pointers to read the positive and negative parts of the array - one pointer, right, in the positive direction and another, left, in the negative direction.
     *
     * We read in two increasing arrays (the squares of the elements), so we can merge these arrays using a two-pointer technique.
     * */

    int[] sort(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];

        int left = 0, right = n - 1;

        for (int i = n - 1; i >= 0; i--) {
            int square;
            if (Math.abs(nums[left]) < Math.abs(nums[right])) {
                square = nums[right--];
            } else {
                square = nums[left++];
            }
            res[i] = square * square;
        }
        return res;
    }
}
