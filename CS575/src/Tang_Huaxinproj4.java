import java.util.*;

public class Tang_Huaxinproj4 {
    static int[][] createGraph() {
        Random rand = new Random();
        int n = rand.nextInt(6) + 5;
        System.out.printf("n = %d\n", n);
        int[][] graph = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int weight = rand.nextInt(10) + 1;
                graph[i][j] = weight;
                graph[j][i] = weight;
            }
        }

        System.out.printf("print the random undirected graph: \n");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(graph[i][j] + " ");
            }
            System.out.println();
        }
        return graph;
    }

    static void Prim(int[][] graph) {
        int n = graph.length;
        //Initialize
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b)->a[1] - b[1]);
        Set<Integer> visited = new HashSet<>();
        //start from node 0
        visited.add(0);
        int sum = 0;
        for (int j = 1; j < n; j++) {
            pq.offer(new int[] {j, graph[0][j]});
        }

        System.out.println("MST edges of Prim are:");
        while (!pq.isEmpty()) {
            int[] edg = pq.poll();
            int vertex = edg[0], weight = edg[1];
            if (visited.contains(vertex)) continue;
            visited.add(vertex);
            sum += weight;
            System.out.printf("[%d, %d] with weight %d, now total weight is %d\n",getParent(graph, visited, vertex) + 1, vertex + 1, weight, sum);
            //maintain the priority queue
            for (int j = 0; j < n; j++) {
                if (!visited.contains(j)) {
                    pq.offer(new int[] {j, graph[vertex][j]});
                }
            }
        }

    }

    //get previous node
    private static int getParent(int[][] A, Set<Integer> visited, int v) {
        int minWeight = Integer.MAX_VALUE, parent = -1;
        for (int i : visited) {
            if (A[i][v] > 0 && A[i][v] < minWeight) {
                minWeight = A[i][v];
                parent = i;
            }
        }
        return parent;
    }

    static class MySet{
        int[] parents;
        int[] heights;

        public MySet(int n) {
            parents = new int[n];
            heights = new int[n];
            for (int i = 0; i < n; i++) {
                parents[i] = i;
                heights[i] = 0;
            }
        }

        int find(int x) {
            //find root
            int root = x;
            while (root != parents[root]) {
                root = parents[root];
            }

            int node = x;
            while (node != root) {
                int parent = parents[node];
                parents[node] = root;
                node = parent;
            }
            return root;
        }

        void union(int x, int y) {
            x = find(x);
            y = find(y);
            if (x == y) {
                return;
            }
            if (heights[x] == heights[x]) {
                parents[y] = x;
                heights[x]++;
            } else if (heights[x] > heights[y]) {
                parents[y] = x;
            } else {
                parents[x] = y;
            }
        }
    }

    static void Kruskal(int[][] graph) {
        int n = graph.length, count = 0, sum = 0;
        //initialize the tree set
        List<Edge> edges = new ArrayList<>();
        //add all the edges
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (graph[i][j] != 0) {
                    edges.add(new Edge(i, j, graph[i][j]));
                }
            }
        }

        MySet set = new MySet(n);

        Collections.sort(edges);
        System.out.println("MST edges of Kruskal are:");
        for (Edge edge : edges) {
            if (count == n - 1) {
                break;
            }
            if (set.find(edge.u) != set.find(edge.v)) {
                sum += edge.weight;
                System.out.printf("[%d, %d] with weight %d, now total weight is %d\n",edge.u + 1, edge.v + 1, edge.weight, sum);
                set.union(edge.u, edge.v);
                count++;
            }
        }
    }

    static class Edge implements Comparable<Edge> {
        int u, v, weight;

        public Edge(int i, int j, int w) {
            u = i;
            v = j;
            weight = w;
        }

        @Override
        public int compareTo(Edge o) {
            return this.weight - o.weight;
        }
    }
    public static void main(String[] args) {
        int choice = 0;
        int[][] test = createGraph();
        Scanner scanner = new Scanner(System.in);
        int[][] test2 = {{0, 10, 1, 4, 7, 1},
                {10, 0, 7, 4, 6, 3},
                {1, 7, 0, 6, 7, 5},
                {4, 4, 6, 0, 8, 5},
                {7, 6, 7, 8, 0, 7},
                {1, 3, 5, 5, 7, 0}};


        while (choice != 3) {
            System.out.printf("Please enter 1 for Prim, 2 for Kruskal, 3  for quit\n");
            choice = scanner.nextInt();
            if (choice == 1) {
                Prim(test);
            } else if (choice == 2) {
                Kruskal(test);
            } else if (choice == 3) {
                System.out.printf("Have a good day!\n");
            }
            else {
                System.out.printf("Error!! Please choose between 1 or 2.\n");
            }
        }

    }
}
