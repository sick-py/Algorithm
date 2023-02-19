public class Main {
    public static void main(String[] args) {
        long time = System.nanoTime();
        for (int i = 0; i < 999; i++) {
            int t = 1;
        }
        time = (System.nanoTime() - time);
        double value = (double) time / 1_000_000_000.0;

        System.out.printf("%.6f\n", value);
    }
}