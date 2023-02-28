package SlidingWindows;

import java.util.*;

public class Main {
    /**
     * The idea of this algorithm technique is very simple, it is to maintain a window, keep sliding, and then update the answer.
     *
     * */
    void template(String s) {
        int left = 0, right = 0;
        List<Character> window = new ArrayList<>();
        while (right < s.length()) {
            //enlarge the window
            window.add(s.charAt(right));
            right++;

            while (true /*window needs to shrink*/) {
                window.remove(s.charAt(left));
                left++;
            }
        }
    }

    /**
     * but the key part is to pay attention to the details, how to add new elements to the window, how to shrink the window, and at which stage the window slides to update the results.
     *
     * Also, although there is a nested while loop in the sliding window code framework, the time complexity of the algorithm is still O(N), where Nis the length of the input string/array.
     *
     * why? Simply put, each element in the string/array will only enter the window once, and then be moved out of the window once.
     *
     * To make a digression, I found that many people like to cling to the appearance and don't like to explore the essence of the problem. For example, many people commented on my framework, saying that the hash table is slow, it is better to replace the hash table with an array; there are also many people who like to write the code very short, saying that my code is too redundant, which affects the compilation speed, and the speed on LeetCode not fast enough.
     *
     * My opinion is that the algorithm mainly depends on the time complexity, and you can ensure that your time complexity is optimal. As for the so-called running speed of LeetCode, that is all metaphysics. As long as it is not too slow, there is no problem. It is not worth optimizing from the compilation level.
     */

    void slidingWindow(String s) {
        HashMap<Character, Integer> window;
        int left = 0, right = 0;
        while (right < s.length()) {
            //c is the element to get into the window
            char c = s.charAt(right);
            //enlarge the window
            right++;
            /**update something in the window
            do something
             debug place*/
            System.out.printf("window: [%d, %d]\n", left, right);

            //judge whether to shrink the window
            while (true) {
                char d = s.charAt(left);
                left++;
                /**update something in the window
                 do something*/
            }
        }
    }

    public boolean containsNearbyDuplicate(int[] nums, int k) {
        HashSet<Integer> window = new HashSet<>();
        int left = 0, right = 0;
        while (right < nums.length) {
            int a = nums[right];
            for (int n : window) {
                System.out.printf("windowR: [%d]\n", n);
            }
            if (window.contains(a)) {
                return true;
            }
            window.add(a);
            right++;
            while (right - left > k) {
                int d = nums[left];
                left++;
                window.remove(d);
            }
        }
        return false;
    }
    /**
     *
     * Well, this is the end of the sliding window algorithm template. I hope everyone can understand the idea, remember the algorithm template and master it.
     *
     * 1. When should the window be enlarged?
     *
     * 2. When should the window be reduced?
     *
     * 3. When should an answer be updated?
     *
     *
     * */


}
