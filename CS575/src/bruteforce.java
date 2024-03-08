import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class bruteforce {

    private static class Item {
        public int value;
        public int weight;
        public double valuePerWeight;

        int id;

        public Item(int value, int weight, int id) {
            this.value = value;
            this.weight = weight;
            this.valuePerWeight = (double) value / weight;
            this.id = id;
        }
    }

    public static void bruteforce(Item[] items, int capacity) {
        int n = items.length;
        int maxProfit = 0;
        int maxWeight = 0;
        Item[] bestSelection = new Item[n];

        // Generate all possible combinations of items using binary digits
        for (int i = 0; i < (1 << n); i++) {
            int currentProfit = 0;
            int currentWeight = 0;
            Item[] currentSelection = new Item[n];

            for (int j = 0; j < n; j++) {
                if ((i & (1 << j)) != 0) {
                    // Item j is included in the knapsack
                    currentProfit += items[j].value;
                    currentWeight += items[j].weight;
                    currentSelection[j] = items[j];
                }
            }

            if (currentWeight <= capacity && currentProfit > maxProfit) {
                // Found a better solution
                maxProfit = currentProfit;
                maxWeight = currentWeight;
                bestSelection = currentSelection;
            }
        }
        int num = 0;
        for (int i = 0; i < n; i++) {
            if (bestSelection[i] != null) {
                num++;
            }
        }
        // Print the total profit and weight
        System.out.printf("%d     %d   %d\n", num, maxProfit, maxWeight);

        // Print the selected items together with their profits and weights
        for (int i = 0; i < n; i++) {
            if (bestSelection[i] != null) {
                System.out.println("Item" + (i+1) + " " + bestSelection[i].value + "   " + bestSelection[i].weight);
            }
        }
    }

    // Main function to solve the fractional knapsack problem
    public static double fractionalKnapsack(int[] values, int[] weights, int capacity) {
        // Create an array of items from the values and weights arrays
        int n = values.length;
        Item[] items = new Item[n];
        for (int i = 0; i < n; i++) {
            items[i] = new Item(values[i], weights[i], i + 1);
        }

        // Sort the items in descending order of their value-to-weight ratio
        Arrays.sort(items, (a, b) -> Double.compare(b.valuePerWeight, a.valuePerWeight));

        // Initialize the total value and total weight to zero
        double totalValue = 0;
        double totalWeight = 0;

        // Take items in descending order of their value-to-weight ratio until the knapsack is full
        for (int i = 0; i < n && totalWeight < capacity; i++) {
            double fraction = Math.min(1.0, (capacity - totalWeight) / (double) items[i].weight);
            totalValue += fraction * items[i].value;
            totalWeight += fraction * items[i].weight;
        }

        // Return the maximum value that can be obtained
        return totalValue;
    }

    // Example usage
    public static void main(String[] args) {
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
        bruteforce(items, cap);
    }
}
