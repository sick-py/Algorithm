public class CAlculatePowerOfaNumber {
    /**
     * Given a double number, x, and an integer, n, write a function to calculate x raised to the power n.
     *A simple algorithm for this problem is to multiply x with itself by n times:
     * We can reduce the number of multiplications, in a classic divide and conquer approach, exploiting this property of exponents:
     *
     * In the dividing step, we divide n by 2 recursively until we reach the base case, n == 1.
     *
     * In the combining step, we get the result, res, of the sub-problem and compute the result of the current problem using the two rules below:
     *
     * If n is even at some level, the result is res * res (where res is the result of the sub-problem
     * If n is odd at some level, the result is x * res * res (where res is the result of sub-problem  x ^ (n/2)
     * */

    double powerRec(double x, int n) {
        if (n == 0) {
            return 1;
        }

        if (n == 1) {
            return x;
        }

        double temp = powerRec(x, n / 2);
        if (n % 2 == 0) {
            return temp * temp;
        }
        else {
            return temp * temp * x;
        }
    }

    double power(double x, int n) {
        boolean isNegative = false;
        if (n < 0) {
            isNegative = true;
            n *= -1;
        }

        double res = powerRec(x, n);
        if (isNegative) {
            return 1 / res;
        }
        return res;
    }
}
