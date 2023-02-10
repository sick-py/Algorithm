public class ProductOfAllArrayElementsExceptSelf {
    /**
     * Given an integer array, nums, return an array, answer, such that answer[i] is equal to the product of all the elements of nums except nums[i].
     *
     *
     * */

    /**
     * this solution is O(n) and O(1) without 0 value
     * */
        public int[] productExceptSelf(int[] nums) {
            int n = nums.length;
            int[] answer = new int[n];
            // write your code here
            int product = 1;
            for (int i = 0; i < n; i++) {
                product *= nums[i];
            }

            for (int i = 0; i < n; i++) {
                if (nums[i] == 0) {
                    answer[i] = 1;
                    for (int j = 0; j < n; j++) {
                        if (j == i) continue;
                        answer[i] *= nums[j];
                    }
                    continue;
                }
                answer[i] = product / nums[i];
            }

            return answer;
        }

        /**
         *For any given index i in nums, the value in answer[i] is the result of multiplying all the elements in nums to the left of i and all the elements to its right.
         *
         * We can store intermediate multiplication results from i=1 through to i=length(nums)-1 in an array, left, where each value is calculated as: left[i] = left[i-1] * nums[i-1], with the edge case left[0] = 1.
         *
         * Similarly, we can move from the end of the input array to the start, storing intermediate multiplication results from i=length(nums)-2 through to i=0 in an array, right, such that: right[i] = right[i+1] * n
         * */

        public int[] product(int[] nums){
            int len = nums.length;

            int[] left = new int[len];
            int[] right = new int[len];
            int[] res = new int[len];

            left[0] = 1;
            for (int i = 1; i < len; i++) {
                left[i] = left[i - 1] * nums[i - 1];
            }

            right[len - 1] = 1;
            for (int i = len - 2; i >= 0; i--) {
                right[i] = right[i - 1] * nums[i - 1];
            }

            for (int i = 0; i < len; i++) {
                res[i] = left[i] * right[i];
            }
            return res;
        }

}
