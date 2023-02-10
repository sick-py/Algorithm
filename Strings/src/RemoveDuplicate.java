import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

public class RemoveDuplicate {
    /**
     * */

    static String remove(char[] str) {
        if (str == null || 0 == str.length || str[0] == '\0') return "";

        Set<Character> hashSet = new LinkedHashSet<>();

        int writeIndex = 0, readIndex = 0;

        while (readIndex != str.length) {
            if (!hashSet.contains(str[readIndex])) {
                hashSet.add(str[readIndex]);
                str[writeIndex] = str[readIndex];
                writeIndex++;
            }
            readIndex++;
        }

        String res = String.valueOf(Arrays.copyOfRange(str, 0, writeIndex));
        return res;
    }
}
