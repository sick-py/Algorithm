package SlidingWindows;


public class LongestRepeatingSubArray {

    /** 1004
     * Given a binary array numsand an integer k, if at most can be flipped 0 to 1 k times, please return the largest number of 1.
     *
     * This question examines the sliding window technique. You maintain a window that slides numson to ensure that numsall numbers in are replaced with 1, then the maximum length that the window can reach is the answer to the question.
     *
     * As mentioned earlier in the sliding window framework , the following issues need to be clarified to use the sliding window algorithm:
     * 1. When should the window be enlarged?
     * 2. When should the window be reduced?
     * 3. When do you get a legitimate answer?
     *
     * For this question, the answers to the above three questions are:
     * 1. When the number of replacements is greater than or equal to 0, expand the window so that all 0s entering the window become 1s, making the length of continuous 1s as large as possible.
     * 2. When the number of replacements is less than 0, the window is reduced to allow the number of replacements to continue expanding the window.
     * 3. As long as the number of replacements is greater than or equal to 0, the elements in the window will be replaced with 1, that is, the sub-arrays with consecutive 1s. What we want to find is the maximum window length.
     * */
    int longest1(int[] nums, int k) {
        int left = 0, right = 0;
        //the number of 1 in window
        int windowOneCount = 0;
        int res = 0;

        while (right < nums.length) {
            if (nums[right] == 1) {
                windowOneCount++;
            }
            right++;
            while (right - left - windowOneCount > k) {
                //when 0 in the window is more than k
                if (nums[left] == 1) {
                    windowOneCount--;
                }
                left++;
            }
            res = Math.max(res, right - left);
        }
        return res;
    }

    public int characterReplacement(String s, int k) {
        int left = 0, right = 0;
        int[] window = new int[26];
        int windowMaxCount = 0;
        int res = 0;

        while (right < s.length()) {
            window[s.charAt(right) - 'A']++;
            windowMaxCount = Math.max(windowMaxCount, window[s.charAt(right) - 'A']);
            right++;
            while (right - left - windowMaxCount > k) {
                window[s.charAt(left) - 'A']--;
                left++;
                //we don't need to update maxCount because only when maxCount is bigger can we get longer res
            }
            //now it's a valid window
            res = Math.max(res, right - left);
        }
        return res;
    }
}
