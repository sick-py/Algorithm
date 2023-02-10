import java.util.List;

public class FindMIssingNumber {
    /**
     * Given an array of positive numbers from 1 to n, such that all numbers from 1 to n are present except one, find the missing number.
     * All the numbers in the array must be unique.
     * The array must have non-negative integers.
     *
     * instead of search, hashmap, or sort
     * use math formula
     * */

    int find(List<Integer> input) {
        int sum = 0;
        for (int i : input) {
            sum += i;
        }

        int n = input.size();
        int sumV = (n * (n - 1)) / 2;

        return sumV - sum;
    }

}
