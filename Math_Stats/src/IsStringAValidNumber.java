public class IsStringAValidNumber {
    /**
     * Given an input string, determine if a given string makes a valid number or not.
     *
     * We use the state machine below to check if a number is valid or not.
     * The initial state is Start. We process each character to identify the next state.
     * The input string is not a valid number if we reach an UNKNOWN state at any point or if it ends in a DECIMAL.
     * We do not look at the sign at the start of a number in the state machine.
     * If there is a sign at the start of the input string, we start processing the string for the state machine after that sign character.
     * */

    enum STATE {
        START, INTEGER, DECIMAL, UNKNOWN, AFTERDECIMAL
    };

    // Implements the state machine that decides the next state
    // based on the current state and the current character of the string
    static STATE getNext(STATE current, char ch) {
        if (current == STATE.START || current == STATE.INTEGER) {
            // if the current character is the decimal point, return current state as Decimal
            if (ch == '.') {
                return STATE.DECIMAL;
            } else if (ch >= '0' && ch <= '9') {
                return STATE.INTEGER;
            } else {
                return STATE.UNKNOWN;
            }
        }

        if (current == STATE.DECIMAL) {
            if (ch >= '0' && ch <= '9') {
                return STATE.AFTERDECIMAL;
            } else {
                return STATE.UNKNOWN;
            }
        }

        if (current == STATE.AFTERDECIMAL) {
            if (ch >= '0' && ch <= '9') {
                return STATE.AFTERDECIMAL;
            } else {
                return STATE.UNKNOWN;
            }
        }

        return STATE.UNKNOWN;
    }

    static boolean isNumber(String s) {
        if (s.isEmpty()){
            return true;
        }
        int i = 0;
        if (s.charAt(i) == '+' || s.charAt(i) == '-') {
            i++;
        }
        STATE current = STATE.START;

        for (; i < s.length(); i++) {
            current = getNext(current, s.charAt(i));
            if (current == STATE.UNKNOWN) {
                return false;
            }
        }
        if (current == STATE.DECIMAL) return false;

        return true;
    }
}
