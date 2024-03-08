public class Floyd {
    static int[][] graph = {{Integer.MAX_VALUE, 4, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 10, Integer.MAX_VALUE}, {3, Integer.MAX_VALUE, Integer.MAX_VALUE, 18, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE}, {Integer.MAX_VALUE, 6, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE},
            {Integer.MAX_VALUE, 5, 15, Integer.MAX_VALUE, 2, 19, 5}, {Integer.MAX_VALUE, Integer.MAX_VALUE, 12, 1, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE}, {Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 10},
            {Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 8, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE}};

    public static void main(String[] args) {
        System.out.println(graph.length);
    }
}
