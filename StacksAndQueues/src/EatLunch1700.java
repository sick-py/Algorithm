import java.util.LinkedList;
import java.util.Queue;

public class EatLunch1700 {
    public int countStudents(int[] students, int[] sandwiches) {
        Queue<Integer> queue = new LinkedList<>();
        int top = 0, count = 0;
        for (int s : students) {
            queue.add(s);
        }

        while (!queue.isEmpty() && count != queue.size()) {
            if (queue.peek() == sandwiches[top]) {
                count = 0;
                top++;
                queue.poll();
            } else {
                queue.add(queue.poll());
                count++;
            }
        }
        return queue.size();
    }
}
