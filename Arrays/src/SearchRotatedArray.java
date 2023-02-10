public class SearchRotatedArray {
    /**
     * We’re given a sorted integer array, nums and an integer value, target. The array is rotated by some arbitrary number. Search the target in this array. If the target does not exist then return -1.
     * first find the mid and then
     * always find the monotonous part, else for the broken part
     * */
    int search(int[] nums, int target) {
        int start = 0, end = nums.length - 1;
        if (start > end) {
            return -1;
        }

        while (start <= end) {
            int mid = start + (end - start);
            if (nums[mid] == target) {
                return mid;
            }

            //start to mid is sorted
            if (nums[mid] >= nums[start]) {
                if (nums[start] <= target && target < nums[mid]) {
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
            }

            //mid to end is sorted
            else {
                if (nums[mid] < target && target <= nums[end]) {
                    start = mid + 1;
                } else {
                    end = mid - 1;
                }
            }
        }
        return -1;
    }
}
