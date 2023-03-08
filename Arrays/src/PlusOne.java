public class PlusOne {
    public int[] plusOne(int[] digits) {
        int n = digits.length;
        boolean notall9Flag = false;
        for (int i = 0; i < digits.length; i++) {
            if (digits[i] != 9) {
                notall9Flag = true;
                break;
            }
        }

        if (!notall9Flag) {
            int[] res = new int[digits.length + 1];
            res[0] = 1;
            return res;
        }

        int[] res;
        if (digits[n - 1] != 9) {
            digits[n - 1]++;
            return digits;
        }

        int carry = 1;
        int i = n - 1;
        while (carry != 0 & i >= 0) {
            int digit = digits[i] + carry;
            if (digit > 9) {
                digits[i] = digit - 10;
                carry = digit / 10;
            }else {
                digits[i] = digit;
                break;
            }
            i--;
        }
        return digits;
    }

    /**
     * There are different ways to solve this problem, but one common approach is to iterate over the array of digits from right to left and add one to the current digit if it is less than 9. If it is equal to 9, then set it to zero and carry over one to the next digit. If all digits are 9, then create a new array with an extra length and set the first element to 1.
     * */
    public int[] plusOne0(int[] digits) {
        int n = digits.length;
        for(int i=n-1; i>=0; i--) {
            if(digits[i] < 9) {
                digits[i]++;
                return digits;
            }

            digits[i] = 0;
        }

        int[] newNumber = new int [n+1];
        newNumber[0] = 1;

        return newNumber;
    }
}
