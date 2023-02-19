import java.util.PriorityQueue;

public class KthLargestInStream {
    private PriorityQueue<Integer>  heap;
    private int k;

    public KthLargestInStream(int k, int[] nums) {
        this.k = k;
        heap = new PriorityQueue<>();

        for (int i : nums) {
            heap.offer(i);
        }

        while (heap.size() > k) {
            heap.poll();
        }
    }

    public int add(int val) {
        heap.offer(val);
        while (heap.size() > k) {
            heap.poll();
        }
        return heap.peek(); //you have to use peek here to maintain the k element
    }
}
