import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class FirstUniqueCharacter {
    public int firstUniqChar(String s) {
        HashMap<Character, Integer> charToCount = new HashMap<>();
        for (char c : s.toCharArray()) {
            charToCount.put(c, charToCount.getOrDefault(c, 0) + 1);
        }


        for (int i = 0; i < s.length(); i++) {
            if (charToCount.get(s.charAt(i)) == 1) {
                return i; //because we want the first one
            }
        }
        return -1;
    }
}
