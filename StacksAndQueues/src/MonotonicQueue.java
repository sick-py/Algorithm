import java.util.LinkedList;

public class MonotonicQueue<E extends Comparable<E>> {
    /**
     * Our original simple implementation includes the implementation of maxthe method . The principle is to maintain a queue at the bottom layer maxq, and maintain the monotonous increase of elements in this queue from the tail to the head.
     *
     * */
    LinkedList<E> q = new LinkedList<>();
    LinkedList<E> maxq = new LinkedList<>();
    LinkedList<E> minq = new LinkedList<>();

    public void push(E element) {
        q.addLast(element);

        //maintain the maxq, remove all the elements that are smaller than elem
        while (!maxq.isEmpty() && maxq.getLast().compareTo(element) < 0) {
            maxq.pollLast();
        }
        maxq.addLast(element);

        while (!minq.isEmpty() && minq.getLast().compareTo(element) > 0) {
            minq.pollLast();
        }
        minq.addLast(element);
    }

    public E max() {
        //the element at the head is the last
        return maxq.getFirst();
    }

    public E min() {
        return minq.getFirst();
    }

    public E pop() {
        E deleteVal = q.pollFirst();
        assert deleteVal != null;

        //because it may delete the val during the push
        if (deleteVal.equals(maxq.getFirst())) {
            maxq.pollFirst();
        }

        if (deleteVal.equals(minq.getFirst())) {
            minq.pollFirst();
        }
        return deleteVal;
    }
    public int size() {
        return q.size();
    }
    /** The monotonic queue can be combined with the sliding window algorithm to quickly calculate the maximum value inside the window during the window sliding process.
     *
     * 1438
     * Given an array of integers nums, and an integer representing the limit limit, please return the length of the longest continuous subarray, the absolute difference between any two elements in the subarray must be less than or equal to limit. Returns if there is no subarray that satisfies the condition.
     *
     * Expand the window when the absolute value difference in the window does not exceed limit, and start shrinking the window when the newly added elements of the window make the absolute value difference exceed limit, the maximum width of the window is the length of the longest sub-array.
     * */
    public int longestSubArray(int[] nums, int limit) {
        int left = 0, right = 0;
        int n = nums.length;
        int windowSize = 0, res = 0;
        MonotonicQueue<Integer> window = new MonotonicQueue<>();
        while (right < n) {
            //enlarge the window
            window.push(nums[right]);
            right++;
            windowSize++;
            while (window.max() - window.min() > limit) {
                window.pop();
                left++;
                windowSize--;
            }
            res = Math.max(res, windowSize);
        }
        return res;
    }

    /** Monotonic stack + circular array 918. Maximum Sum of Circular Subarrays
     * Monotonic queues can also be used in ring array scenarios. The principle of monotone stack mentioned before that you can simulate a circular array nby doubling , but if you want to calculate the element sum of the sub-array in the circular array, you need monotone queue assistance, because you need to ensure that the length of the sub-array cannot exceed n.
     * Given na circular array of integers nums of length , numsreturns the maximum sum of non- empty subarrays of .
     *
     * A circular array means that the end of the array will be connected to the beginning in a ring. Formally, nums[i]the next element of is nums[(i + 1) % n], nums[i]and the previous element is nums[(i - 1 + n) % n].
     *
     * First of all, this question and 53. The method of dealing with circular arrays mentioned in 53. The largest subsequence sum is actually to double the size of the original array, so that the ring effect can be simulated.
     *
     * Then this question can also double numsthe array , calculate the prefix sum array preSum, and use a monotone queue with a nums.lengthfixed to calculate the maximum subarray sum in the ring array. See the code directly for the specific implementation.
     * */
    public int maxSubArraySum(int[] nums) {
        int n = nums.length;
        int[] preSum = new int[n * 2 + 1];
        preSum[0] = 0;

        for (int i = 1; i < preSum.length; i++) {
            preSum[i] = preSum[i - 1] + nums[(n - 1) % n];
        }

        int maxSum = Integer.MIN_VALUE;

        //use slideWindow to get the maxSum in window by the min in window
        MonotonicQueue<Integer> window = new MonotonicQueue<>();
        window.push(0);
        for (int i = 1; i < preSum.length; i++) {
            maxSum = Math.max(maxSum, preSum[i] - window.min());
            if (window.size() == n) {
                window.pop();
            }
            window.push(preSum[i]);
        }
        return maxSum;
    }
}
