import java.util.Stack;

public class BaseballGame {
    //682

    public static int calPoints(String[] operations) {
        Stack<Integer> stack = new Stack<>();
        for (String i : operations) {
            if (i.equals("+")) {
                int a = stack.pop();
                int b = stack.pop();
                stack.push(b);
                stack.push(a);
                stack.push(a + b);
            } else if (i.equals("D")) {
                stack.push(stack.peek() * 2);
            } else if (i.equals("C")) {
                stack.pop();
            } else {
                stack.push(Integer.parseInt(i));
            }
        }

        int res = 0;
        while (!stack.isEmpty()) {
            System.out.println(stack.peek());
            res += stack.pop();
        }
        return res;
    }
}
