import jdk.nashorn.internal.ir.SplitReturn;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static class Card {
        String name;
        Integer price;

        public Card(String a, String b) {
            name = a;
            price = Integer.parseInt(b);
        }
    }
    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
        String marketFileName = "", priceFileName = "";

        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("-m")) {
                marketFileName = args[i + 1];
            } else if (args[i].equals("-p")) {
                priceFileName = args[i + 1];
            }
        }

        Scanner marketFile = new Scanner(new File(marketFileName));
        Scanner priceFile = new Scanner(new File(priceFileName));

        int n = Integer.parseInt(marketFile.nextLine());
        HashMap<String, Integer> markets = new HashMap<>();
        for (int i = 0; i < n; i++) {
            String[] line = marketFile.nextLine().split(" ");
            markets.put(line[0], Integer.parseInt(line[1]));
            //markets[i] = new Card(line[0], line[1]);
        }

        PrintWriter writer = new PrintWriter("output.txt", "UTF-8");

        while (priceFile.hasNextLine()) {
            String[] l = priceFile.nextLine().split(" ");
            n = Integer.parseInt(l[0]);
            int  w = Integer.parseInt(l[1]);
            Card[] prices = new Card[n];
            for (int i = 0; i < n; i++) {
                String[] line = priceFile.nextLine().split(" ");
                prices[i] = new Card(line[0], line[1]);
            }
            long time = System.nanoTime();
            int res = computeMaxProfit(markets, prices, w);
            time = (System.nanoTime() - time);
            double value = (double) time / 1_000_000_000.0;
            writer.printf("%d %d %d %.6f", n, res, cardNum, value);
            for (int bit = 0; bit < n; bit++) {
                if ((SET & (1 <<bit)) != 0) {
                    //the bit th bit is 1
                    writer.println(prices[bit].name);
                }
            }
        }

        writer.close();
    }

    static int cardNum;
    static int SET;
    private static int computeMaxProfit(HashMap<String, Integer> markets, Card[] prices, int w) {
        int n = prices.length;
        int res = -1;
        int sum = 0;
        int card = 0;
        SET = 0;
        cardNum = 0;
        for (int i = 0; i < Math.pow(2, n); i++) {
            //compute the ith combination
            int left = w;
            sum = 0;
            card = 0;
            for (int bit = 0; bit < n; bit++) {
                if ((i & (1 <<bit)) != 0) {
                    //the bit th bit is 1
                    //System.out.printf("mprice %d, Name %s, price %d\n", markets.get(prices[bit].name), prices[bit].name, prices[bit].price);
                    if (left >= prices[bit].price) {
                        if (!markets.containsKey(prices[bit].name)) {
                            System.out.printf("error: no this card named %s in market\n", prices[bit].name);
                            return -1;
                        }
                        sum += markets.get(prices[bit].name) - prices[bit].price;
                        left -= prices[bit].price;
                        card++;
                    }
                }
            }
            //System.out.printf("current sum is %d \n", sum);
            if (sum > res) {
                res = sum;
                cardNum = card;
                SET = i;
            }
        }
        return res;
    }
}