package Sorting;

public class MergeSort {
    int[] temp;
    public int[] sortArray(int[] arr) {
        int n = arr.length;
        temp = new int[n];
        mergeSort(arr, 0, n - 1);
        return arr;
    }

    private void mergeSort(int[] arr, int left, int right) {
        if (left == right) {
            return;
        }
        int mid = left + (right - left) / 2;
        mergeSort(arr, left, mid);
        mergeSort(arr, mid + 1, right);
        merge(arr, left, mid, right);
    }

    private void merge(int[] arr, int left, int mid, int right) {
        int p1 = left, p2 = mid + 1, p = left;
        for (int i = left; i <= right; i++) {
            temp[i] = arr[i];
        }
        while (p1 <= mid && p2 <= right) {
            if (temp[p1] < temp[p2]) {
                arr[p] = temp[p1];
                p1++;
            } else {
                arr[p] = temp[p2];
                p2++;
            }
            p++;
        }

        while (p1 <= mid) {
            arr[p++] = temp[p1++];
        }

        while (p2 <= right) {
            arr[p++] = temp[p2++];
        }
    }
}
