package SlidingWindows;

import java.util.HashMap;
import java.util.HashSet;

public class TheLongestNoneRepeatingSubstring {
    /**
     *
     * */
    int len(String s) {
        HashMap<Character, Integer> window = new HashMap<>();

        int left = 0, right = 0, res = 0;
        while (right < s.length()) {
            char c = s.charAt(right);
            right++;
            window.put(c, window.getOrDefault(c, 0) + 1);
            while (window.get(c) > 1) {
                char d = s.charAt(left);
                left++;
                window.put(d, window.get(d) - 1);
            }
            res = Math.max(res, right - left);
        }
        return res;
    }

    class review {
        public int lengthOfLongestSubstring(String s) {
            if (s.length() == 0) {
                return 0;
            }
            HashMap<Character, Integer> window = new HashMap<>();
            int left = 0, right = 0;
            int res = 0;
            while (right < s.length()) {
                char a = s.charAt(right);
                right++;
                window.put(a, window.getOrDefault(a, 0) + 1);
                while (window.get(a) > 1) {
                    char d = s.charAt(left);
                    left++;
                    window.put(d, window.get(d) - 1);
                }
                res = Math.max(res, right - left);
            }
            return res;
        }
    }
}
