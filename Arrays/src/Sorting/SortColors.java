package Sorting;

import java.util.Arrays;

public class SortColors {
    /**
     * Given an array of n objects colored white, red, or blue, sort the array in place so that objects of the same color are adjacent, with the colors in the order red, white, and blue.
     *
     * This problem is known as Dutch National Flag Problem. The idea is to attribute a color to each number and then arrange them following the order of colors on the Dutch flag.
     *
     * We’ll use the integers 0, 1, and 2 to represent red, white, and blue, respectively.
     *
     * We’ll use three pointers to track the rightmost boundary of zeros, the leftmost boundary of twos, and the current element, which is under consideration.
     *
     * The idea is to move the current pointer along with the array, swapping the current value to the left or right, based on whether it is 0 or 2, while leaving the 1's in place:
     * Initialize the rightmost boundary of zeros so that start = 0.
     * Initialize the leftmost boundary of twos so that end = length of array - 1.
     * Initialize the index of the current element to consider so that index = 0.
     * While index <= end :
     * If array [index] = 0, swap index and start elements and move both pointers to the right.
     * If array [index] = 2, swap index and end elements and move the end pointer to the left.
     * If array [index] = 1, move index pointer to the right.
     * */
    public static void sort(int[] arr) {
        int low = 0, current = 0, high = arr.length - 1, tmp;

        while (current <= high) {
            if (arr[current] == 0) {
                //swap the elements
                //increment low and current index
                //here we increment current because current always run faster than low, we already know the element in low is 1, if it's 2 we would put it in the back, if it's 0 we will put it in the low
                tmp = arr[low];
                arr[low++] = 0;
                arr[current++] = tmp;
            } else if (arr[current] == 2) {
                //swap
                //decrement high not current because we need to check the swapped element
                tmp = arr[current];
                arr[current] = arr[high];
                arr[high--] = tmp;
            } else {
                current++;
            }
        }
    }
    public static void main(String[] args) {
        int[][] inputs = {{2, 0, 2, 1, 1, 0}, {2, 0, 1}, {2, 0, 2, 1, 1, 0, 1, 0, 1, 0, 2}};
        int index = 0;
        for (int[] input : inputs) {
            System.out.println((++index) + ". Unsorted colors: " + Arrays.toString(input));
            sort(input);
            System.out.println("   Sorted colors:   " + Arrays.toString(input));
            System.out.println("----------------------------------------------------------------");
        }
    }
}
