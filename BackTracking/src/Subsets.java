import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Subsets {
    //Subset (elements have no duplicates and cannot be reselected)
    /**
     * Because the elements in the collection don't need to consider the order, only [1,2,3]after 2the middle 3, if you think forward 1, then [2,1]will be [1,2]repeated with the subset that has been generated before.
     *
     * In other words, we prevent duplicate subsets by keeping the relative order of elements unchanged .
     * If the root node is taken as the 0th layer, and the elements on the branches between each node and the root node are used as the value of the node, then all the nodes in the nlayer are nall subsets of size .
     * Then go a step further, if you want to calculate all the subsets, you just need to traverse the multi-fork tree and collect the values ​​​​of all nodes?
     *
     * Read the previous article Backtracking Algorithm Core FrameworkIt should be easy for readers to understand this code. We use startparameters to control the growth of branches to avoid duplicate subsets, use trackto record the value of the path from the root node to each node, and at the same time put the path value of each node in the preorder position Collect them, and complete the traversal of the backtracking tree to collect all subsets:
     *
     * Finally, backtrackthere seems to be no base case at the beginning of the function, will it enter infinite recursion?
     *
     * In fact, no, at that start == nums.lengthtime , the value of the leaf node will be loaded res, but the for loop will not be executed, and the recursion will end.
     * */
    List<List<Integer>> res = new LinkedList<>();
    LinkedList<Integer> track = new LinkedList<>();
    public List<List<Integer>> subsets(int[] nums) {
        backtrack(nums, 0);
        return res;
    }

    private void backtrack(int[] nums, int start) {
        res.add(new LinkedList<>(track));

        for (int i = start; i < nums.length; i++) {
            //make the choice
            track.add(nums[i]);
            backtrack(nums, i + 1);
            track.removeLast();
        }
    }

    /** Subset/combination (elements can be repeated but not reselected)
     * The input of the standard subset problem just mentioned numshas no repeated elements, but if there are repeated elements, how to deal with them?
     * Check out Question 90 " Subset II"It is such a question:
     * Given an array of integers nums, which may contain repeated elements, please return all possible subsets of the array.
     *
     * Take nums = [1,2,2]for example , in order to distinguish two 2are different elements, we will write later nums = [1,2,2'].
     * Draw the tree structure of the subset according to the previous idea. Obviously, two adjacent branches with the same value will produce duplication:
     * [
     *     [],
     *     [1],[2],[2'],
     *     [1,2],[1,2'],[2,2'],
     *     [1,2,2']
     * ]
     * So we need to perform pruning. If a node has multiple adjacent branches with the same value, only the first one will be traversed, and the rest will be cut off. Do not traverse:
     * Reflected in the code, it needs to be sorted first, so that the same elements are close together, and if found nums[i] == nums[i-1], skip, we only want the front one (nums[i - 1]
     * */
    public List<List<Integer>> subsetWithDup(int[] nums) {
        Arrays.sort(nums);
        backtrackDup(nums, 0);
        return res;
    }

    private void backtrackDup(int[] nums, int start) {
        res.add(new LinkedList<>(track));

        for (int i = start; i < nums.length; i++) {
            if (i > start && nums[i] == nums[i - 1]) {
                continue;
            }
            track.addLast(nums[i]);
            backtrack(nums, i + 1);
            track.removeLast();
        }
    }

    /**
     * Finally, we have reached the last type: the input array has no repeated elements, but each element can be used infinitely.
     * Given an integer array with no repeating elements candidatesand a target sum target, find all combinations candidatesin which the sum targetof numbers is the target number . candidatesEach number in can be chosen indefinitely.
     * If you want to solve this type of problem, you have to go back to the backtracking tree. Let's think about it first. How does the standard subset/combination problem ensure that elements are not reused
     * This is equivalent to adding a branch to the previous backtracking tree. In the process of traversing this tree, an element can be used infinitely:
     * Of course, this backtracking tree will grow forever, so our recursive function needs to set a suitable base case to end the algorithm, that is, target when
     * */
    int trackSum = 0;
    public List<List<Integer>> subsetReused(int[] nums, int target) {
        if (nums.length == 0) {
            return res;
        }
        backtrackReused(nums, 0, target);
        return res;
    }

    private void backtrackReused(int[] nums, int start, int target) {
        if (trackSum == target) {
            res.add(new LinkedList<>(track));
            return;
        }
        if (trackSum > target) {
            return;
        }

        for (int i = start; i < nums.length; i++) {
            trackSum += nums[i];
            track.addLast(nums[i]);
            backtrackReused(nums, start, target);
            track.removeLast();
            trackSum -= nums[i];
        }
    }

    class review {
        List<List<Integer>> res = new LinkedList<>();
        LinkedList<Integer> track = new LinkedList<>();
        int trackSum = 0;
        public List<List<Integer>> combinationSum(int[] candidates, int target) {
            backtrack0(candidates, target, 0);
            return res;
        }

        private void backtrack0(int[] candidates, int target, int start) {
            if (trackSum == target) {
                res.add(new LinkedList<>(track));
                return;
            }

            if (trackSum > target) {
                return;
            }

            for (int i = start; i < candidates.length; i++) {
                trackSum += candidates[i];
                track.addLast(candidates[i]);
                backtrack0(candidates, target, i);
                track.removeLast();
                trackSum -= candidates[i];
            }
        }


    }
}
