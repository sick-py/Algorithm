package backpack;

import java.util.PriorityQueue;

public class LastStoneWeight {
    public int lastStoneWeight(int[] stones) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b)->(b - a));
        for (int i : stones) {
            maxHeap.offer(i);
        }
        while (maxHeap.size() > 1) {
            int a = maxHeap.poll(), b = maxHeap.poll();
            maxHeap.offer(a - b);
        }
        return maxHeap.poll();
    }

    //One possible solution is to use dynamic programming to find the maximum sum of stones that is less than or equal to half of the total sum of stones. Then, the final weight is equal to the total sum minus twice that maximum sum2.

    /**
     * This question eaquals to partition an array into 2 subsets whose difference is minimal
     * (1) S1 + S2  = S
     * (2) S1 - S2 = diff
     *
     * ==> -> diff = S - 2 * S2  ==> minimize diff equals to  maximize S2
     *
     * Now we should find the maximum of S2 , range from 0 to S / 2, using dp can solve this
     * dp[i][j]   = {true if some subset from 1st to j'th has a sum equal to sum i, false otherwise}
     *     i ranges from (sum of all elements) {1..n}
     *     j ranges from  {1..n}
     *
     * same as 494. Target Sum
     * */

    public int lastStoneWeightII(int[] stones) {
        int S = 0, S2 = 0, sum;
        for (int i : stones) S += i;
        sum = S;
        S /= 2;
        int n = stones.length;
        boolean[][] dp = new boolean[n + 1][S + 1];
        for (int i = 0; i <= n; i++){
            dp[i][0] = true;
        }
        for (int i = 1; i <= n; i++) {
            for (int s = 1; s <= S; s++) {
                if (dp[i - 1][s] || (s >= stones[i - 1] && dp[i - 1][s - stones[i-1]])) {
                    dp[i][s] = true;
                    S2 = Math.max(S2, s);
                }
            }
        }
        return sum - 2 * S2;
    }
}
