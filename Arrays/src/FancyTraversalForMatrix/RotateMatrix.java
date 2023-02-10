package FancyTraversalForMatrix;

import static jdk.nashorn.internal.objects.NativeArray.reverse;

public class RotateMatrix {
    /**
     * How to rotate a 2D matrix "in place", But in fact, this question cannot take the usual path . Before talking, it's similar to reverse the order of all words in place .
     *
     * We can first mirror the matrix according to the diagonal from the upper left to the lower right, Then invert each row of the matrix
     * */
    void rotate(int[][] matrix) {
        int n = matrix.length;
        //mirror
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }

        for (int[] row : matrix) {
            reverse(row);
        }
    }
}
