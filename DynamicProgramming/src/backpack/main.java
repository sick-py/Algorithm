package backpack;

public class main {
    /** 0-1 backpack
     * You are given N items and W bag, each item has two attributes of weight and value. Among them, the weight of the i th item is wt[i], and the value is val[i]. Now let you use this backpack to pack items, what is the maximum value that can be loaded?
     *
     * The first step is to clarify two points, "state" and "selection" .
     *
     * Let's talk about the state first, how can we describe a problem situation? As long as a few items and the capacity of a backpack are limited, a backpack problem is formed. So there are two states, namely "capacity of backpack" and "selectable items" .
     *
     * Besides the choice, it is easy to think, what can you choose for each item? The choice is "put it in the backpack" or "not put it in the backpack" .
     *
     * After understanding the state and selection, the dynamic programming problem is basically solved, as long as you put this framework in place, you are done:
     *      * for state1 in state1 all possible：
     *      *     for state2 in state2 all possible：
     *      *         for ...
     *      *             dp[state1][state2][...] = most value(choice1，choice2...)
     *
     *
     * The second step is to clarify the definition of the dp array .
     * First look at the "state" just found, there are two, which means we need a two-dimensional dp array .
     * dp[i][w]The definition of is as follows: For the iprevious items, the capacity of the current knapsack is w, and the maximum value that can be loaded in this case is dp[i][w].
     * For example, if dp[3][5] = 6the meaning is: for a given series of items, if only the first 3 items are selected, when the backpack capacity is 5, the maximum value that can be loaded is 6.
     *
     *
     * The third step is to think about the logic of state transition based on "choice" .
     * To put it simply, how do you use the code to express "put items iinto backpack" and "not put items iinto the backpack" in the pseudo-code above?
     * This is combined with the definition of the dp array to see how these two choices affect the state:
     * If you don't pack i this knapsack , then obviously, the maximum value dp[i][w]should be equal to dp[i-1][w], inheriting the previous result.
     *
     * If you packed the ith item into your knapsack , then dp[i][w]should be equal to val[i-1] + dp[i-1][w - wt[i-1]].

     * First, since the array index starts from 0, and in our idefinition counts from 1, so val[i-1]and wt[i-1]represent ithe value and weight of the item.
     * */
    static int bag0(int W, int N, int[] wt, int[] val) {
        assert N == wt.length;
        int[][] dp = new int[N + 1][W + 1]; //base case == 0 is already done now

        for (int i = 1; i <= N; i++) {
            for (int w = 1; w <= W; w++) {
                if (w - wt[i - 1] < 0) {
                    //we can only choose to not put into the bag
                    dp[i][w] = dp[i - 1][w];
                } else {
                    dp[i][w] = Math.max(
                            dp[i - 1][w],
                            dp[i - 1][w - wt[i - 1]] + val[i - 1]
                    );
                }
            }
        }

        for (int i = 0; i <= N; i++) {
            for (int j = 0; j <= W; j++) {
                System.out.printf(" %d ", dp[i][j]);
            }
            System.out.printf("\n");
        }
        return dp[N][W];
    }


    public static void main(String[] args) {
        int N = 3, W = 4;
        int[] wt = new int[] {2, 1, 3};
        int[] val = new int[] {4, 2, 3};
        bag0(W, N, wt, val);
    }


}
