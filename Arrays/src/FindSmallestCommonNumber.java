public class FindSmallestCommonNumber {
    /**
     * We’re given three integer arrays, each sorted in ascending order. Return the smallest number common in all three arrays. In case no number is common, return -1.
     *
     * The arrays are sorted in ascending order. We will use three iterators simultaneously to traverse each of the arrays. We can start traversing each array from the 0 index, which always has the smallest value.
     * If the values pointed to by the three iterators are equal, that is the solution. Since the arrays are sorted in ascending order, that value must be the smallest value present in all of the arrays.
     *
     * Otherwise, we see which of the three iterators points to the smallest value and increment that iterator so that it points to the next index.
     *
     * If any of the three iterators reaches the end of the array before we find the common number, we return -1.
     * */

    Integer find(int[] arr1, int[] arr2, int[] arr3) {
        int i = 0, j = 0, k = 0;
        while (i < arr1.length && j < arr2.length && k < arr3.length) {
            if (arr1[i] == arr2[j] && arr2[j] == arr3[k]) {
                return arr1[i];
            }

            if (arr1[i] <= arr2[j] && arr1[i] <= arr3[k]) {
                i++;
            } else if (arr2[j] <= arr1[i] && arr2[j] <= arr3[k]) {
                j++;
            } else if (arr3[k] <= arr1[i] && arr3[k] <= arr2[j]) {
                k++;
            }
        }
        return -1;
    }
}
