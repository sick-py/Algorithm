public class CalculateSquareRoot {
    /**
     * Given a number of type double, calculate its square root. For this problem, the given number is always a non-negative double number.
     *
     * We pass both low and high values to each recursive function call, where mid and square is calculated.
     *
     * If the square of mid is equal to n then it is the base case, and we return mid as the square root of n.
     *
     * If the square of mid is smaller than n then in the next recursive call, low is changed to mid and high remains unchanged.
     *
     * If the square of mid is larger than n then in the next recursive call, high is changed to mid and low remains unchanged.
     * */

    private static final double EPSILON = 0.00001;

    private static double squareRootRec(double num, double low, double high) {
        double mid = (low + high) / 2;
        double sqr = mid * mid;

        double diff = Math.abs(sqr - num);

        // we can't do a == b for doubles because
        // of rounding errors, so we use error threshold
        // EPSILON. Two doubles a and b are equal if
        // abs(a-b) <= EPSILON
        if (diff <= EPSILON) {
            return mid;
        }

        if (sqr < num) {
            return squareRootRec(num, mid, high);
        }

        return squareRootRec(num, low, mid);
    }

    public static double squareRoot(double num) {
        if (num < 0) {
            return -1;
        }
        else {
            return squareRootRec(num, 0, 1 + num / 2);
        }
    }

    double rec(double num, double low, double high) {
        double mid = (high - low) / 2;
        double square = mid * mid;
        double diff = Math.abs(square - num);

        if (diff <= EPSILON) {
            return mid;
        }

        else if (square > num) {
            return rec(num, low, mid);
        }
        else {
            return rec(num, mid, high);
        }
    }
    double a(double num) {
        if (num < 0) {
            return -1;
        } else {
            return rec(num, 0, 1 + num / 2);
        }
    }
}
