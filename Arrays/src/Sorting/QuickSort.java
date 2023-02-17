package Sorting;

public class QuickSort {
    /**
     * Select a pivot element from the array to divide it into two parts.
     * We pick the first element as the pivot if we follow Hoare’s algorithm. Another common approach is to select a random element as the pivot.
     * Reorder the array by comparing elements with the pivot element such that smaller values end up at the left side and larger values end up at the right side of the pivot.
     * Now, the pivot element is in its correct sorted position.
     * By applying the above steps, we can recursively sort the sublists on the right and left sides of the pivot.
     * */
    int partition(int[] arr, int low, int high) {
        int pivotValue = arr[low];
        int i = low, j = high;

        while (i < j) {
            //increment the i till it finds a value bigger than pivot
            while (i <= high && arr[i] <= pivotValue) {
                i++;
            }
            while (j >= 0 && arr[j] > pivotValue) {
                j--;
            }

            //swap the i j
            if (i < j) {
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        //swap pivot with j
        arr[low] = arr[j];
        arr[j] = pivotValue;
        return j;//return pivot value
    }

    void quick(int[] arr, int low, int high) {
        if (high > low) {
            int pivot = partition(arr, low, high);
            quick(arr, low, pivot - 1);
            quick(arr, pivot + 1, high);
        }
    }
}
