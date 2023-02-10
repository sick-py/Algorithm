package SlidingWindows;

import java.util.HashMap;

public class TheMinimumCoveringSubstring {
    /**
     * It means to find a substring in S(source) that contains all the letters in T(target), and this substring must be the shortest among all possible substrings.
     *
     * We use the left and right pointer technique in the double pointer in the string Sto initialize, and call the left-closed and right-open interval left = right = 0of the index a "window".[left, right)
     *
     * PS: In theory, you can design an interval with both ends open or both ends closed, but it is most convenient to design an interval with left closed and right open. Because there are no elements in the initial left = right = 0time interval, but as long as is moved (expanded) to the right, the interval will contain an . If you set it as an interval with both ends open, then move to the right by one bit and the open interval still has no elements; if you set it as an interval with contains one element. Both of these cases can cause unnecessary trouble for boundary handling.[0, 0)right[0, 1)0right(0, 1)[0, 0]
     *
     * We first keep increasing the rightpointer to expand the window [left, right)until the character string in the window meets the requirements (including all the characters T in ).
     *
     *A t this point, we stop increasing right, and continue to increase the leftpointer to shrink the window [left, right)until the string in the window no longer meets the requirements (does not T contain all the characters in ). left At the same time , we have to update a round of results for each increment .
     *
     * Repeat steps 2 and 3 until rightreaches Sthe end of the string.
     *
     * This idea is actually not difficult. The second step is equivalent to looking for a "feasible solution", and then the third step is to optimize this "feasible solution", and finally find the optimal solution , which is the shortest covering substring. The left and right pointers move forward in turn, the size of the window increases and decreases, and the window keeps sliding to the right. This is the origin of the name "sliding window".
     * */
    String minWindow(String s, String t) {
        String res = "";
        HashMap<Character, Integer> need = new HashMap<>(), window = new HashMap<>();

        for (int i = 0; i < t.length(); i++) {
            char ch = t.charAt(i);
            need.put(ch, need.getOrDefault(ch, 0) + 1);
        }

        int left = 0, right = 0;
        int valid = 0;
        //for the res
        int start = 0, len = Integer.MAX_VALUE;
        while (right < s.length()) {
            char c = s.charAt(right);
            right++;
            //update the window
            if (need.containsKey(c)) {
                window.put(c, window.getOrDefault(c, 0) + 1);
                if (window.get(c).equals(need.get(c))) {
                    valid++;
                }
            }

            while (valid == need.size()) {
                //for the res
                if (right - left < len) {
                    start = left;
                    len = right - left;
                }

                char d = s.charAt(left);
                left++;
                if (need.containsKey(d)) {
                    if (window.get(d).equals(need.get(d))) {
                        valid--;
                    }
                    window.put(d, window.get(d) - 1);
                }
            }
        }
        return len == Integer.MAX_VALUE ? "" : s.substring(start, start+len);
    }
}
