import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;

class MyStack {
    //225
    Queue<Integer> q0, q1;

    public MyStack() {
        q0 = new ArrayDeque<>();
        q1 = new ArrayDeque<>();
    }

    public void push(int x) {
        q0.add(x);
    }

    public int pop() {
        if (q0.isEmpty()) {
            return -1;
        }
        while (q0.size() != 1) {
            q1.add(q0.poll());
        }

        int val = q0.poll();
        Queue<Integer> temp = q0;
        q0 = q1;
        q1 = temp;
        return val;

    }

    public int top() {
        int val = pop();
        push(val);
        return val;
    }

    public boolean empty() {
        return q0.isEmpty();
    }
}