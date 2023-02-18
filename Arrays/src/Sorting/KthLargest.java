package Sorting;

import java.util.PriorityQueue;

public class KthLargest {
    //O(nlogk)
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> minHeap =
                new PriorityQueue<>((a,b)->(a - b));
        for (int n : nums) {
            minHeap.add(n);
        }

        while (minHeap.size() > k) {
            minHeap.poll();
        }
        return minHeap.peek();
    }

    public int findK(int[] nums, int k) {
        int left = 0, right = nums.length - 1;
        k = right + 1 - k;

        while (left <= right) {
            int p = part(nums, left, right);
            if (p == k) {
                return nums[p];
            } else if (p > k) {
                right = p - 1;
            } else {
                left = p + 1;
            }
        }
        return -1;

    }

    int part(int[] nums, int left, int right) {
        int key = nums[left];
        int start = left;
        while (left < right) {
            while (left <= right && nums[left] <= key) {
                left++;
            }

            while (right >= left && nums[right] > key) {
                right--;
            }

            if (left < right) {
                swap(nums, left, right);
            }
        }
        swap(nums, start, right);
        return right;
    }
    void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
