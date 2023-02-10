import java.util.Stack;

public class PolishNotation {
    /**
     * Given an array of operators and operands representing an arithmetic expression in reverse Polish notation, evaluate the expression and determine its value.
     *
     * In this solution, we use a stack to store integer numbers. At each step, while iterating over the input array we check if the element is an operand or an operator:
     *
     * If the element is an operand, we push it into the stack.
     * If the element is an operator, then pop out the last two elements from the stack and arithmetically solve it while taking care of the order. After solving, we push the answer in the stack again.
     * We repeat these steps until we reach the end of the input array.
     * Eventually, we get the answer left in the stack.
     * */
    int eval(String[] tokens) {
        Stack<Integer> stack = new Stack<>();

        for (String token : tokens) {
            if (!"+-*/".contains(token)) {
                stack.push(Integer.valueOf(token));
                continue;
            } //else

            int num2 = stack.pop(), num1 = stack.pop();
            int res = 0;
            switch (token) {
                case "+":
                    res = num1 + num2;
                    break;
                case "-":
                    res = num1 - num2;
                    break;
                case "*":
                    res = num1 * num2;
                    break;
                case "/":
                    res = num1 / num2;
            }
            stack.push(res);
        }
        return stack.pop();
    }
}
