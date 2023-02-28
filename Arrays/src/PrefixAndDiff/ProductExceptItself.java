package PrefixAndDiff;

public class ProductExceptItself {
    public int[] productExceptSelf(int[] nums) {
        /**
         * One possible solution in Java is to use two arrays: one to store the product of all elements to the left of each element and another to store the product of all elements to the right of each element2. Then you can multiply these two arrays element-wise to get the final output array
         * */
        int n = nums.length;
        int[] left = new int[n], right = new int[n];
        int[] res = new int[n];
        left[0] = nums[0];
        right[n - 1] = nums[n - 1];
        for (int i = 1; i < n; i++) {
            left[i] = left[i - 1] * nums[i];
            right[n - i - 1] = right[n - i] * nums[n - i - 1];
        }
        res[0] = right[1];
        res[n - 1] = left[n - 2];
        for (int i = 1; i < n - 1; i++) {
            res[i] = left[i - 1] * right[i + 1];
        }
        return res;

    }
}
