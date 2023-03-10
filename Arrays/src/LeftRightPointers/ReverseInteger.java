package LeftRightPointers;

public class ReverseInteger {
    public int reverse(int x) {
        int sign = x > 0 ? 1 : -1;
        x = Math.abs(x);
        long res = 0;
        while (x > 0) {
            int lastDigit = x % 10;
            res = res * 10 + lastDigit;
            x /= 10;
        }
        if (res > Integer.MAX_VALUE) {
            return 0;
        }
        return (int)res * sign;
    }
}
