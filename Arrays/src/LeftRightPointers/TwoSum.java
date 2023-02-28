package LeftRightPointers;

import java.util.HashSet;
import java.util.Set;

public class TwoSum {
    /**
     * In this solution, we’ll use the following algorithm to find a pair that adds to the target (say val).
     *
     * Scan the whole array once and store visited elements in a hash set.
     * During the scan, for every element e in the array, we check if val - e is present in the hash set. In other words, we check if val - e was already visited.
     * If val - e is found in the hash set, it means there is a pair (e, val - e) in the array whose sum is equal to the given val, and the function returns true.
     * If we exhaust all elements in the array and don’t find any such pair, the function returns false.
     *
     * time O(n) Space O(n)
     * */
    boolean find1(int[] nums, int val) {
        Set<Integer> foundValues = new HashSet<>();
        for (int i : nums) {
            if (foundValues.contains(val - i)) {
                return true;//becasue there will be a pair, i + j = val the first time val - i you store j, the second time you find j by val - i
            }
            foundValues.add(i);
        }
        return false;
    }

    /**
     * sort and two pointers, time O(nlogn) Space(1)
     * */
    int[] find2(int[] nums, int val) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int sum = nums[left] + nums[right];

            if (sum == val) {
                return new int[] {left + 1, right + 1};
            }

            if (sum < val) {
                left++;
            } else {
                right--;
            }
        }
        return new int[] {-1, -1};
    }
    class review {
        public int[] twoSum(int[] numbers, int target) {
            int[] res = new int[2];
            int left = 0, right = numbers.length - 1;
            while (left < right) {
                int sum = numbers[left] + numbers[right];
                if (sum == target) {
                    res[0] = left + 1;
                    res[1] = right + 1;
                    break;
                } else if (sum < target) {
                    left++;
                } else  {
                    right--;
                }
            }
            return res;
        }
    }
}
