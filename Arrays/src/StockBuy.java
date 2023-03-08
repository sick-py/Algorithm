public class StockBuy {
    /**
     * We’re given an array of daily stock prices (integers for simplicity). Return the buying and selling prices for making the maximum profit.
     *
     * The values in the array represent the cost of stock each day. As we can buy and sell the stock only once, we need to find the best buy and sell prices that maximize profit (or minimized loss) over a given span of time.
     *
     * We need to maximize the profit from a single buy and sell. If we can’t make any profit, we’ll try to minimize the loss.
     *
     * There is a tricky linear solution to this problem that requires that we maintain current_buy_price (which is the smallest number seen so far), current_profit, and global_profit as we iterate through the entire array of stock prices. At each iteration, we compare the current_profit with the global_profit, and update the global_profit accordingly.
     * */

    Tuple<Integer, Integer> find(int[] nums) {
        if (nums.length < 2) {
            return null;
        }

        int currentBuy = nums[0];
        int globalSell = nums[1];
        int globalProfit = globalSell - currentBuy;
        int currentProfit = Integer.MIN_VALUE;

        for (int i = 1; i < nums.length; i++) {
            currentProfit = nums[i] - currentBuy;

            if (currentProfit > globalProfit) {
                globalProfit = currentProfit;
                globalSell = nums[i];
            }

            if (currentBuy > nums[i]) {
                currentBuy = nums[i];
            }
        }

        Tuple<Integer, Integer> res = new Tuple<>(globalSell - globalProfit, globalSell);
        return res;
    }

    private class Tuple<T, T1> {
        public T x;
        public T1 y;
        public Tuple(T x, T1 y) {
            this.x = x;
            this.y = y;
        }
    }

    class review {
        public int maxProfit(int[] prices) {
            int res = 0;
            for (int i = 1; i < prices.length; i++) {
                if (prices[i] > prices[i - 1]) {
                    res += prices[i] - prices[i - 1];
                }
            }
            return res;
        }
    }
}
