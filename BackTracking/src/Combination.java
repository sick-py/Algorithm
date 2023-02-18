import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Combination {
    /**
     * If you can successfully generate all weightless subsets, then you can generate all weightless combinations with a little code modification.
     * For example, let you take 2 elements nums = [1,2,3]in to form all the combinations, how do you do it?
     * If you think about it for a while, you will find that all combinations of size 2 are  all subsets of size 2.
     * So I say combination and subset are the same: a combination k of is ka subset of size .
     * */
    List<List<Integer>> res = new LinkedList<>();
    LinkedList<Integer> track = new LinkedList<>();
    public List<List<Integer>> combine(int n, int k) {
        backtrack(1, n, k);
        return res;
    }

    private void backtrack(int start, int n, int k) {
        if (track.size() == k) {
            res.add(new LinkedList<>(track)); //pay attention to new, because it's reference
            return;
        }

        for (int i = start; i < n; i++) {
            track.addLast(i);
            backtrack(i + 1, n, k);
            track.removeLast();
        }
    }

    /**
     * We said that the combination problem and the subset problem are equivalent , so let's look at a combination problem directly, this is the 40th problem of Lituo Portfolio Sum II":
     * */
    int trackSum = 0;
    public List<List<Integer>> combineDup(int[] nums, int target) {
        if (nums.length == 0) return res;
        Arrays.sort(nums);
        backtrack2(nums, 0, target);
        return res;
    }

    private void backtrack2(int[] nums, int start, int target) {
        if (trackSum == target) {
            res.add(new LinkedList<>(track));
            return;
        }

        if (trackSum > target) {
            return;
        }

        for (int i = start; i < nums.length; i++) {
            if (i > start && nums[i] == nums[i - 1]) {
                continue;
            }
            trackSum += nums[i];
            track.addLast(nums[i]);
            backtrack2(nums, i + 1, target);
            track.removeLast();
            trackSum -= nums[i];
        }
    }

}
