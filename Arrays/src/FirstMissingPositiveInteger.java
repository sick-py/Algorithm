public class FirstMissingPositiveInteger {
    /** Summary:
     *
     * it's the idea comes from the hashtable, key-value. and use the local spcae as key by using negative mark, and everytime we use it by using abs
     *
     * Given an unsorted integer array, nums, find the smallest positive integer that is missing from the array.
     * Implement a solution that takes O(n) time and constant space.
     *
     * First, we check the base case. To verify that the first missing positive integer is not 1, we check for its presence in the array. We use the array below as an example. Since 1 exists in the array, it’s not our missing positive integer:
     * Our missing positive integer cannot be less than zero or greater than the length of array + 1. So, we need to clean up the input array.
     * To respect the O(n) time complexity condition, we cannot pop or delete these values. So, instead, we replace 1 with all the non-positive numbers and all the numbers greater than the length of the array.
     *
     * Now, our array only contains integers ranging from 1 to the length of the array.
     * If we were allowed to use a hashmap that stores the relation number -> occurrence in the array, the solution would be simpler. But, to respect the constant space condition, we need a more efficient solution.
     *
     * We cannot use a hashmap but we can use the key-value idea to build some sort of mapping within the nums array. Since we know that all the values in nums are positive,
     *
     * we can use the index of the array as a key and the sign of the value at that index to indicate whether that value is present in the array.
     * The key idea is that if a number is negative at index i, it means that the value i is present in the array.
     *
     * As the value 0 is already replaced with 1, we do not need to look for it in the array. But our array can contain values ranging from 1 to the length of the array and if the length is present as a value in the array, we need an index whose value indicates the presence of this element. We decide to use the sign of the value at the 0
     * 0
     *  index for this purpose. As the length of the array, 10, is not present, we do not mark the element at nums[0] with a negative sign.
     *
     *  After marking the numbers present in the array, we can scan the array again and return the index with a positive number.
     * */

    public int first(int[] nums) {
        int len = nums.length;
        int contains = 0;
        for (int i = 0; i < len; i++) {
            if (nums[i] == 1) {
                contains++;
                break;
            }
        }

        if (contains == 0) {
            return 1;
        }

        for (int i = 0; i < len; i++) {
            if (nums[i] <= 0 || nums[i] > len) {
                nums[i] = 1;
            }
        }

        for (int i = 0; i < len; i++) {
            int a = Math.abs(nums[i]); //As we iterate through the array from left to right, for each value encountered, say a, we set the sign of the value at index a to negative. We also need to look out for duplicates and make sure that we do not change the sign back to positive on encountering a duplicate value.
            if (a == len) {
                nums[0] = - Math.abs(nums[0]);
            } else {
                nums[a] = - Math.abs(nums[a]);
            }
        }

        for (int i = 1; i < len; i++) {
            if (nums[i] > 0) {
                return i;
            }
        }

        if (nums[0] > 0) return len;
        return len + 1;
    }


}
