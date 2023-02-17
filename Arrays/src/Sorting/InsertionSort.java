package Sorting;

public class InsertionSort {
    /**
     * This implementation of Insertion Sort works by iterating through the list from the second element to the last. For each element, it compares it to the previous elements, and if the previous elements are larger, it shifts them to the right and inserts the current element in the correct position.
     * */
    public int[] sortArray(int[] nums) {
        int n = nums.length;
        for (int i = 1; i < n; i++) {
            int key = nums[i];
            int j = i - 1;
            //shift elements of nums[0..i - 10, that are greater than key
            while (j >= 0 && nums[j] > key) {
                nums[j + 1] = nums[j];
                j--;
            }
            nums[j + 1] = key;
        }
        return nums;
    }
}
