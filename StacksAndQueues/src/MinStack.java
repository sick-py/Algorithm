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

    class review {
        class MinStack0 {
            Stack<Integer> stack, minStack;
            public MinStack0() {
                stack = new Stack<>();
                minStack = new Stack<>();
            }

            public void push(int val) {
                stack.push(val);
                val = Math.min(val, stackMin.isEmpty() ? val : stackMin.peek());
                minStack.push(val);
            }

            public void pop() {
                stack.pop();
                minStack.pop();
            }

            public int top() {
                return stack.peek();
            }

            public int getMin() {
                return minStack.peek();
            }
        }
    }
}
