package LeftRightPointers;

public class BinarySearch {
    /**
     * We are given an array of integers, nums, sorted in ascending order, and an integer value, target. If the target exists in the array, return its index. If the target does not exist, then return -1.
     *
     * The binary search divides the input array by half at every step. After every step, either we find the index we are looking for, or we discard half of the array.
     * */

    static int search(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] == target) {
                return mid;
            }

            else if (nums[mid] < target) {
                left = mid + 1;
            }

            else {
                right = mid - 1;
            }
        }
        return -1;
    }

    /**
     * We’re given a sorted array of integers, nums, and an integer value, target. Return the low and high index of the given target element. If the indexes are not found, return -1.
     *
     * Let’s look at the algorithm for finding the low index:
     *
     * At every step, consider the array between low and high indices and calculate the mid index.
     * If the element at the mid index is less than the target, the value of low becomes mid + 1 (to move towards the start of range). The value of high remains the same.
     * If the element at mid is greater or equal to the target, the value of high becomes mid - 1. The value of low remains the same.
     * When the value of low is greater than high, low would point to the first occurrence of the target.
     * If the element at low does not match the target, return -1. It means that the target doesn’t exist in the array.
     * */

    /**
     * When the target element target does not exist in the array nums, the return value of the binary search that searches the left boundary can be interpreted in the following ways :
     *
     * 1. The returned value is the smallest in the nums that are bigger or equal to the target.
     *
     * 2. The returned value is the index position that should be inserted in for the target .
     *
     * 3. The value returned is the numbers of elements in the nums that are less than target.
     *
     * For example, when searching in an ordered array nums = [2,3,5,7], target = 4 the binary algorithm for searching the left boundary will return 2, and the above statement you brought in is correct.
     * */

    public static void main(String args[]) {
        int[] nums = new int[]{1, 2, 3, 3, 3, 5, 7};
        System.out.println(findLeft(nums, 3));
        System.out.println(findRight(nums, 3));

    }
    public static int findLeft(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) { //when left = right + 1
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
               right = mid - 1;
            }
            else if (nums[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
            System.out.printf("left: %d, right %d", left, right);
        }

        if (left < nums.length && nums[left] == target) {
            return left;
        }
        return -1;
    }

    static int findRight(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int midValue = nums[mid];

            if (midValue == target) {
                left = mid + 1;
            } else if (midValue > target) {
                right = mid - 1;
            } else if (midValue < target) {
                left = mid + 1;
            }
        }
        if (right == -1) {
            return -1;
        }

        if (right < nums.length && nums[right] == target) {
            return right;
        }

        return -1;
    }

    Boolean isBadVersion(int a) {
        return true;
    }
    public int firstBadVersion(int n) {
        int left = 1, right = n;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (isBadVersion(mid)) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
}
