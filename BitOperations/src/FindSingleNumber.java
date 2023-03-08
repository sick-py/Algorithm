public class FindSingleNumber {
    public int singleNumber(int[] nums) {
        //1 ^ 0 = 1
        //1 ^ 1 = 0
        int n = nums.length;
        int res = 0;
        for (int i = 0; i < n; i++) {
            res = res ^ nums[i];
        }
        return res;
    }
}
