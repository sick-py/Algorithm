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
            while (j >= low && arr[j] > pivotValue) {
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

    class review{
        public int[] sortArray(int[] arr) {
            int n = arr.length;
            quickSort(arr, 0, n - 1);
            return arr;
        }

        private void quickSort(int[] arr, int left, int right) {
            if (left == right) {
                return;
            }
            int pivot = part(arr, left, right);
            quickSort(arr, left, pivot - 1);
            quickSort(arr, pivot + 1, right);
        }

        private int part(int[] arr, int left, int right) {
            int key = arr[left++]; //here you should use left
            int start = left;
            while (left < right) {//when out left = right
                while (left <= right && arr[left] <= key) {
                    left++;
                }
                while (right >= left && arr[right] > key) {
                    right--;
                }
                if (left < right) { //here you need to judge, at the last case, you need to swap only the right one, draw the pic and you will know
                    int temp = arr[left];
                    arr[left] = arr[right];
                    arr[right] = temp;
                }
            }
            int temp = arr[right];
            arr[right] = key;
            arr[start] = temp;
            return right;
        }
    }
}
