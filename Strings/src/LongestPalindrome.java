public class LongestPalindrome {
    /**
     * There can be multiple palindromes in the input string, but we have to find the longest one.
     *
     * There are two ways we can check if a string is a palindrome:
     *
     * Start two pointers from each end of the string. Move towards the center while checking that the element at each pointer is the same.
     * Start two pointers from the center of the string. One pointer moves left, and the other moves right while checking that the element at each pointer is the same.
     * We use the second approach to solve our problem. We can traverse over the string, considering each position the center of a palindromic string. This way, we find each palindrome within our string and return the longest one. This center position can either be a specific character in the string (if the string size is odd) or between two characters (if the string size is even).
     * */
    int find(String s, int j, int k) {
        while (s.charAt(j) == s.charAt(k) && j >= 0 && k < s.length()) {
            j--;
            k++;
        }
        return k - j - 1;
    }

    String findLongest(String s) {
        if (s.length() == 0 || s == null) {
            return "";
        }

        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            int len1 = find(s, i, i + 1);
            int len2 = find(s, i, i);
            int res = Math.max(len1, len2);
            if (res > end - start) {
                start = i - (res - 1) / 2;
                end = i + res / 2;
            }
        }

        return s.substring(start, end + 1);
    }
}
