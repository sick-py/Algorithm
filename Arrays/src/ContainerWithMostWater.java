public class ContainerWithMostWater {
    /**
     * The further apart these two bars are, the more the area of the container is.
     *
     * We use two markers, left and right, representing the height array’s start and end, respectively. We also use a variable to keep track of the maximum area.
     *
     * Here is the optimized algorithm we use:
     *
     * Initialize the left and right markers with the two outermost exterior bars and the maximum area as zero.
     * Now, we iterate through the lines by moving the shortest height bar inwards until we find the maximum area.
     * After each iteration, we update the maximum area if we found a larger area container is found.
     * We stop iterating once the two bars pass each other.
     * */

    int max(int[] nums) {
        int max = 0, left = 0, right = nums.length - 1;

        while (left < right) {
            max = Math.max(max, Math.min(nums[left], nums[right]) * (right - left));
            if (nums[left] < nums[right]) {
                left++;
            }else {
                right--;
            }
        }
        return max;
    }
}
