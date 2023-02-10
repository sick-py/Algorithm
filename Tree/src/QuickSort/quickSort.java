package QuickSort;

import java.util.Random;

public class quickSort {
    /**
     * have a look at the frame first
    void sort(int[] nums, int lo, int hi) {
        if (lo >= hi) {
            return;
        }

        int p = partition(nums, lo, hi);
        sort(nums, lo, p - 1);
        sort(nums, p + 1, hi);
    }
     * just like the proOrder
     *
     * Quick sort is to sort one element first, and then sort the remaining elements.
     * The core of quick sort is undoubtedly the partition function , the function is nums[lo..hi]to find a cut-off point pin , by exchanging elements so that nums[lo..p-1]both are less than or equal to nums[p], and nums[p+1..hi]are greater than nums[p]:
     * Isn't it just that the nums[p] has been put in the correct position.
     * You can even understand it this way: the process of quick sorting is a process of constructing a binary search tree .
     *
     * But when it comes to the construction of the binary search tree, we have to talk about the extreme case of the unbalanced binary search tree. In extreme cases, the binary search tree will degenerate into a linked list, resulting in a significant reduction in operating efficiency.
     *
     * There is a similar situation in the process of quick sorting. For example, every time the dividing point selected by the partitionfunction can divide the function nums[lo..hi]into two halves, but in reality, you may not be so lucky.
     *
     * If you are particularly lucky every time, and there are very few elements on one side, this will cause the binary tree to grow unbalanced:
     *
     * In order to avoid this extreme situation, we need to introduce randomness .
     *
     * A common way is to perform the shuffling , or to randomly select array elements in the partitionfunction as the dividing point. This article will use the former.
     *
     * Another thing to note is that quick sort is an "unstable sort", in contrast to the above-mentioned merge sort It is a "stable sort" .
     * */
    void sort(int[] nums) {
        shuffle(nums);
        sortRec(nums, 0, nums.length);
    }

    private void sortRec(int[] nums, int lo, int hi) {
        if (lo >= hi) {
            return;
        }

        int p = partition(nums, lo, hi);
        sortRec(nums, lo, p - 1);
        sortRec(nums, p + 1, hi);
    }

    public static int partition(int[] nums, int lo, int hi) {
        int pivot = nums[lo];

        int i = lo + 1, j = hi;
        while (i <= j) {
            while (i < hi && nums[i] <= pivot) {
                i++;
                //when out of the loop the nums[i] > pivot
            }

            while (j > lo && nums[j] > pivot) {
                j--;
            }

            if (i >= j) {
                break;
            }

            //swap
            swap(nums, i, j);
        }
        swap(nums, lo, j);
        return j;
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void shuffle(int[] nums) {
        Random rand = new Random();
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            int r = i + rand.nextInt(n - i);
            swap(nums, i, r);
        }
    }

}
