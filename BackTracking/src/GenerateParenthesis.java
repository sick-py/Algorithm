import java.util.ArrayList;
import java.util.List;

public class GenerateParenthesis {
    /**
     * The goal is to print a string of “(“ ,”)” in certain order. The length of string is 2n. The constraints are that “(“s need to match “)”s.
     * Without constraints, we just simply print out “(“ or “)” until length hits n. So the base case will be length ==2 n, recursive case is print out “(“ and “)”. The code will look like
     *
     * //base case
     * if(string length == 2*n) {
     * add(string);
     * return;
     * }
     * //recursive case
     * add a “(“
     * add a “)"
     *
     * Let’s add in constraints now. We need to interpret the meanings of constraints. First, the first character should be “(“. Second, at each step, you can either print “(“ or “)”, but print “)” only when there are more “(“s than “)”s. Stop printing out “(“ when the number of “(“ s hit n. The first actually merges into the second condition.
     * */
    public List<String> generateParenthesis(int n) {
        List<String> list = new ArrayList<>();
        backTrack(list, "", 0, 0, n);
        return list;
    }

    private void backTrack(List<String> list, String str, int open, int close, int max) {
        if (str.length() == max * 2) {
            list.add(str);
            return;
        }

        if (open < max) {
            backTrack(list, str + "(", open + 1, close, max);
        }
        if (close < open) {
            backTrack(list, str + ")", open, close + 1, max);
        }
    }
}
