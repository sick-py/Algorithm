import java.util.Stack;

public class MinimunRemoveValidParenthese {
    /**
     * Given a string with matched and unmatched parentheses, remove the minimum number of parentheses with all matching parentheses in the string.
     *
     * We’ll traverse the array to:
     *
     * Check whether a delimiter is unmatched or not.
     * Note the index of the unmatched delimiter so that we can remove them later on.
     * To do this, we go over each character one by one. If the character is a delimiter, we push it and its index in the stack; otherwise, we move on to the next character. To check whether the delimiter is matched or not, we make the following check at each character:
     * If the current character is an ending delimiter and the character at the top of the stack is a starting one, then both of them are matched.
     *
     * In such a case, we do not add the current character to the stack, and we also pop an element from the stack to remove the starting delimiter. This way, we do not have any matched delimiter in the stack.
     *
     * Now we make a new string and start appending characters from the end of the original string in it. We start from the end because the positions of the unmatched delimiters are at the top of the stack. In the end, we reverse the string and return it.
     * */
    class Pair {
        int first;
        char second;

        public Pair(int a, char b) {
            first = a;
            second = b;
        }
    }

    String remove(String s) {
        Stack<Pair> stack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            if (stack.size() > 0 && stack.peek().second == '(' && s.charAt(i) == ')') {
                stack.pop();
            } else if (s.charAt(i) == ')' || s.charAt(i) == '(') {
                Pair p = new Pair(i, s.charAt(i));
                stack.push(p);
            }
        }

        StringBuilder res = new StringBuilder();

        int count = 0, c = stack.size();
        for (int x = s.length() - 1; x >= 0; x--) {
            if (count < c && stack.peek().first == x) {
                stack.pop();
                count++;
            } else {
                res.append(s.charAt(x));
            }
        }

        return res.reverse().toString();
    }

    String remove1(String s) {
        Stack<Pair> stack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            if (stack.size() != 0 && stack.peek().second == '(' && s.charAt(i) == ')') {
                stack.pop();
            } else if (s.charAt(i) == '(' || s.charAt(i) == ')') {
                stack.push(new Pair(i, s.charAt(i)));
            }
        }

        StringBuilder res = new StringBuilder();
        int count = 0, c = stack.size();
        for (int i = s.length() - 1; i >= 0; i--) {
            if (count < c && stack.peek().first == i) {
                stack.pop();
                count++;
            } else {
                res.append(s.charAt(i));
            }
        }

        return res.reverse().toString();
    }
}
