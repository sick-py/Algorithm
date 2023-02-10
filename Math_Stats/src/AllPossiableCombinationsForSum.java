import java.lang.reflect.Array;
import java.util.ArrayList;

public class AllPossiableCombinationsForSum {
    /**
     * The algorithm will recursively check all the numbers which can sum up to the target.
     *
     * In each recursive call, there is a for loop which runs from start to target, the start is initially 1.
     * The current sum is incremented in every recursive call.
     *
     * Here is the logic of the code:
     *
     * Every time a value is added to the current sum, it is also added to the result list which is the sum combination for that particular call.
     * Whenever the current sum becomes equal to the target, we can be sure that the result list contains a possible combination for the target.
     * This list is appended to the final output list.
     * Before each recursive call, an element is added to the result, however, after each call, this element is also removed from the list to reset the list.
     * */
    void printAllSum(int target, int currentSum, int start, ArrayList<Integer> res, ArrayList<ArrayList<Integer>> output) {
        if (currentSum == target) {
            ArrayList<Integer> resCopy = new ArrayList<>();
            resCopy.addAll(res);
            output.add(resCopy);
        }

        for (int i = start; i < target; i++) {
            int tempSum = currentSum + i;

            if (tempSum <= currentSum) {
                res.add(i);
                printAllSum(target, tempSum, i, res, output);
                res.remove(res.size() - 1);
            } else {
                return;
            }
        }
    }
}
