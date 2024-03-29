package MergeSort;

public class main {
    /**
     * Let’s put it this way, all recursive algorithms, regardless of what they do, are essentially traversing a (recursive) tree, and then executing code on nodes (front, middle, and back order positions). You need to write a recursive algorithm , essentially telling each node what to do .
     *
     * Merge sort is to sort the left half of the array first, then sort the right half of the array, and then merge the two halves of the array.
     *
     * Preamble Hand brush binary tree (programme)It is said that the binary tree problem can be divided into two types of ideas, one is the idea of ​​traversing the binary tree, and the other is the idea of ​​​​decomposing the problem. According to the above analogy, it is obvious that the merge sort uses the idea of ​​​​decomposing the problem ( divide and conquer algorithm).
     *
     * The process of merge sorting can be logically abstracted into a binary tree. The value of each node on the tree can be considered as nums[lo..hi], and the value of the leaf node is a single element in the array.
     *
     * After all, the time complexity of the algorithm is only a theoretical measure, and the actual operating efficiency of the algorithm has more factors to consider, such as avoiding frequent allocation and release of memory, code logic should be as concise as possible, and so on.
     *
     * Note that we do not new the auxiliary array when the mergefunction is executed, but new the auxiliary array in advance temp, so as to avoid the possible performance problems caused by frequent allocation and release of memory in recursion.
     * */

    int[] temp;

    void sort(int[] nums) {
        temp = new int[nums.length];
        rec(nums, 0, nums.length - 1);
    }

    private void rec(int[] nums, int left, int right) {
        if (left == right) {
            return; //base case, leaf node where single element is in right order
        }

        int mid = left + (right - left) / 2;
        rec(nums, left, mid);
        rec(nums, mid + 1, nums.length);
        merge(nums, left, mid, right);
    }

    //merge nums[left, mid] nums[mid + 1, right]
    private void merge(int[] nums, int left, int mid, int right) {
        for (int i = left; i <= right; i++) {
            temp[i] = nums[i];
        }

        int i = left, j = mid + 1;
        for (int p = left; p <= right; p++) {
            if (i == mid + 1) {
                nums[p] = nums[j++];
            } else if (j == right) {
                nums[p] = nums[j++];
            } else if (temp[i] > temp[j]) {
                nums[p] = nums[j++];
            } else {
                nums[p] = nums[i++];
            }
        }
    }

    /**
     * The number of executions is the number of binary tree nodes, and the complexity of each execution is the length of the sub-array represented by each node, so the total time complexity is the number of "array elements" in the entire tree .
     *
     * So on the whole, the height of this binary tree is logN, and the number of elements in each layer is the length of the original array N, so the total time complexity is O(NlogN).
     * */
    class review {
        public void merge(int[] nums1, int m, int[] nums2, int n) {
            int index = m + n - 1, i = m - 1, j = n - 1;
            while (i >= 0 && j >= 0) {
                if (nums1[i] < nums2[j]) {
                    nums1[index] = nums2[j];
                    j--;
                } else {
                    nums1[index] = nums1[i];
                    i--;
                }
                index--;
            }

            while (i >= 0) {
                nums1[index--] = nums1[i--];
            }

            while (j >= 0) {
                nums1[index--] = nums2[j--];
            }
        }

        int[] temp;
        void mergeSort(int[] nums) {
            int n = nums.length;
            temp = new int[n];
            helper(nums, 0, n - 1);

        }

        private void helper(int[] nums, int low, int high) {
            if (low == high) {
                return;
            }

            int mid = low + (high - low) / 2;
            helper(nums, low, mid);
            helper(nums, mid + 1, high);
            merge0(nums, low, mid, high);
        }

        private void merge0(int[] nums, int low, int mid, int high) {
            for (int i = low; i <= high; i++) {
                temp[i] = nums[i];
            }
            int i = low;
            int i1 = low, i2 = mid + 1;
            while (i1 <= mid && i2 <= high) {
                if (temp[i1] < temp[i2]) {
                    nums[i++] = temp[i1++];
                } else {
                    nums[i++] = temp[i2++];
                }
            }

            while (i1 <= mid) {
                nums[i++] = temp[i1++];
            }

            while (i2 <= high) {
                nums[i++] = temp[i2++];
            }

        }
    }
}
