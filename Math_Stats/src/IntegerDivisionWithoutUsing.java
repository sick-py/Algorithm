public class IntegerDivisionWithoutUsing {
    /**
     * return x / y without using / or * operators
     * We can count the number of times that the divisor can be added to approach the dividend. The above solution is really inefficient. it would take ~50000 iterations to get 100000 / 2
     *
     * An efficient approach is to use the bit shift operators << and >> to multiply and divide. Here is how it works:
     *
     * Num * 2 = Num << 1 (shift left by 1)
     * Num / 2 = Num >> 1 (shift right by 1)
     *
     * Instead of adding the divisor to approach the dividend, we keep shifting the dividend and quotient left by 2 (which multiplies by 2) until we reach the divisor.
     *
     * quotient = 1
     * while divisor is less than dividend
     *     divisor = (left shift divisor)
     *     quotient = (left shift quotient)
     * handle overflow recursively.
     * */
    int divide(int dividend, int divisor) {
        if (divisor == 0) {
            return -1;
        }

        if (dividend < divisor) return 0;

        else if (dividend == divisor) return 1;

        else if (divisor == 1) return dividend;

        int q = 1;
        int val = divisor;

        while (val < dividend) {
            val <<= 1;
            // functionally equivalent to, but faster than 'val = val + val;
            q <<= 1;
        }

        // We have increased val so that it's higher than the dividend,
        // which means that we need to move back one step and handle the overflow
        if (val > dividend) {
            val >>= 1;
            q >>= 1;

            return q + divide(dividend - val, divisor);
        }

        return q;
    }
}
