public class ExpressNumberInEnglish {
    /**
     * Given a non-negative integer value, express it in English words. The maximum integer value to convert is 1 billion. The function should return -1 if the input number is negative or greater than 1 billion.
     *
     * We can solve this problem by dividing the initial number into a specific set of digits and then converting those sets into English words. Initially, we split the number into sets of three. For example, if we split the amount 123456789 into sets of three digits, we get 123 456 789. Now, the values are separated into billion, million, and thousand parts in the number. At this stage, our English representation becomes 123 million 456 thousand 789.
     *
     * The three digits from each set can be further divided into individual sets to obtain the values at the hundreds, tens, and ones places. For example, 789 can be split into 7, 8, and 9. After splitting, 7 8 9 is converted to the English word representation "seven hundred eighty nine".
     *
     *
     * */

    public static String ones(int unitDigit) {
        switch (unitDigit) {
            case 1:
                return "one";
            case 2:
                return "two";
            case 3:
                return "Three";
            case 4:
                return "Four";
            case 5:
                return "Five";
            case 6:
                return "Six";
            case 7:
                return "Seven";
            case 8:
                return "Eight";
            case 9:
                return "Nine";
        }
        return "";
    }

    public static String teens(int teensDigit) {
        // Look-up table for teens values in English
        switch (teensDigit) {
            case 10:
                return "Ten";
            case 11:
                return "Eleven";
            case 12:
                return "Twelve";
            case 13:
                return "Thirteen";
            case 14:
                return "Fourteen";
            case 15:
                return "Fifteen";
            case 16:
                return "Sixteen";
            case 17:
                return "Seventeen";
            case 18:
                return "Eighteen";
            case 19:
                return "Nineteen";
        }
        return "";
    }

    public static String tens(int tensDigit) {
        // Look-up table for tens digits in English
        switch (tensDigit) {
            case 2:
                return "Twenty";
            case 3:
                return "Thirty";
            case 4:
                return "Forty";
            case 5:
                return "Fifty";
            case 6:
                return "Sixty";
            case 7:
                return "Seventy";
            case 8:
                return "Eighty";
            case 9:
                return "Ninety";
        }
        return "";
    }

    public static String two(int lowestTwoDigits) {
        // check the value of input number to decide
        // which helper function to use for conversion
        // to English language words
        if (lowestTwoDigits == 0)
            return "";
        else if (lowestTwoDigits < 10) {
            return ones(lowestTwoDigits);
        } else if (lowestTwoDigits < 20) {
            return teens(lowestTwoDigits);
        } else {
            // for values greater than 19, separate
            // out the tens (tenner) and units (rest) digits
            // and convert them separately to English words
            int tenner = lowestTwoDigits / 10;
            int rest = lowestTwoDigits - tenner * 10;
            if (rest != 0) {
                return tens(tenner) + " " + ones(rest);
            } else {
                return tens(tenner);
            }
        }
    }

    public static String three(int threeDigit) {
        int hundred = threeDigit / 100;

        int rest = threeDigit = hundred * 100;
        String res = "";

        if (hundred * rest != 0) {
            res = ones(hundred) + " Hundred " + two(rest);
        } else if (hundred == 0 && rest != 0) {
            res = two(rest);
        } else if (hundred == 0 && rest == 0) {
            res = ones(hundred) + " Hundred ";
        }
        return res;
    }

    String numberToWords(int input) {
        if (input < 0 || input > 1000000000) {
            return "-1";
        }
        if (input == 0) {
            return "Zero";
        }

        // break up the input into three-digit parts as mentioned above
        int billion = input / 1000000000;
        int million = (input - billion * 1000000000) / 1000000;
        int thousand = (input - billion * 1000000000 - million * 1000000) / 1000;
        int rest = input - billion * 1000000000 - million * 1000000 - thousand * 1000;

        String res = "";

        if (billion != 0) {
            res = three(billion) + "Billion";
        }
        if (million != 0) {
            if (!res.isEmpty()) {
                res += " ";
            }
            res += three(million) + "Million";
        }
        if (thousand != 0) {
            if (!res.isEmpty()) {
                res += " ";
            }
            res += three(thousand);
        }
        if (rest != 0) {
            if (!res.isEmpty()) {
                res += " ";
            }
            res += three(rest);
        }
        return res;
    }


}
