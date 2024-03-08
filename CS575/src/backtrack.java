import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class backtrack {
    int maxProfit;
    //int weight;
    int capacity;
    Item[] items;

    int numOfItems;
    int order;

    List<Item> include;
    List<Item> bestSet;




    public backtrack(int capacity, int[] values, int[] weights) {
        this.capacity = capacity;
        //this.weight = weight;
        order = 1;
        maxProfit = 0;
        numOfItems = values.length;
        items = new Item[numOfItems + 1];
        items[0] = new Item(0, 0, 0);
        for (int i = 0; i < numOfItems; i++) {
            items[i + 1] = new Item(values[i], weights[i], i + 1);
        }
        // Sort the items in descending order of their value-to-weight ratio
        Arrays.sort(items, (a, b) -> Double.compare(b.valuePerWeight, a.valuePerWeight));
        include = new ArrayList<>();
        bestSet = new ArrayList<>();
    }
    private static class Item {
        public int value;
        public int weight;
        public double valuePerWeight;
        public int id;

        public Item(int value, int weight, int id) {
            this.value = value;
            this.weight = weight;
            this.valuePerWeight = (double) value / weight;
            this.id = id;
        }
    }

    public static Item[] createkn01() {
        int[] values = {10, 13, 21, 24};
        int[] weights = {7, 9, 10, 15};
        int n = values.length;
        Item[] items = new Item[n];
        for (int i = 0; i < n; i++) {
            items[i] = new Item(values[i], weights[i], i + 1);
        }
        return items;
    }

    public void out() {
        int num = bestSet.size();
        int sumWeights = 0;
        for (int i = 0; i < num; i++) {
            sumWeights += bestSet.get(i).weight;
        }
        System.out.printf("%d     %d   %d\n", num, maxProfit, sumWeights);

        // Print the selected items together with their profits and weights
        for (int i = 0; i < num; i++) {
            System.out.println("Item" + bestSet.get(i).id + " " + bestSet.get(i).value + "   " + bestSet.get(i).weight);
        }
    }

    public void knapsack(int i, int profit, int weight) {
        if (weight <= capacity && profit > maxProfit) {
            //save solution
            maxProfit = profit;
            bestSet = new ArrayList<>(include);
        }

        if (promising(i, profit, weight)) {
            include.add(items[i + 1]);
            knapsack(i + 1, profit + items[i + 1].value, weight + items[i + 1].weight);
            include.remove(include.size() - 1);
            knapsack(i + 1, profit, weight);
        }
    }


    boolean promising(int i, int profit, int weight) {
        //it's feasible and can lead to feasible solution
        //can get better bound > best
        System.out.printf("node%d: profit: %d weight: %d bound: ", order, profit, weight);
        order++;
        if (weight >= capacity) {
            System.out.printf(" no compute\n");
            return false;
        }

        int bound = fractionalKnapsack(i + 1, weight, profit);
        System.out.printf("%d\n", bound);
        return (bound > maxProfit);
    }


    // Main function to solve the fractional knapsack problem
    public int fractionalKnapsack(int i, int weight, int profit) { //i = 1 is literally the first object
        double totalValue = profit;
        double totalWeight = weight;

        int bound = maxProfit;

        for (int j = i; j <= numOfItems && totalWeight < capacity; j++) {
            double fraction = Math.min(1.0, (capacity - totalWeight) / (double) items[j].weight);
            totalValue += fraction * items[j].value;
            totalWeight += fraction * items[j].weight;
        }

        return (int)totalValue;

        /*while (weight < capacity && i <= numOfItems) {
            if (weight + items[i].weight <= capacity) {

            }
        }*/

    }

    // Example usage
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


        backtrack test = new backtrack(cap, value, weight);
        test.knapsack(0, 0, 0);
        test.out();
    }
}
