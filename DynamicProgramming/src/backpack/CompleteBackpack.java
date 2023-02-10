package backpack;

public class CompleteBackpack {
    /**
     * Given coins of different denominations coinsand a total amount amount, write a function to count the number of combinations of coins that make up the total amount. Suppose there are an infinite number of coins of each denomination .
     * For example, input amount = 5, coins = [1,2,5], the algorithm should return 4, because there are 4 ways to make up the target amount:
     * 5=5
     * 5=2+2+1
     * 5=2+1+1+1
     * 5=1+1+1+1+1
     *
     * The biggest difference between this problem and the two knapsack problems we mentioned earlier is that the number of each item is infinite, which is the legendary " complete knapsack problem ". There is only a slight change in the transfer equation.
     * */

    /** Solving steps
     * The first step is to clarify two points, "state" and "selection" .
     * There are two states, namely "the capacity of the backpack" and "selectable items". The choice is "put in the backpack" or "not put in the backpack".
     * After understanding the state and selection, the dynamic programming problem is basically solved, as long as you put this framework in place, you are done:
     *
     *      * for state1 in state1 all possible：
     *      *     for state2 in state2 all possible：
     *      *         for ...
     *      *             dp[state1][state2][...] = most value(choice1，choice2...)
     *
     *
     * The second step is to clarify the definition of the dparray .
     * First look at the "state" just found, there are two, which means we need a two-dimensional dparray .
     * dp[i][j]is defined as follows:
     * If only the ifirst (which can be reused), when the backpack capacity is j, there dp[i][j]are ways to fill the backpack.
     * In other words, the translation back to our title means:
     * If you only use the denominations coinsof the first i( icounting from 1) coins in , if you want to make up the amount j, there are dp[i][j]ways to do it .
     *
     * After the above definition, we can get:
     * The base case is dp[0][..] = 0, dp[..][0] = 1. i = 0It means that no coin denomination is used, and it is obviously impossible to make up any amount in this case; it j = 0means that the target amount that needs to be made up is 0, so doing nothing is the only way to make up.
     *
     * The third step is to think about the logic of state transition based on "choice" .
     * If you don't put ithis item into your backpack , that is to say, you don't use coins of coins[i-1]this denomination, then jthe number of ways to make up the denomination dp[i][j]should be equal to dp[i-1][j], inheriting the previous result.
     * If you put ithis item in your backpack , that is to say, you use coins of coins[i-1]this denomination, then dp[i][j]should be equal to dp[i][j-coins[i-1]].
     * For example, if you want to use a coin with a denomination of 2 to make up an amount of 5, then if you know how to make up an amount of 3 and add a coin with a face value of 2, you can make up 5.
     * */
    int change(int amount, int[] coins) {
        int n = coins.length;
        int[][] dp = new int[n + 1][amount + 1];

        for (int i = 0; i <= n; i++) {
            dp[i][0] = 1;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= amount; j++) {
                if (j - coins[i] < 0) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - coins[i - 1]]; //here i is the infinity
                }
            }
        }
        return dp[n][amount];
    }

    /** compress the space
     * Iterating j from 1 to back is equivalent to using dp[j-coins[i]] immediately when it is calculated in the ith iteration, which actually means dp[i][[j-coins[i]].
     * Iterating j from the back to 1 is equivalent to dp[j-coins[i]] is the old result saved in the i - 1 iteration, which actually represents dp[i - 1][[j-coins[i]]. The state transition equation needs dp[i][j-coins[i]], so after compressing into one dimension, iterate j from front to back.
     * */
    int change1(int amount, int[] coins) {
        int n = coins.length;
        int[] dp = new int[amount + 1];
        dp[0] = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= amount; j++) {
                if (j - coins[i - 1] < 0) {
                    dp[j] = dp[j];
                } else {
                    dp[j] = dp[j] + dp[j - coins[i - 1]];
                }
            }
        }
        return dp[amount];
    }
}
