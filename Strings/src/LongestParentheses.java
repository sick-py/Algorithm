public class LongestParentheses {
    /**
     * Given a string that contains the characters '(' and ')', find the length of the longest valid parentheses substring.
     * We can solve this problem with two passes over the input string. As we traverse the string from left to right, we count the number of left parentheses (left) and right parentheses (right). If at any point, we see more right parentheses than left parentheses, we can be certain that the sub-string we’ve seen so far can’t be part of the longest valid parenthesization. In this case, we reset the left and right values to zero.
     *
     * For example, consider "())()()". When we iterate from left to right, we cannot rule out the first character as a part of the longest valid parenthesization. The second character may also be part of a valid parenthesization. At this point, we see an equal number of left and right parentheses, specifically 1 of each. So, we note a valid parentheses substring of length 2.
     *
     * However, when we see the third character, we can be certain that the first three characters of the string (together) can’t be part of the longest valid parenthesization. Either the longest valid parenthesization is the first two characters of the string, or it is present in the remainder of the string. When we see the remaining four characters in the string, we find another valid parenthesization of size 4, which is also the longest.
     * */
    int longest(String s) {
        int left = 0, right = 0, maxLen = 0;

        //Left iteration
        for (int i = 0; i < s.length(); i++) {
            //update left
            if (s.charAt(i) == '(') {
                left++;
            } else {
                right++;
            }
            if (left == right) {
                maxLen = Math.max(maxLen, 2 * right);
            } else if (right >= left) {
                left = right = 0;
            }
        }
        left = right = 0;

        //right iteration
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == '(') {
                left++;
            } else {
                right++;
            }

            if (left == right) {
                maxLen = Math.max(maxLen, 2 * left);
            } else if (left >= right) {
                left = right = 0;
            }
        }
        return maxLen;
    }
}
