package Main;


public class main1OptimalStructureSubDPSizeDirection {

    /**
     * 1. What exactly is "optimal substructure" and what is its relationship with dynamic programming.
     *
     * 2. How to judge that a problem is a dynamic programming problem, that is, how to see whether there are overlapping sub-problems.
     *
     * 3. Why is it often seen that the size of the dp array is set to n + 1not n.
     *
     * 4. Why dynamic programming traverses dp arrays in various ways, some are traversed forward, some are traversed backwards, and some are traversed obliquely.
     *
     * */

    /**
     * The process of finding the optimal substructure is actually the process of proving the correctness of the state transition equation. If the equation conforms to the optimal substructure, you can write a brute force solution. After writing the brute force solution, you can see whether there are overlapping subproblems. If there are, then optimize , nothing is OK. This is also a routine, and readers who often brush questions should be able to understand it.
     * */

    /** Detailed explanation of the optimal substructure
     * Optimal substructure" is a specific property of certain problems and is not exclusive to dynamic programming problems. In other words, many problems actually have optimal substructures, but most of them do not have overlapping subproblems, so we do not classify them as a series of dynamic programming problems.
     *
     * Let me give you an easy-to-understand example first: Suppose there are 10 classes in your school, and you have calculated the highest test scores for each class. So now I ask you to calculate the highest grade in the school, will you calculate it? Of course, and you don't need to re-traverse the scores of the students in the whole school for comparison, but just take the largest of the 10 highest scores and it is the highest score of the whole school.
     *
     * The problem I pose to you fits into the optimal substructure : the optimal result of a larger problem can be deduced from the optimal result of the subproblem. Let you calculate the best grades of each class is a sub-question. After you know the answers to all the sub-questions, you can use this to deduce the answer to the larger question of the best grades of the students in the whole school .
     *
     * You see, such a simple problem has optimal substructure properties, just because there are obviously no overlapping subproblems, so we will definitely not be able to use dynamic programming simply to find the maximum value.
     *
     * Another example: Suppose your school has 10 classes, and you know the maximum score difference of each class (the difference between the highest score and the lowest score). So now I ask you to calculate the largest score difference among all the students in the school, will you calculate it? You can find a way to calculate it, but it certainly cannot be deduced from the known maximum score difference of these 10 classes. Because the maximum score difference of the 10 classes does not necessarily include the maximum score difference of the whole school, for example, the maximum score difference of the whole school may be the difference between the highest score of class 3 and the lowest score of class 6.
     *
     * This time, the question I put forward to you does not conform to the optimal substructure , because you can’t use the optimal value of each class to deduce the optimal value of the whole school, and you can’t use the optimal value of subproblems to deduce larger-scale problems.
     *
     * So what should we do if we encounter this optimal substructure failure? The strategy is: transform the problem .
     * To transform the problem is to transform the problem equivalently: Isn't the maximum score difference equivalent to the difference between the highest score and the lowest score? Isn't that the highest and lowest score required? Isn't it the first question we discussed?
     *
     *
     * To give another common but very simple example, it is not difficult to find the maximum value of a binary tree (for simplicity, assume that the values in the nodes are all non-negative numbers):
     * int maxVal(TreeNode root) {
     *         if (root == null) {
     *             return -1;
     *         }
     *         int left = maxVal(root.left);
     *         int right = maxVal(root.right);
     *         return Math.max(left, right);
     *     }
     *
     * */

    /** how to see overlapping subproblems
     * directly judge whether there are overlapping sub-problems through the recursive framework .
     *
     * But even if I write a violent solution, how to judge whether there are overlapping subproblems in this solution
     * First of all, the simplest and rude way is to draw a picture, draw the recursive tree, and see if there are any duplicate nodes .
     *
     * such as in Minimum path and problem, we wrote such a brute force solution:
     *
     * int dp(int[][] grid, int i, int j) {
     *     if (i == 0 && j == 0) {
     *         return grid[0][0];
     *     }
     *     if (i < 0 || j < 0) {
     *         return Integer.MAX_VALUE;
     *     }
     *
     *     return Math.min(
     *             dp(grid, i - 1, j),
     *             dp(grid, i, j - 1)
     *         ) + grid[i][j];
     * }
     * You don't need to read the previous article, just look at the code of this function, you can see that the parameters of this function i, jare , that is, the "state" is (i, j)the value of , can you judge whether there are overlapping subproblems in this solution?
     * Assuming the input i = 8, j = 7, the recursive tree of the two-dimensional state is as shown in the figure below, obviously there are overlapping sub-problems:
     *
     * But after a little thought, you can know that there is no need to draw a picture at all, and you can directly judge whether there are overlapping sub-problems through the recursive framework .
     * The specific operation is to directly delete the code details and abstract the recursive framework of the solution:
     *
     * int dp(int[][] grid, int i, int j) {
     *     dp(grid, i - 1, j), // #1
     *     dp(grid, i, j - 1)  // #2
     * }
     *
     * It can be seen that i, jthe value of is constantly decreasing, so I ask you a question: If I want to (i, j)transfer from state to (i-1, j-1), how many paths are there?
     *
     * Obviously there are two paths, either (i, j) -> #1 -> #2or (i, j) -> #2 -> #1, more than one, indicating that (i-1, j-1)will be calculated multiple times, so there must be overlapping subproblems.
     * */

    /** Size setting of dp array
     * In theory, you can define it however you want, as long as you handle the base case according to the definition .
     * dpLooking at the array again , you can of course also define the edit distance between dp[i][j]storage s1[0..i]and s2[0..j], but the question is what about the base case? How can the index be -1?
     *
     * So we initialize the dparray to int[m+1][n+1]offset the index by one bit, reserve index 0 as the base case to represent the empty string, and then dp[i+1][j+1]define thes1[0..i] edit distance between storing and .s2[0..j]
     * */

    /** Traversal direction
     * sometimes we traverse forward:
     * Sometimes we iterate in reverse:
     * Sometimes it is possible to traverse diagonally:
     * Even more confusing is that sometimes it is found that both forward and reverse traversal can get the correct answer, for example, we are in The Group Extinction Stock ProblemIn some places, both pros and cons can be used.
     *
     * If you observe carefully, you can find the reason, you just need to hold on to two points:
     * 1. During the traversal process, the required state must have been calculated .
     * 2. After the traversal, the location where the result is stored must have been calculated .
     *
     * */




}
