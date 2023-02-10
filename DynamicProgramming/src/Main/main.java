package Main;

public class main {
    /** What is dynamic programming?
     * First, the general form of the dynamic programming problem is to find the most value .
     * such as asking you to find the longest increasing subsequence, the minimum edit distance, and so on.
     * Since it is the most valuable requirement, what is the core problem? The core problem of solving dynamic programming is exhaustion . Because the most value is required, all feasible answers must be exhausted, and then find the most value among them.
     *
     * First of all, although the core idea of dynamic programming is to exhaustively seek the maximum value, the problem can be ever-changing. It is not an easy task to exhaustively enumerate all feasible solutions.
     *
     * You need to master recursive thinking. Only by listing the correct "state transition equation " , in order to be exhaustive correctly.
     *
     * Moreover, you need to judge whether the algorithm problem has an "optimal substructure" and whether the maximum value of the original problem can be obtained through the maximum value of the subproblem.
     *
     * In addition, there are "overlapping sub-problems" in the dynamic programming problem , and the efficiency will be very low if brute force exhaustion, so you need to use "memory" or "DP table" to optimize the exhaustive process and avoid unnecessary calculations.
     *
     * The "overlapping subproblems", "optimal substructure", and "state transition equations" mentioned above are the three elements of dynamic programming.
     *
     * The specific meaning will be explained in detail with examples, but in the actual algorithm problem, it is the most difficult to write the state transition equation, which is why many friends find the dynamic programming problem difficult. Let me provide a thinking framework I summarized , to help you think about the state transition equation:
     *
     * Clarify the base case ->
     * Clarify the "state" ->
     * Clarify the "choice" ->
     * Define the meaning of the dparray /function .
     *
     * According to the above routine, the final solution code will be the following framework:
     * # from the top to bottom
     * def dp(state1, state2, ...):
     *     for choice in choices:
     *         # state changed
     *         result = most value(result, dp(state1, state2, ...))
     *     return result
     *
     * # from the bottom to the top
     * #  base case
     * dp[0][0][...] = base case
     * # state transition
     * for state1 in state1 all possible：
     *     for state2 in state2 all possible：
     *         for ...
     *             dp[state1][state2][...] = most value(choice1，choice2...)
     * */

    /** Fibonacci Sequence for OverLapping subproblems
     *
     * (the Fibonacci sequence does not find the maximum value, so strictly speaking, it is not a dynamic programming problem)
     * */

    //Violent Recursion
    int fib0(int n) {
        if (n == 1 || n == 2) return 1;
        return fib0(n - 1) + fib0(n - 2);
    }

    /**
    it is very inefficient. Where is the inefficiency? Assuming n = 20, please draw the recursion tree:
    if I want to calculate the original problem f(20), I have to calculate the sum of the sub-problems f(19)first f(18), and then I need to calculate the sum of the sub-problems firstf(19) by calculate f(18) f(17) , and so on. When the last or , the result is known, and the result can be returned directly, and the recursive tree no longer grows downward.f(18)f(17)f(1)f(2)
    PS: Whenever you encounter a problem that requires recursion, it is best to draw a recursive tree. This will help you analyze the complexity of the algorithm and find the reason for the inefficiency of the algorithm.
     How to calculate the time complexity of recursive algorithm? It is to multiply the number of subproblems by the time required to solve a subproblem .
    First calculate the number of subproblems, that is, the total number of nodes in the recursive tree. Obviously, the total number of binary tree nodes is exponential, so the number of sub-problems is O(2^n).
     * Observing the recursion tree, it is obvious that the reason for the inefficiency of the algorithm is found: there are a lot of repeated calculations, such as f(18)being calculated twice, and you can see that f(18)， the recursion tree rooted at is huge, and it will cost a lot to calculate it again time. What's more, more f(18)than one node is repeatedly calculated, so this algorithm is extremely inefficient.
     *
     * we can improve it with memory
     * we can create a "memo", and don't rush to return after calculating the answer to a sub-question every time, first record it in the "memo" and then return; every time we encounter a sub-question Go to the "memorandum" to check it first. If you find that this problem has been solved before, just take out the answer and use it, don't waste time to calculate it.
     *
     * In fact, the recursive algorithm with "memorandum" transforms a recursive tree with huge redundancy into a recursive graph without redundancy through "pruning", which greatly reduces sub-problems (ie, recursive the number of nodes in the graph).
     *
     * How to calculate the time complexity of recursive algorithm? It is to multiply the number of subproblems by the time required to solve a subproblem .
     *
     * The number of sub-problems is the total number of nodes in the graph. Since there is no redundant calculation in this algorithm, the sub-problems are f(1), f(2), f(3)... f(20), and the number is proportional to the input size n = 20, so the number of sub-problems is O(n).
     */
    int fib1(int[] memo, int n) {
        if (n == 1 || n == 2) return 1;
        if (memo[n] != 0) return memo[n];
        memo[n] = fib1(memo, n - 1) + fib1(memo, n - 2);
        return memo[n];
    }

    /**
     * In fact, this solution is almost the same as the common dynamic programming solution, except that this solution is a "recursive" solution "top-down". Our more common dynamic programming code is "bottom-up" for "recursive" Push" to solve.
     *
     * What is "top-down"? Note that the recursive tree (or graph) we drew just now extends from top to bottom, starting from a large-scale original problem. For example f(20), gradually decomposing the scale downward until f(1)and f(2)these two base cases, and then Returning to the answer, this is called "top-down".
     * What is "bottom up"? Conversely, we directly push up from the bottom, the simplest, the smallest problem size, f(1)and until we reach the answer we want . This is the idea of ​​​​"recursion", which is why dynamic programming generally breaks away from recursion, but completes calculations by loop iterations.
     *
     * Inspired by the "memorandum" in the previous step, we can separate this "memorandum" into a table, usually called a DP table.
     * */

    /** state transition equation
     * Here, the term "state transition equation" is introduced, which is actually a mathematical form describing the structure of the problem:
     * f(n)The function parameters of will keep changing, so you nthink a state, this state nis derived from state n - 1and state n - 2transition (addition), which is called state transition.
     *
     * As long as you write a violent solution, the optimization method is nothing more than using a memo or DP table, and there is no mystery at all.
     * */
    int fib2(int n) {
        if (n == 0) return 0;
        int[] dp = new int[n + 1];

        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    /**
     * Careful readers will find that according to the state transition equation of the Fibonacci sequence, the current state is only related to the previous two states. In fact, there is no need for such a long DP table to store all the states. Just find a way to store the previous Two states will do.
     * Therefore, it can be further optimized to reduce the space complexity to O(1). This is our most common algorithm for calculating Fibonacci numbers:
     * */
    int fib3(int n) {
        if (n == 0 || n == 1) return n;
        int dp0 = 0, dp1 = 1, dp2 = 1;
        for (int i = 2; i <= n; i++) {
            dp2 = dp0 + dp1;
            dp1 = dp2;
            dp0 = dp1;
        }
        return dp2;
    }

    /**  Take a real dynamical problem in main1
     * */
}
