import java.util.Stack;

public class QueueUseStack {
    /**
     * The first solution is implemented in a way that makes the enqueue() operation faster. We maintain two stacks named stack1 and stack2. Let’s see the pseudocode for the enqueue() and dequeue() operations:
     *
     * enqueue():
     *     always push onto stack1
     *
     * dequeue():
     *     if queue size is 0,
     *         return -1
     *
     *     if stack2 has element(s),
     *         pop the topmost and return.
     *
     *     otherwise, if stack1 is non-empty,
     *         pop all elements from stack1 and push them onto stack2.
     *
     *     at the end pop the topmost element from stack2 and return.
     * */
    Stack<Integer> stack1 = new Stack<>();
    Stack<Integer> stack2 = new Stack<>();

    void enqueue(int data) {
        stack1.push(data);
    }

    boolean isEmpty() {
        return stack1.size() + stack2.size() == 0;
    }

    int dequeue() {
        if (isEmpty()) {
            return -1;
        }

        if (stack2.isEmpty()) {
            while (!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
        }

        return stack2.pop();
    }

    /**
     * The second solution will instead make the dequeue() operation faster. Similar to the first solution, we maintain two stacks. Let’s look at the pseudocode enqueue() and dequeue() operations:
     *
     * enqueue()
     *     pop each element from stack1 and push it to stack2.
     *     push the element being enqueued to stack1.
     *     pop each element back from stack2 and push it to stack1.
     *
     * dequeue()
     *     if queue size is 0,
     *         return -1
     *
     *     pop from stack1 and return.
     * */
}
