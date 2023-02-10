public class RegularExpressionMatching {
    /**
     * Given a text and a pattern, determine if the pattern matches the text completely or not at all by using regular expression matching. For simplicity, assume that the pattern may contain only two operators: . and *.
     *
     * * operator in the pattern means that the character preceding * may not appear or may appear any number of times in the text. The . operator matches with any character in the text exactly once.
     *
     * If there are no operators in the pattern, then regex matching is a simple string comparison. Handling the . operator is also very easy as it can match with any character in the text. However, handling the * operator is not that simple. To handle *, let’s look at the below example.
     *
     * Suppose the given text is fabbbc, and the given pattern is .ab*c. We start matching from the first character of both the text and the pattern. The first character of the pattern is . and the first character of the text is f. Since . matches with any character, they match. Now, we match the remaining text and the remaining pattern recursively , that is, we match ab*c and abbbc.
     *
     * For the remaining text and remaining pattern, the first character of the pattern is a, and the first character of text is also a. They both match, so we again match the remaining text and remaining pattern recursively.
     * Now our pattern is b*c, meaning, the second character of the pattern is *. Here, b* can match with 0 or more b's in the text. So, we need to match the remaining pattern, for example, c with bbbc, bbc, bc and c.
     *
     * The worst-case time complexity of this algorithm is exponential. Let’s look at an example where the text is aaa and the pattern is a*a*a*. First, we match a*a* with aaa, aa, a, and an empty string. Then we match a* with aaa, aa, a, and an empty string.
     * */
    boolean regxMatchRec(String text, String pattern) {
        if (text.isEmpty() && pattern.isEmpty()) {
            return true;
        }
        if (!text.isEmpty() && pattern.isEmpty()) {
            return false;
        }

        // this block deals with the asterisk wildcard in the pattern
        if (pattern.length() > 1 && pattern.charAt(1) == '*') {
            // when matching against the asterisk wildcard, we need two characters, one an actual
            // character and then the asterisk. So, we consume two characters from the pattern at
            // this point
            String remainingPattern = pattern.substring(2);
            String remainingText = text;

            for (int i = 0; i < text.length() + 1; i++) {
                // checking the same text against the remaining pattern allows a match even if there are 0 occurrences of the character present at pattern[0]
                if (regxMatchRec(remainingText, remainingPattern)) {
                    return true;
                }

                if (remainingText.isEmpty()) {
                    return false;
                }

                // if the current pattern character (pattern[0]) is not the dot wildcard
                // and if it is different from the current text character (remaining_text[0]),
                // report the mismatch
                if (pattern.charAt(0) != '.' && remainingText.charAt(0) != pattern.charAt(0)) {
                    return false;
                }

                // current text character matched, moving on to check the next
                remainingText = remainingText.substring(1);
            }
        }

        if (text.isEmpty() || pattern.isEmpty()) {
            return false;
        }

        // # the same character appeared in the pattern and the text at the same time
        // or, the dot wildcard was encountered in the pattern, which allows us to
        // accept any character at this position in the text
        if (pattern.charAt(0) == '.' || pattern.charAt(0) == text.charAt(0)) {
            String remainingText = "";
            if (text.length() >= 2) {
                remainingText = text.substring(1);
            }

            String remainingPattern = "";
            if (pattern.length() >= 2) {
                remainingPattern = pattern.substring(1);
            }

            return regxMatchRec(remainingText, remainingPattern);
        }

        return false;
    }
}
