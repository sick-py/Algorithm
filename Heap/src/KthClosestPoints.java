import java.util.PriorityQueue;

public class KthClosestPoints {
    public int[][] kClosest(int[][] points, int K) {
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>((p1, p2) -> p2[0] * p2[0] + p2[1] * p2[1] - p1[0] * p1[0] - p1[1] * p1[1]);

        for (int[] p : points) {
            maxHeap.offer(p);
            if (maxHeap.size() > K) {
                maxHeap.poll();
            }
        }

        int[][] res = new int[K][2];
        while (K > 0) {
            res[--K] = maxHeap.poll();
        }
        return res;

    }
}
