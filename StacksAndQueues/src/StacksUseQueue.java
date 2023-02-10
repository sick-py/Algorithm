import java.util.ArrayDeque;
import java.util.Queue;

public class StacksUseQueue {
    /**
     * Given a Queue class with common operations such as enqueue(), dequeue(), and size(), implement a Stack class with push(), pop(), and isEmpty() operations. The isEmpty() function should return true if the stack is empty and false otherwise.
     *
     * The first solution will make the push() operation faster. We use two queues named queue1 and queue2.
     *
     * Let’s see what our push() and pop() operations look like:
     *
     * push():
     *     always enqueue on queue1
     *
     * pop():
     *     if stack size is 0,
     *         return -1
     *
     *     if queue1 has element(s),
     *         dequeue all elements from queue1 and
     *         enqueue on queue2 except the last element.
     *
     *     The last element found above will be returned
     *     after swapping queue1 and queue2 references.
     * */
    Queue<Integer> q1 = new ArrayDeque<>();
    Queue<Integer> q2 = new ArrayDeque<>();

    void push(int data) {
        q1.add(data);
    }

    void swapQ() {
        Queue<Integer> tmp = q1;
        q1 = q2;
        q2 = tmp;
    }

    boolean isEmpty() {
        return q1.size() + q2.size() == 0;
    }

    Integer pop() {
        if (isEmpty()) {
            return -1;
        }

        while (q1.size() > 1) {
            q2.add(q1.remove());
        }
        int val = q1.remove();
        swapQ();
        return val;
    }

    /**
     * The second solution will make the pop() operation faster. Similar to the first solution, we maintain two queues. Let’s see what our push and pop operations look like:
     *
     * push():
     *     if queue1 is empty,
     *         then enqueue on queue1.
     *
     *     otherwise,
     *         enqueue on queue2 and dequeue all elements
     *         from queue1 and push them on queue2.
     *         then swap the queue references.
     *
     * pop():
     *     if stack size is 0,
     *         return -1.
     *     otherwise,
     *         dequeue from queue1 and return.
     * */
    Queue<Integer> queue1 = new ArrayDeque<Integer>();
    Queue<Integer> queue2 = new ArrayDeque<Integer>();

    void push1(int data) {
        if (queue1.isEmpty()) {
            queue1.add(data);
        } else {
            queue2.add(data);
            while (!queue1.isEmpty()) {
                // Dequeue values from queue1 and enqueue them
                // onto queue2
                queue2.add(queue1.remove());
            }
            // queue2 now has all the elements in the correct order
            swapQueues();
        }
    }

    // Uses the default size() method of the queue class
    // to check if the stack is empty
    boolean isEmpty1() {
        return queue1.size() + queue2.size() == 0;
    }

    // Helper function that swaps our queues using a temp
    // queue pointer
    void swapQueues() {
        Queue<Integer> queue3 = queue1;
        queue1 = queue2;
        queue2 = queue3;
    }

    int pop1() {
        if (isEmpty()) {
            return -1;
        }
        return queue1.remove();
    }
}
