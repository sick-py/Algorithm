import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class dynpro {
    private static class Item {
        public int value;
        public int weight;
        public double valuePerWeight;

        public int id;

        public Item(int value, int weight, int id) {
            this.id = id;
            this.value = value;
            this.weight = weight;
            this.valuePerWeight = (double) value / weight;
        }
    }
    public static int dynamic(Item[] items, int W) {
        int n = items.length;
        int[][] table = new int[n+1][W+1];

        int[][] computed = new int[n + 1][W + 1];

        computed[n][W] = 1;
        for (int i = n; i >= 1; i--) {
            for (int j = W; j >= 0; j--) {
                if (computed[i][j] != 1) {
                    continue;
                }
                if (items[i - 1].weight > j) {
                    computed[i - 1][j] = 1;
                } else {
                    computed[i - 1][j] = 1;
                    computed[i - 1][j - items[i - 1].weight] = 1;
                }
            }
        }





        // Compute B table using dynamic programming approach
        for (int i = 1; i <= n; i++) {
            for (int w = 1; w <= W; w++) {
                if (computed[i][w] != 1) {
                    continue;
                }
                if (items[i-1].weight > w) {
                    table[i][w] = table[i-1][w];
                } else {
                    table[i][w] = Math.max(table[i-1][w], table[i-1][w-items[i-1].weight] + items[i-1].value);
                }
            }
        }

        // Backtrack to find which items are selected
        List<Item> selectedItems = new ArrayList<>();
        int w = W;
        for (int i = n; i >= 1; i--) {
            if (table[i][w] != table[i-1][w]) {
                selectedItems.add(items[i-1]);
                w -= items[i-1].weight;
            }
        }

        // Print the total profit and weight of the selected items
        int totalProfit = table[n][W];
        int totalWeight = selectedItems.stream().mapToInt(item -> item.weight).sum();
        int num = selectedItems.size();
        System.out.printf("%d     %d   %d\n", num, totalProfit, totalWeight);


        // Print the selected items with their profits and weights
        System.out.println("Selected Items:");
        for (Item item : selectedItems) {
            System.out.println("Item" + item.id + " " + item.value + "   " + item.weight);
        }

        for (int i = 1; i <= n; i++) {
            System.out.printf("row%d    ", i);
            for (int j = 0; j <= W; j++) {
                System.out.printf("%d ", table[i][j]);
            }
            System.out.printf("end\n");
        }
        return totalProfit;
    }


    public static void main(String[] args) {
        /*int[] values = {20, 30, 35, 12, 3};
        int[] weights = {2, 5, 7, 3, 1};
        int capacity = 13;
        double maxValue = fractionalKnapsack(values, weights, capacity);
        System.out.println("Maximum value: " + maxValue);*/
        if (args.length != 2) {
            System.err.println("Usage: java KnapsackReader <input-file>");
            System.exit(1);
        }

        String inputFile = args[1];

        // Initialize lists to store values and weights
        List<Integer> values = new ArrayList<>();
        List<Integer> weights = new ArrayList<>();

        int cap = 0;
        // Read file and extract values and weights
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            // Read first line to get number of items and knapsack capacity
            String line = reader.readLine();
            String[] tokens = line.split(" ");
            int n = Integer.parseInt(tokens[0]);
            cap = Integer.parseInt(tokens[1]);
            //System.out.println(tokens[0]);

            // Read remaining lines to get values and weights of items
            for (int i = 0; i < n; i++) {
                line = reader.readLine();
                tokens = line.split(" ");
                int value = Integer.parseInt(tokens[1]);
                int weight = Integer.parseInt(tokens[2]);
                values.add(value);
                weights.add(weight);
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
            System.exit(1);
        }

        // Print values and weights
        //System.out.println("Values: " + values);
        //System.out.println("wei: " + weights);
        int num = values.size();
        int[] value = new int[num];
        for (int i = 0; i < num; i++) {
            value[i] = values.get(i);
        }
        int[] weight = new int[num];
        for (int i = 0; i < num; i++) {
            weight[i] = weights.get(i);
        }

        int n = value.length;
        Item[] items = new Item[n];
        for (int i = 0; i < n; i++) {
            items[i] = new Item(value[i], weight[i], i + 1);
        }

        dynamic(items, cap);
    }
}

