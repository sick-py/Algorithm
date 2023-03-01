import java.util.LinkedList;
import java.util.List;

public class LetterCombinationOfPhoneNumber {
    String[] map = new String[] {
            "", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"
    };

    List<String> res = new LinkedList<>();

    public List<String> letterCombinations(String digits) {
        if (digits.isEmpty()) {
            return res;
        }
        backTrack(digits, 0, new StringBuilder());
        return res;
    }

    private void backTrack(String digits, int start, StringBuilder sb) {
        if (sb.length() == digits.length()) {
            res.add(sb.toString());
            return;
        }

        for (char c : map[digits.charAt(start) -'0'].toCharArray()) {
            sb.append(c);
            backTrack(digits, start + 1, sb);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}
