import java.util.PriorityQueue;

public class LastStone {
    public int lastStoneWeight(int[] stones) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b)->(b - a));
        for (int i : stones) {
            maxHeap.offer(i);
        }

        while (maxHeap.size() > 1) {
            int a = maxHeap.poll();
            int b = maxHeap.poll();
            int c = Math.abs(a - b);
            maxHeap.offer(c);
        }

        if (maxHeap.isEmpty()) {
            return 0;
        }
        return maxHeap.poll();
    }
}
