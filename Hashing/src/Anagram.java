import java.util.HashMap;
import java.util.Map;

public class Anagram {
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        HashMap<Character, Integer> charToCount = new HashMap<>();
        for (char c : t.toCharArray()) {
            charToCount.put(c, charToCount.getOrDefault(c, 0) + 1);
        }

        for (char c : s.toCharArray()) {
            if (charToCount.containsKey(c)) {
                charToCount.put(c, charToCount.get(c) - 1);
            }
        }

        for (Map.Entry<Character, Integer> entry : charToCount.entrySet()) {
            if (entry.getValue() > 0) {
                return false;
            }
        }
        return true;
    }
}
