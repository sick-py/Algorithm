import java.util.LinkedList;
import java.util.List;

public class Main {
    /**
     * what is back track
     * It is essentially a violent exhaustive algorithm. The nuance between the backtracking algorithm and the DFS algorithm is that the backtracking algorithm traverses the branches, and the DFS algorithm traverses the nodes.
     *
     * Standing on a node of the backtracking tree, you only need to think about 3 questions:
     *
     * 1. Path: the choice that has been made.
     * 2. Choice list: the choices you can make currently.
     * 3. End condition: the condition that reaches the bottom of the decision tree and cannot make any more choices.
     *
     * But it must be noted that no matter how optimized it is, it conforms to the backtracking framework, and the time complexity cannot be lower than O(N!), because it is unavoidable to exhaust the entire decision tree. This is also a feature of the backtracking algorithm. Unlike dynamic programming, which has overlapping subproblems that can be optimized, the backtracking algorithm is purely brute force exhaustion, and its complexity is generally high .
     *
     * template
     * */

    /**
     * result = []
     * def backtrack(path, choice list):
     *     if (it's time to over):
     *         result.add(path)
     *         return
     *
     *     for choice in choice list:
     *         make the choice(delete this choice in choice list and add it to the path)
     *         backtrack(path, choice list)
     *         undo the choice
     *
     * Its core is the recursion in the for loop. It is very simple to "make a selection" before the recursive call and "undo the selection" after the recursive call .
     * */

    /**
     * take the full permutation as an example (The full permutation problem we are discussing this time does not include repeated numbers)
     *
     * For example, if you give three numbers [1,2,3], you will definitely not exhaustively list them irregularly, generally like this:
     *
     * First fix the first digit to be 1, then the second digit can be 2, then the third digit can only be 3; then the second digit can be changed to 3, and the third digit can only be 2; then it can only be changed The first one becomes 2, and then exhaustively enumerates the last two...
     *
     * In fact, this is the backtracking algorithm, which we can use in high school without a teacher, or some students directly draw the following backtracking tree:
     *
     * Just traverse the tree from the root and record the numbers on the path, which is actually all the full permutations. We might as well call this tree the "decision tree" of the backtracking algorithm .
     *
     * Why do you say this is a decision tree, because you are actually making decisions at each node . For example, if you stand on the red node in the figure below: You are making a decision now, you can choose the 1 branch or the 3 branch. Why can only choose between 1 and 3? Because the 2 branch is behind you, you have made this choice before, and the full arrangement does not allow repeated use of numbers.
     * Now you can answer the first few nouns: [2]it is "path", which records the choices you have made; [1,3]it is "choice list", which means the choices you can make currently; \
     *
     * The backtrack function actually like a pointer. When walking on the tree, the attributes of each node must be maintained correctly. Whenever we go to the bottom leaf node of the tree, its "path" is a full array, its "choice list" is empty
     *
     *
     * */
    List<List<Integer>> res = new LinkedList<>();

    List<List<Integer>> permute(int[] nums) {
        LinkedList<Integer> track = new LinkedList<>();
        boolean[] used = new boolean[nums.length];
        backTrack(nums, track, used);
        return res;
    }

    private void backTrack(int[] nums, LinkedList<Integer> track, boolean[] used) {
        if (track.size() == nums.length) {
            res.add(track);
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (used[i]) {
                continue;
            }
            //make the choice
            track.add(nums[i]);
            used[i] = true;
            //backtrack, dive into next level of the decision tree
            backTrack(nums, track, used);
            //undo
            used[i] = false;
            track.removeLast();
        }
    }

    public static void main(String[] args) {
        System.out.println("Hello world!");
    }
}