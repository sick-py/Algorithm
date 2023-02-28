package PrefixAndDiff;

public class PreSum {
    /**
     * Let's look at a sample question first, and click on question 303 " Regions and Retrieval - Arrays are immutable", which lets you calculate the sum of elements in an array range, which is a standard prefix-sum problem:
     *
     * The optimal solution to this problem is to use preSums and tricks to reduce the time complexity of the sumRangefunction to O(1). To put sumRangeit use for loops in . What’s wrong?
     *
     * The core idea is that we create a new preSumarray and preSum[i]record nums[0..i-1]the accumulated sum.
     * Looking at this pre Sumarray , if I want to find [1, 4]the sum of all elements in the index interval, I can preSum[5] - preSum[1]get .
     *
     * In this way, the sumRangefunction only needs to perform a subtraction operation, avoiding each for loop call, and the worst time complexity is constant O(1).
     * */
    private int[] preSum;

    public PreSum(int[] nums) {
        preSum = new int[nums.length + 1]; //preSum[0] = 0, it's easy to accumlate
        for (int i = 1; i < preSum.length; i++) {
            preSum[i] = preSum[i - 1] + nums[i - 1];
        }
    }

    public int sumRange(int left, int right) {
        return preSum[right + 1] - preSum[left];
    }

    /**
     * 2D matrix
     * "In fact, it is similar to the previous question. The previous question asked you to calculate the sum of the elements of the sub-array. This question allows you to calculate the sum of the elements of the sub-matrix in the two-dimensional matrix:
     *
     * Note that the element sum of any sub-matrix can be transformed into the operation of the element sum of several large matrices around it:
     * preSum[x2+1][y2+1] - preSum[x1][y2+1] - preSum[x2+1][y1] + preSum[x1][y1];
     *
     * And these four large matrices have a common feature, that is, the upper left corner is the (0, 0)origin .
     *
     * Then a better way to do this question is very similar to the prefix sum in a one-dimensional array. We can maintain a two-dimensional preSumarray to record the sum of the elements of the matrix with the origin as the vertex, and we can use several additions and subtractions The operation finds the element-wise sum of any submatrix:
     * */
    class NumMatrix0 {
        private int[][] preSumM;

        public NumMatrix0(int[][] matrix) {
            int m = matrix.length, n = matrix[0].length;
            if (m == 0 || n == 0) return;
            preSumM = new int[m + 1][n + 1];
            for (int i = 1; i < m + 1; i++) {
                for (int j = 1; j < n + 1; j++) {
                    preSumM[i][j] = preSumM[i - 1][j] + preSumM[i][j - 1] + matrix[i - 1][j - 1] - preSumM[i - 1][j - 1];
                }
            }
        }

        public int sumRegion(int x1, int y1, int x2, int y2) {
            return preSumM[x2+1][y2+1] - preSumM[x1][y2+1] - preSumM[x2+1][y1] + preSumM[x1][y1];
        }
    }

    class NumArray { //303
        int[] preSum;
        public NumArray(int[] nums) {
            preSum = new int[nums.length + 1];
            preSum[0] = 0;
            for (int i = 1; i < preSum.length; i++) {
                preSum[i] = preSum[i - 1] + nums[i - 1];
            }
        }

        public int sumRange(int left, int right) {
            return preSum[right + 1] - preSum[left];
        }
    }

    class NumMatrix { //304
        int[][] preSum;
        public NumMatrix(int[][] matrix) {
            int m = matrix.length, n = matrix[0].length;
            preSum = new int[m + 1][n + 1];
            for (int i = 1; i < m + 1; i++) {
                for (int j = 1; j < n + 1; j++) {
                    preSum[i][j] = preSum[i - 1][j] + preSum[i][j - 1] - preSum[i - 1][j - 1] + matrix[i - 1][j - 1];
                    System.out.printf(" %d ", preSum[i][j]);
                }
                System.out.println(" ");
            }
        }

        public int sumRegion(int row1, int col1, int row2, int col2) {
            return preSum[row2 + 1][col2 + 1] + preSum[row1][col1] - preSum[row1][col2 + 1] - preSum[row2 + 1][col1];
        }


    }


}
