package MergeSort;

import java.util.LinkedList;
import java.util.List;

public class calculateTheRightSmaller {
    /**
     * I will describe it in a more mathematical language (for comparison with subsequent similar questions). The question asks you to find an count array such that:
     *
     * count[i] = COUNT(j) where j > i and nums[j] < nums[i]
     * What is the relationship between this question and merge sort, mainly in the mergefunction , when we use the mergefunction to merge two ordered arrays, we can actually know nums[i]how many elements after an element are nums[i]smaller .
     *
     * https://labuladong.github.io/algo/di-yi-zhan-da78c/shou-ba-sh-66994/gui-bing-p-1387f/
     *
     * At this time we should temp[i]put nums[p]on , because temp[i] < temp[j].
     *
     * But in this scenario, we can also know a piece of information: the number of elements after 5 that is smaller than 5 is the number of elements in the left-closed right-open interval [mid + 1, j), that is, the two elements 2 and 4:
     *
     * In other words, in the process of nums[lo..hi]merging , whenever is executed nums[p] = temp[i], it can be determined temp[i]thatj - mid - 1 the number of elements behind this element is smaller than it .
     *
     * Of course, nums[lo..hi]it is only a sub-array itself, and this sub-array will be executed later merge, and the position of the elements in it will still change. But this is a problem that other recursive nodes need to consider. We only need to do some tricks in the merge function and superimpose merge the results recorded each time.
     * */
    class Pair {
        int val, id;
        Pair(int a, int b) {
            val = a;
            id = b;
        }
    }

    Pair[] temp;
    int[] count;

    List<Integer> countSmaller(int[] nums) {
        int n = nums.length;
        temp = new Pair[n];
        count = new int[n];
        Pair[] arr = new Pair[n];

        for (int i = 0; i < n; i++) {
            arr[i] = new Pair(nums[i], i);
        }

        sort(arr, 0, n - 1);
        List<Integer> res = new LinkedList<>();
        for (int c : count) res.add(c);
        return res;
    }

    private void sort(Pair[] arr, int left, int right) {
        if (left == right) {
            return;
        }
        int mid = left + (right - left) / 2;
        sort(arr, left, mid);
        sort(arr, mid + 1, right);
        merge(arr, left, mid, right);
    }

    private void merge(Pair[] arr, int left, int mid, int right) {
        for (int i = left; i <= right; i++) {
            temp[i] = arr[i];
        }

        int i = left, j = right;
        for (int p = left; p <= right; p++) {
            if (i == mid + 1) {
                arr[p] = temp[j++];
            } else if (j == right + 1) {
                arr[p] = temp[j++];
                count[arr[p].id] += j - mid - 1;
            } else if (temp[i].val > temp[j].val) {
                arr[p] = temp[j++];
            } else  {
                arr[p] = temp[i++];
                count[arr[p].id] += j - mid - 1;
            }
        }
    }
}
