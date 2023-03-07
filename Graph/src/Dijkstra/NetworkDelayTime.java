package Dijkstra;

import java.util.*;

public class NetworkDelayTime {
    /**
     * equal to what is the longest shortest path distance from the node kto
     * */
    int networkDelayTime(int[][] times, int n, int k) {
        List<int[]>[] graph = new LinkedList[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new LinkedList<>();
        }

        //make the graph
        for (int[] edge : times) {
            int from = edge[0];
            int to = edge[1];
            int weight = edge[2];
            graph[from].add(new int[]{to, weight});
        }
        int[] distTo = dijkstra(k, graph);

        int res = 0;
        for (int i = 1; i < distTo.length; i++) {
            if (distTo[i] == Integer.MAX_VALUE) {
                return -1;
            }
            res = Math.max(res, distTo[i]);
        }
        return res;
    }

    class State {
        int id;
        int disFromStart;
        State(int a, int b) {
            id = a;
            disFromStart = b;
        }
    }
    private int[] dijkstra(int start, List<int[]>[] graph) {
        int[] disTo = new int[graph.length];
        Arrays.fill(disTo, Integer.MAX_VALUE);
        disTo[0] = 0;

        Queue<State> pq = new PriorityQueue<>((a, b)->(a.disFromStart - b.disFromStart));
        pq.offer(new State(start, 0));

        while (!pq.isEmpty()) {
            State curNode = pq.poll();
            int curId = curNode.id;
            int curDistFromStart = curNode.disFromStart;
            if (curDistFromStart > disTo[curId]) {
                continue;
            }

            for (int[] neighbor : graph[curId]) {
                int nextId = neighbor[0];
                int distToNextNode = disTo[curId] + neighbor[1];
                if (disTo[curId] > distToNextNode) {
                    disTo[curId] = distToNextNode;
                    pq.offer(new State(curId, distToNextNode));
                }
            }
        }
        return disTo;
    }
}
