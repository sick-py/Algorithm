import java.util.HashMap;

public class RomanNumbersToIntegers {
    /**
     * Given a Roman numeral, our task is to convert it to an integer.
     *
     * Roman numerals are represented by seven different symbols:
     *
     * “I”, “V”, “X”, “L”, “C”, “D”, and “M”.
     * Roman numerals are traditionally written from left to right, from largest to smallest.
     *
     * However, the numeral for four is “IV” instead of “IIII”. We deduct the one because it is less than five, resulting in four.
     * The number nine, written as “IX,” follows the same pattern.
     * This subtraction rule occurs in six different situations:
     *
     * “I” can be placed before “V” (5) and “X” (10) to make four and nine, respectively.
     * “X” can be placed before “L” (50) and “C” (100) to make 40 and 90, respectively.
     * “C” can be placed before “D” (500) and “M” (1000) to make 400 and 900, respectively.
     *
     * To solve this problem, we need to take the following steps:
     *
     * First, initialize a variable, total, to store the values of the Roman numerals.
     * Next, iterate over the string input s.
     * If at least two symbols remain and the value of the current character is less than the value of the next character, we add the difference of the two to total and increment the marker two characters ahead.
     * Otherwise, we add the value of the current character to the total value.
     * We iterate the loop until we reach the string’s end and return the total.
     * */

    public static HashMap<Character, Integer> values = new HashMap<Character, Integer>() {
        {
            put('I', 1);
            put('V', 5);
            put('X', 10);
            put('L', 50);
            put('C', 100);
            put('D', 500);
            put('M', 1000);
        }
    };

    int romanToInt(String input) {
        Integer res = 0;
        int i = 0;
        while (i < input.length()) {
            if (i + 1 < input.length() && values.get(input.charAt(i)) < values.get(input.charAt(i + 1))) {
                res += values.get(input.charAt(i + 1)) - values.get(input.charAt(i));
                i += 2;
            } else {
                res += values.get(input.charAt(i));
                i++;
            }
        }
        return res;
    }
}
