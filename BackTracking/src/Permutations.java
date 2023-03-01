import javax.naming.StringRefAddr;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Permutations {
    /**
     * Whether it is permutation, combination or subset problem, simply put, it is nothing more than allowing you to take some elements from with given rules. There are mainly the following variants:
     *
     * Form 1. Elements are non-repeatable and cannot be reselected, that is, the elements numsin are unique, and each element can only be used once at most. This is also the most basic form .
     *
     * Take combinations as an example, if input nums = [2,3,6,7], there should be only combinations whose sum is 7 [7].
     *
     * Form 2. Elements can be repeated but not reselected, that is, elements numsin can be repeated, and each element can only be used once at most .
     *
     * Take the combination as an example, if input nums = [2,5,2,1,2], the combination whose sum is 7 should have two [2,2,2,1]sums [5,2].
     *
     * Form 3, the element is non-repeatable and can be reselected, that is, the elements numsin are unique, and each element can be used several times .
     * */

    /**
     * But no matter how the form changes, its essence is to enumerate all the solutions, and these solutions present a tree structure, so reasonable use of the backtracking algorithm framework and a slight modification of the code framework can eliminate these problems in one go .
     *  remember the backtracking tree of the following subset problem and permutation problem, you can solve all permutation and combination subset related problems:
     *  First of all, the combination problem and the subset problem are actually equivalent, which will be discussed later; as for the three variations mentioned above, they are nothing more than cutting off or adding some branches to the two trees .
     * */

    /** About permutations
     * Given an array with no duplicate numbersnums , return all possible full permutations of it .
     *
     * The combination/subset problem just mentioned uses startvariables to ensure nums[start]that only the elements nums[start+1..]in the element, and the relative position of the elements is fixed to ensure that no repeated subsets appear.
     *
     * But the permutation problem itself allows you to exhaustively enumerate the positions of the elements, nums[i]and the elements nums[i]on the left , so the previous set can't work, and you need to use an additional usedarray to mark which elements can still be selected .
     * */
    List<List<Integer>> res = new LinkedList<>();
    LinkedList<Integer> track = new LinkedList<>();
    boolean[] used;

    public List<List<Integer>> permute(int[] nums) {
        used = new boolean[nums.length];
        backtrack(nums);
        return res;
    }

    private void backtrack(int[] nums) {
        if (track.size() == nums.length) {
            res.add(new LinkedList<>(track));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (used[i]) {
                continue;
            }
            used[i] = true;
            track.addLast(nums[i]);
            backtrack(nums);
            track.removeLast();
            used[i] = false;
        }
    }

    /**
     * Given a sequence that can contain repeated numbers nums, please write an algorithm to return all possible full permutations. The function signature is as follows:
     * */
    public List<List<Integer>> permuteDup(int[] nums) {
        Arrays.sort(nums);
        used = new boolean[nums.length];
        backtrackDup(nums);
        return res;
    }

    private void backtrackDup(int[] nums) {
        if (track.size() == nums.length) {
            res.add(new LinkedList<>(track));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (used[i]) {
                continue;
            }

            if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) {
                //if the front duplicate is not used, we skip, we make sure we select2` only if we have selected 2
                continue;
            }
            /**
             * Assuming the input is nums = [1,2,2'], the standard full permutation algorithm would yield the following answer:
             * [
             *     [1,2,2'],[1,2',2],
             *     [2,1,2'],[2,2',1],
             *     [2',1,2],[2',2,1]
             * ]
             * Obviously, there are duplicates in this result, such as [1,2,2']and [1,2',2]should only be counted as the same permutation, but are counted as two different permutations.
             * So the key now is how to design pruning logic to remove this duplication?
             *
             * The answer is that the relative position of the same elements in the arrangement is guaranteed to remain the same .
             * For example, in nums = [1,2,2']this example, I keep 2 in front of 2' the array.
             * In this case, you can only pick 3 permutations from the above 6 permutations that meet this condition:
             *
             * When there is a repeated element, such as input nums = [1,2,2',2''], it will be selected 2'only if 2 has been used. Similarly, it will be selected 2'' only if 2' has been used, which ensures the relative position of the same element in the arrangement Guaranteed to be fixed .
             *
             * Let’s expand here. If you !used[i - 1]change used[i - 1], you can actually pass all the test cases, but the efficiency will decrease. Why?
             * The reason why this modification will not cause errors is because this way of writing is equivalent to maintaining 2'' -> 2' -> 2the relative order of and can eventually achieve the effect of deduplication.
             * But why does writing efficiency drop in this way? Because there are not enough branches cut off by this way of writing.
             *
             * */

            track.addLast(nums[i]);
            used[i] = true;
            backtrackDup(nums);
            used[i] = false;
            track.removeLast();
        }
    }

    /**
     * There is no similar topic on Likou. Let's think about it first. numsWhen the elements in the array are non-repetitive and can be selected again, what permutations will there be?
     *
     * For example, input nums = [1,2,3], then there are 3^3 = 27 full permutations under this condition:
     *
     * The standard full permutation algorithm uses used arrays for pruning to avoid reusing the same element. If elements are allowed to be reused, just let yourself go and remove all the pruning logic of used the array .
     * */
    public List<List<Integer>> permuteReused(int[] nums) {
        backtrackReused(nums);
        return res;
    }

    private void backtrackReused(int[] nums) {
        if (track.size() == nums.length) {
            res.add(track);
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            track.addLast(nums[i]);
            backtrackReused(nums);
            track.removeLast();
        }
    }

    class review {
        List<List<Integer>> res = new LinkedList<>();
        LinkedList<Integer> path = new LinkedList<>();
        boolean[] used;
        public List<List<Integer>> permuteUnique(int[] nums) {
            Arrays.sort(nums);
            used = new boolean[nums.length];
            backtrackR1(nums);
            return res;
        }

        private void backtrackR1(int[] nums) {
            if (path.size() == nums.length) {
                res.add(new LinkedList<>(path));
                return;
            }

            for (int i = 0; i < nums.length; i++) {
                if (used[i]) {
                    continue;
                }

                if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) {
                    continue;
                }

                path.addLast(nums[i]);
                used[i] = true;
                backtrackR1(nums);
                used[i] = false;
                path.removeLast();
            }
        }

        public List<List<Integer>> permute(int[] nums) {
            used = new boolean[nums.length];
            backtrackR0(nums);
            return res;
        }

        private void backtrackR0(int[] nums) {
            if (path.size() == nums.length) {
                res.add(new LinkedList<>(path));
                return;
            }

            for (int i = 0; i < nums.length; i++) {
                if (used[i]) {
                    continue;
                }
                used[i] = true;
                path.addLast(nums[i]);
                backtrackR0(nums);
                path.removeLast();
                used[i] = false;
            }
        }
    }


}
