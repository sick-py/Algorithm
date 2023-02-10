package QuickSort;

import java.util.PriorityQueue;

import static QuickSort.quickSort.partition;
import static QuickSort.quickSort.shuffle;

public class findKth {
    /**
     * The title requires us to find the largest element , which is a bit convoluted, which means to find the element that ranks the largest after the array is sorted in descending order
     *
     * There are two solutions to this kind of problem, one is the solution of the binary heap (priority queue), and the other is the fast selection algorithm. Let's look at it separately.
     *
     * The time complexity of binary heap insertion and deletion is related to the number of elements in the heap. Here, the size of our heap will not exceed k, so the complexity of inserting and deleting elements is O(logk), and then set a layer of for loop, assuming the total number of array elements For N, the total time complexity is O(Nlogk).
     *
     * The space complexity of this solution is obviously the size of the binary heap, as O(k).
     * */

    int findKthLargest(int[] nums, int k) {
        // 小顶堆，堆顶是最小元素
        PriorityQueue<Integer>
                pq = new PriorityQueue<>();
        for (int e : nums) {
            // 每个元素都要过一遍二叉堆
            pq.offer(e);
            // 堆中元素多于 k 个时，删除堆顶元素
            if (pq.size() > k) {
                pq.poll();
            }
        }
        // pq 中剩下的是 nums 中 k 个最大元素，
        // 堆顶是最小的那个，即第 k 个最大元素
        return pq.peek();
    }

    /**
     * The quick selection algorithm is a variant of quick sort, which is more efficient . If you can write a quick selection algorithm in the interview, it will definitely be a bonus item.
     *
     * First of all, the title asks " kthe largest element", which is equivalent to the "ranked n - kelement" after sorting the array in ascending order. For the convenience of expression, it will be discussed later k' = n - k.
     *
     * How do you know k'the element"? In fact, you can see a little bit during the execution of the quick sort algorithm partitionfunction .
     *
     * We just said that the partition function will put nums[p] into the correct position such that nums[lo..p-1] < nums[p] < nums[p+1..hi]:
     *
     * At this time, although the entire array has not been sorted, we have made the elements on the nums[p]left of to smaller than nums[p], and we know nums[p]the ranking of .
     *
     * Then we can compare k with p, if p < k'means that the k'largest element is in nums[p+1..hi] , if p > k'means that the k'largest element is in nums[lo..p-1] .
     *
     * Further, go nums[p+1..hi]or nums[lo..p-1]execute the partitionfunction , you can further narrow down k'the range of the elements ranked at , and finally find the target element.
     * */
    int findK(int[] nums, int k) {
        shuffle(nums);
        int lo = 0, hi = nums.length - 1;
        k = nums.length - k;
        while (lo <= hi) {
            int p = partition(nums, lo, hi);
            if (p < k) {
                lo = p + 1;
            } else if (p > k) {
                hi = p - 1;
            } else {
                return nums[p];
            }
        }
        return -1;
    }


}
