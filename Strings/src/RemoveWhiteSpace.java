import java.util.Arrays;

public class RemoveWhiteSpace {
    /**
     * Given a null-terminated string, remove all the white spaces (tabs or spaces) present in the string.
     *
     * This problem can be solved with two pointers. Here is an overview:
     *
     * Initialize read and write pointers to the start of the string.
     * With the read pointer, iterate over the input string reading one character at a time.
     * While moving read pointer towards the end of the array:
     * Skip, if the read pointer points to a white space.
     * If the read pointer points to any other character, write that to the write pointer and increment the write pointer.
     * Terminate the loop when the read pointer reaches the end of the string.
     * At this point, the write pointer points to the string with all white spaces removed.
     * */
    boolean isWhiteChar(char c) {
        if (c == ' ' || c == '\t') {
            return true;
        }
        return false;
    }

    String remove(char[] str) {
        if (str == null || str.length == 0 || str[0] == '\0') {
            return "";
        }
        int readIndex = 0, writeIndex = 0;
        while (readIndex < str.length && str[readIndex] != '\0') {
            if (!isWhiteChar(str[readIndex])) {
                str[writeIndex] = str[readIndex];
                writeIndex++;
            }
            readIndex++;
        }

        String res = String.valueOf(Arrays.copyOfRange(str, 0, writeIndex));
        return res;
    }
}
