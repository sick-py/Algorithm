import java.util.Stack;

public class MinStack {
    /**
     * record every node with the minimum number in that position
     * */
    Stack<Integer> stack, stackMin;
    public MinStack() {
        stack = new Stack<>();
        stackMin = new Stack<>();
    }

    public void push(int val) {
        stack.push(val);
        val = Math.min(val, stackMin.isEmpty() ? val : stackMin.peek());
        stackMin.push(val);
    }

    public void pop() {
        stack.pop();
        stackMin.pop();
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return stackMin.peek();
    }
}
