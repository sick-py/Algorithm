package Main;

import java.util.Arrays;


public class main2BaseCase {
    /**
     * The minimum sum of the descending path", the input is a n * n two-dimensional array matrix , please calculate the minimum path sum from the first row to the last row.
     * Every time you fall, you can move one grid in three directions: down, down to the left, and down to the right. That is matrix[i][j], three positions can be dropped from to matrix[i+1][j]or matrix[i+1][j-1]or .matrix[i+1][j+1]
     *
     * let's use this question to talk about how to determine the return value of the base case, the initial value of the memo, and the return value of the index out of bounds situation
     * In the example given in this article, the data range of the test case can determine "what is a special value", thus helping us convert ideas into code.
     *
     * after statement and choice,
     *
     * then we can define a dp function:
     * the shortest sum of path Falling from the first line ( matrix[0][..]) to matrix[i][j] is dp(matrix, i, j).
     * */


    int minFall(int[][] matrix) {
        int n = matrix.length, res = Integer.MAX_VALUE;
        memo = new int[n][n];
        /** Let me talk about why memothe is 66666, which is determined by the data range given by the title .
         * What is the function of memo array ?
         * It is to prevent double calculation, store dp(matrix, i, j)the calculation result memo[i][j]of , and return it directly when double calculation occurs.
         * So, we have to know memo[i][j]whether to store the calculation result, right? If the result is saved, it will return directly; if it is not saved, it will go to recursive calculation.
         * Therefore, memothe initial value of must be a special value, which is different from the legal answer.
         *
         * Let's look back at the data range given by the title:
         * That is, the legal outcome of this problem would fall [-10000, 10000]in .
         * Therefore, mem oour initial value should avoid the interval [-10000, 10000], in other words, memoas long as the initial value (-inf, -10001] U [10001, +inf)of .
         * */
        for (int i = 0; i < n; i++) {
            Arrays.fill(memo[i], 666666);
        }
        for (int j = 0; j < n; j++) {
            res = Math.min(res, dp1(matrix,n - 1, j));
        }
        return res;
    }


    /** Violation
     *
     * */
    int dp0(int[][] matrix, int i, int j) {
        //check the boundary
        if (i < 0 || j < 0 || i >= matrix.length || j >= matrix[0].length) {
            /** Finally, let’s talk about how to determine the return value for an illegal index, which needs to be determined according to the logic of our state transition equation .
             * First, Obviously, i - 1, j - 1, j + 1these operations may cause the index to go out of bounds. For the dpfunction , it should return a value that cannot be obtained.
             *
             * then from the core function min to take the smallest from 3 parameters, Because we call the min function , the final value returned is the minimum value, so for illegal indexes, as long as the dp function returns a maximum value that will never be obtained.
             *
             * As I just said, the legal answer range is [-10000, 10000], so as long as our return value is greater than 10000, it is equivalent to a maximum value that will never be obtained.
             * */
            return 999999; //return a special ungetable value which won't influence the min function
        }

        /**base case
         * i == 0return value is matrix[0][j], which is determined according to the definition of the dp function .
         * Falling from the first line ( matrix[0][..]), matrix[i][j]the dp(matrix, i, j) is .
         * By this definition, we are falling matrix[0][j]from . Then if the destination we want to land is i == 0, the required path and of course is matrix[0][j]chanting .
          */
        if (i == 0) {
            return matrix[i][j];
        }

        return matrix[i][j] + min(dp0(matrix, i - 1, j), dp0(matrix, i - 1, j - 1), dp0(matrix, i - 1, j + 1));
    }

    int min(int a, int b, int c) {
        return Math.min(a, Math.min(b, c));
    }

    /** with memo
     *
     * */
    int[][] memo;

    private int dp1(int[][] matrix, int i, int j) {
        if (i < 0 || j < 0 || i >= matrix.length || j >= matrix[0].length) {
            return 999999;
        }

        if (i == 0) {
            return matrix[i][j];
        }

        if (memo[i][j] != 666666) {
            return memo[i][j];
        }

        memo[i][j] = matrix[i][j] + min(dp1(matrix, i - 1, j - 1), dp1(matrix, i - 1, j),
                dp1(matrix, i - 1, j + 1));
        return memo[i][j];
    }
}
