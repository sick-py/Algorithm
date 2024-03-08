import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class createkn01 {

    private static final String[] ITEM_NAMES = {"Item1", "Item2", "Item3", "Item4", "Item5", "Item6", "Item7", "Item8"};

    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println("Usage: java KnapsackGenerator <output-file> <seed>");
            System.exit(1);
        }

        String outputFile = args[1];

        Random random = new Random();

        // Generate number of items between 4 and 8
        int n = random.nextInt(5) + 4;
        //System.out.println("Number of items: " + n);

        // Generate list of items
        List<Item> items = new ArrayList<>();
        int totalWeight = 0;
        for (int i = 0; i < n; i++) {
            int profit = random.nextInt(21) + 10;
            int weight = random.nextInt(16) + 5;
            items.add(new Item(ITEM_NAMES[i], profit, weight));
            totalWeight += weight;
        }

        // Set capacity of knapsack
        int capacity = (int) Math.floor(0.6 * totalWeight);
        System.out.printf("%d      %d \n", n, capacity);


        // Write problem to file
        try (FileWriter writer = new FileWriter(outputFile)) {
            writer.write(n + " " + capacity + "\n");
            for (Item item : items) {
                writer.write(item.getName() + " " + item.getProfit() + " " + item.getWeight() + "\n");
            }
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }

        System.out.println("Knapsack problem written to " + outputFile);
    }

    private static class Item {
        private final String name;
        private final int profit;
        private final int weight;

        public Item(String name, int profit, int weight) {
            this.name = name;
            this.profit = profit;
            this.weight = weight;
        }

        public String getName() {
            return name;
        }

        public int getProfit() {
            return profit;
        }

        public int getWeight() {
            return weight;
        }
    }
}
