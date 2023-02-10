public class ReverseWordsInSentence {
    /** Given a sentence (an array of characters), reverse the order of its words without affecting the order of letters within a given word. All operations must be done in place.
     *
     * We will follow two steps:
     *
     * Reverse the string.
     * Traverse the string and reverse each word in place.
     * Note: In each iteration, keep traversing till a white space or end of text occurs.
     * */

    void strRev(char[] str, int start, int end) {
        if (str == null) {
            return;
        }

        while (start < end) {
            char temp = str[start];
            str[start] = str[end];
            str[end] = temp;
            start++;
            end--;
        }
    }

    String reverse(char[] sentence) {
        if (sentence == null || sentence.length == 0) {
            return "empty";
        }

        int strLen = sentence.length;
        strRev(sentence, 0, strLen - 1);

        int start = 0, end = 0;
        while (true) {
            if (start >= strLen - 1) {
                break;
            }

            //// find the start index of a word while skipping spaces.
            while (sentence[start] == ' ') {
                start++;
            }

            // find the end index of the word.
            end = start + 1;
            while (end < strLen && sentence[end] != ' ') {
                end++;
            }

            strRev(sentence, start, end - 1);
            start = end;
        }

        return String.valueOf(sentence);

    }
}
