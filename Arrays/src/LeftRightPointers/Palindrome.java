package LeftRightPointers;

public class Palindrome {
    /**
     * First of all, to be clear, a palindrome is a string that reads the same forwards and backwards.
     * judge
     * */

    boolean isPalindrome(String s) {
        int left = 0, right = s.length() - 1;
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    /**
     *The difficulty of finding the palindrome string is that the length of the palindrome string may be odd or even. The core of solving this problem is the double pointer technique that spreads from the center to both ends .
     * If the length of the palindrome is odd, it has a central character; if the length of the palindrome is even, it can be considered to have two central characters. So we can first implement such a function:
     *
     * In this way, if you input the same lsum r, it is equivalent to looking for a palindrome string with an odd length, and if you input adjacent lsums r, it is equivalent to looking for a palindrome string with an even length.
     *
     * */
    String populatePalindrome(String s, int l, int r) {
        while (l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)) {
            l--;
            r++;
        }
        return s.substring(l + 1, r);
    }

    /**
     *You should be able to find that the left and right pointers used in the longest palindrome substring are somewhat different from the left and right pointers in the previous question: the previous left and right pointers all go from the two ends to the middle, while the palindrome substring problem is to let the left and right pointers move from the center Extend to both ends.
     * */
    String longest(String s) {
        String res = "";
        for (int i = 0; i < s.length(); i++) {
            String s1 = populatePalindrome(s, i, i);
            String s2 = populatePalindrome(s, i, i + 1);
            res = res.length() > s1.length() ? res : s1;
            res = res.length() > s2.length() ? res : s2;
        }
        return res;
    }

    /**
     * 125 with real interview
     * */
    public boolean isPalindromeReal(String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isLetterOrDigit(c)) {
                sb.append(Character.toLowerCase(c));
            }
        }
        s = sb.toString();
        System.out.print(s);
        int left = 0, right = s.length() - 1;
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    /** 516 longest palindromic subsequence
     * */
    //go to the dynamci subsequence

    class review {
        String longest(String str, int i, int j) {
            while (i >= 0 && j < str.length() && str.charAt(i) == str.charAt(j)) {
                i--;
                j++;
            }
            return str.substring(i + 1, j);
        }

        public String longestPalindrome(String s) {
            String res = "";
            String s1 = "";
            for (int i = 0; i < s.length(); i++) {
                s1 = longest(s, i, i);
                res = s1.length() > res.length() ? s1 : res;
                s1 = longest(s, i, i + 1);
                res = s1.length() > res.length() ? s1 : res;
            }
            return res;
        }

        int count(String str, int i, int j) {
            int count = 0;
            while (i >= 0 && j < str.length() && str.charAt(i) == str.charAt(j)) {
                count++;
                i--;
                j++;
            }
            return count;
        }

        public int countSubstrings(String s) {
            int res = 0;
            for (int i = 0; i < s.length(); i++) {
                res += count(s, i, i);
                res += count(s, i, i + 1);
            }
            return res;
        }

        public boolean isPalindrome(String s) {
            int left = 0, right = s.length() - 1;
            while (left < right) {
                while (!Character.isLetterOrDigit(s.charAt(left)) && left + 1 < s.length()) {
                    left++;
                }

                while (!Character.isLetterOrDigit(s.charAt(right)) && right - 1 >= 0) {
                    right--;
                }

                if (Character.isLetterOrDigit(s.charAt(left)) && Character.isLetterOrDigit(s.charAt(right)) && Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right))) {
                    return false;
                }
                left++;
                right--;
            }
            return true;
        }
    }
}
