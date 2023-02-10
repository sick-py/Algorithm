package FancyTraversalForMatrix;

import java.util.LinkedList;
import java.util.List;

public class MatrixSpiral {
    /**
     * The core idea of solving the problem is to traverse the array in the order of right, bottom, left, and top, and use four variables to delineate the boundaries of untraversed elements :
     * As the spiral traverses, the corresponding bounds shrink until the spiral traverses the entire array:
     * */

    List<Integer> spiralOrder(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        int upperBound = 0, lowerBound = m - 1;
        int leftBound = 0, rightBound = n - 1;
        List<Integer> res = new LinkedList<>();

        while (res.size() < m * n) {
            //at the top from left to right
            if (upperBound <= lowerBound) {
                for (int j = leftBound; j <= rightBound; j++) {
                    res.add(matrix[upperBound][j]);
                }
                upperBound++;
            }

            if (leftBound <= rightBound) {
                for (int i = upperBound; i <= lowerBound; i++) {
                    res.add(matrix[i][rightBound]);
                    rightBound--;
                }
            }

            if (upperBound <= lowerBound) {
                for (int j = rightBound; j >= leftBound; j--) {
                    res.add(matrix[lowerBound][j]);
                }
                lowerBound--;
            }

            if (leftBound <= rightBound) {
                for (int i = lowerBound; i >= upperBound; i--) {
                    res.add(matrix[i][leftBound]);
                }
                leftBound++;
            }
        }
        return res;
    }
}
