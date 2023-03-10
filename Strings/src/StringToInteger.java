public class StringToInteger {
    /**
     * Read in and ignore any leading whitespace.
     * Check if the next character (if not already at the end of the string) is '-' or '+'. Read this character in if it is either. This determines if the final result is negative or positive respectively. Assume the result is positive if neither is present.
     * Read in next the characters until the next non-digit character or the end of the input is reached. The rest of the string is ignored.
     * Convert these digits into an integer (i.e. "123" -> 123, "0032" -> 32). If no digits were read, then the integer is 0. Change the sign as necessary (from step 2).
     * If the integer is out of the 32-bit signed integer range [-231, 231 - 1], then clamp the integer so that it remains in the range. Specifically, integers less than -231 should be clamped to -231, and integers greater than 231 - 1 should be clamped to 231 - 1.
     * Return the integer as the final result.
     * */
    public static int myAtoi(String s) {
        int index = 0;
        while (index < s.length() && s.charAt(index) == ' ') {
            index++;
        }
        int sign = 1;
        if (index < s.length() && s.charAt(index) == '-') {
            sign = -1;
            index++;
        } else if (index < s.length() && s.charAt(index) == '+') {
            index++;
        }

        double res = 0; //because overflow of the long and cause a minus
        while (index < s.length() && Character.isDigit(s.charAt(index))) {
            //System.out.printf("index : %d, char : %c res: %d \n", index, s.charAt(index), s.charAt(index) - '0');
            res = res * 10 + s.charAt(index) - '0';
            index++;
        }

        System.out.println(res);
        res = res * sign;
        System.out.println(res);
        if (res < Integer.MIN_VALUE) {
            System.out.println("here");
            return Integer.MIN_VALUE;
        } else if (res > Integer.MAX_VALUE) {

            return Integer.MAX_VALUE;
        } else {
            return (int)res;
        }
    }

    public static void main(String[] args) {
        String case1 = new String("9223372036854775808");
        int test = myAtoi(case1);
        System.out.println(test);
    }
}
