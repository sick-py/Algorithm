package Main;

import java.lang.reflect.Array;
import java.util.Arrays;

public class main1 {
    /**
     * This is the 322th question of Lituo " change exchange":
     *
     * You are given k coins c1, c2 ... ck, and the number of each coin is unlimited, and then a total amount is given amount, and you are asked at least how many coins you need to make up this amount. If it is impossible to make up, the algorithm returns -1.
     * For example k = 3, the denominations are 1, 2, 5, and the total amount amount = 11. Then at least 3 coins are needed to make it out, that is, 11 = 5 + 5 + 1.
     *
     * violent recursion Optimal substructure
     * First of all, this problem is a dynamic programming problem because it has an "optimal substructure". To conform to the "optimal substructure", the subproblems must be independent of each other .
     *
     * What is mutual independence? You definitely don't want to look at the mathematical proof, I will explain it with an intuitive example.
     * For example, suppose you take an exam, and the results of each subject are independent of each other. Your original question is to get the highest total score, then your sub-question is to get the highest score in Chinese and mathematics... In order to get the highest score in each subject, you need to get the corresponding multiple-choice score for each subject Get the highest score, get the highest score for the fill-in-the-blank questions... Of course, in the end, you get full marks for each subject, which is the highest total score.
     * Got the right result: the highest overall score is the total score. Because this process conforms to the optimal substructure, the subproblems of "achieving the highest test in each subject" are independent of each other and do not interfere with each other.
     *
     * However, if you add a condition: your Chinese score and math score will mutually restrict each other, and you cannot achieve full marks at the same time. If your math score is high, your Chinese score will decrease, and vice versa.
     *
     * In this case, obviously the highest total score you can get in the test will not reach the total score, and you will get wrong results according to the idea just now. Because the sub-problems of "getting the highest in each subject" are not independent
     *
     * Back to the problem of collecting change, why do you say it conforms to the optimal substructure? Assuming you have 1, 2, 5a , and you want to find amount = 11the minimum number of coins (original question), if you know amount = 10, 9, 6the minimum number of coins (sub-question), you only need to add one to the answer of the sub-question (choose another Coins with face 1, 2, 5value ), finding the minimum value is the answer to the original question. Because the number of coins is unlimited, there is no mutual constraint between the sub-problems and they are independent of each other.
     *
     * Back to the problem of collecting change, why do you say it conforms to the optimal substructure? Assuming you have 1, 2, 5a , and you want to find amount = 11the minimum number of coins (original question), if you know amount = 10, 9, 6the minimum number of coins (sub-question), you only need to add one to the answer of the sub-question (choose another Coins with face 1, 2, 5value ), finding the minimum value is the answer to the original question. Because the number of coins is unlimited, there is no mutual constraint between the sub-problems and they are independent of each other.
     * */

    /** how to think
     * 1. Determine the base case . This is very simple. Obviously, when the target amount amountis 0, the algorithm returns 0, because the target amount has been collected without any coins.
     *
     * 2. Determine the "state", that is, the variables that will change in the original problem and sub-problems . Since the number of coins is unlimited, the denomination of the coins is also given by the topic. Only the target amount will continue to approach the base case, so the only "state" is the target amount.
     *
     * 3. Determine the "choice", that is, the behavior that causes the "state" to change . Why does the target amount change? Because you are choosing coins, every time you choose a coin, it is equivalent to reducing the target amount. So the face value of all coins is your "choice".
     *
     * 4. Clearly define the dpfunction /array . What we are talking about here is a top-down solution, so there will be a recursive dp function . Generally speaking, the parameter of the function is the amount that will change during the state transition, which is the "state" mentioned above; the return value of the function is The title asks us to calculate the quantity. As far as this question is concerned, there is only one state, that is, "target amount". The question requires us to calculate the minimum number of coins required to make up the target amount.
     *
     * So we can define the dpfunction : dp(n)Indicate, input a target amount n, and return the minimum number of coins nrequired the target amount .
     * */
    int dp(int[] coins, int amount) {
        if (amount == 0) return 0;
        if (amount < 0) return -1;

        int res = Integer.MAX_VALUE;
        for (int coin : coins) {
            int sub = dp(coins, amount - coin);
            if (sub == -1) continue;
            res = Math.min(res, sub + 1);
        }

        return res == Integer.MAX_VALUE ? -1 : res;
    }

    /** memo
     *  so the total number of sub-problems will not exceed the amount n, that is, the number of sub-problems is O(n). The time to process a subproblem is constant, still O(k), so the total time complexity is O(kn).
     * */
    int[] memo;
    int coinChange(int[] coins, int amount) {
        memo = new int[coins.length];
        Arrays.fill(memo, -666); //some value will never happen during th recursion
        return dp1(coins, amount);
    }

    private int dp1(int[] coins, int amount) {
        if (amount == 0) return 0;
        if (amount < 0) return -1;

        if (memo[amount] != -666) {
            return memo[amount];
        }

        int res = Integer.MAX_VALUE;
        for (int coin : coins) {
            int sub = dp1(coins, amount);
            if (sub == -1) continue;
            res = Math.min(res, sub + 1);
        }

        memo[amount] = (res == Integer.MAX_VALUE ? -1 : res);
        return memo[amount];
    }

    /** dp
     *  There is no difference between the "state", "selection" and base case. dpThe definition of the array is similar to the dpfunction , and the "state", that is, the target amount as a variable. But dpfunctions are reflected in function parameters, and dparrays are reflected in array indexes:
     *  dpDefinition of the array: When the target amount iis , at least dp[i]coins are required to make up .
     * */

    int coin(int[] coins, int amount) {
        int[] dp = new int[coins.length + 1];
        Arrays.fill(dp, -666);

        dp[0] = 0;

        for (int i = 0; i < dp.length; i++) {
            for (int coin : coins) {
                if (i - coin < 0) continue;
                dp[i] = Math.min(dp[i], 1 + dp[i - coin]);
            }
        }

        return dp[amount] == -666 ? -1 : dp[amount];
    }
}
