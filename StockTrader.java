import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class StockTrader {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File("tiny.txt"));
        String symbol = in.next();
        int n = in.nextInt();

        int[] years = new int[n];
        int[] months = new int[n];
        double[] prices = new double[n];

        loadData(in, years, months, prices);
        printData(years, months, prices);

        int lowIndex = indexOfLowest(prices);
        int highIndex = indexOfHighest(prices);
        // print TINY
        System.out.printf("TINY lowest: $%.2f in %s %d%n", prices[lowIndex],
            monthName(months[lowIndex]), years[lowIndex]);
        System.out.printf("TINY highest: $%.2f in %s %d%n", prices[highIndex],
            monthName(months[highIndex]), years[highIndex]);
        //        System.out.printf("TINY biggest drop: $%.2f%n from %s %d to %s %d",
        //            prices[diffIndex + 1] - prices[diffIndex], months[diffIndex],
        //            years[diffIndex], months[diffIndex + 1], years[diffIndex + 1]);

        // call simulators and display result
        double result = simulate(years, months, prices);
        System.out.printf("Final value: $%,.2f%n", result);
    }

    public static void loadData(
        Scanner in, int[] years, int[] months, double[] prices) { // for loading stuff from the file
        for (int i = 0; i < years.length; i++) {
            years[i] = in.nextInt(); // grab and store the value into year, month, price, etc.
            months[i] = in.nextInt();
            prices[i] = in.nextDouble();
        }
    }

    public static String monthName(int months) {
        String[] monthName = {
            "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
        return monthName[months - 1];
    }

    public static void printData(int[] years, int[] months, double[] prices) {
        for (int i = 0; i < years.length; i++) {
            System.out.printf("%s %d: $%.2f%n", monthName(months[i]), years[i], prices[i]);
        }
    }

    public static int indexOfLowest(double[] prices) {
        int lowIndex = 0;
        for (int i = 0; i < prices.length; i++) {
            if (prices[i] < prices[lowIndex]) {
                lowIndex = i;
            }
        }
        return lowIndex;
    }

    public static int indexOfHighest(double[] prices) {
        int highIndex = 0;
        for (int i = 0; i < prices.length; i++) {
            if (prices[i] > prices[highIndex]) {
                highIndex = i;
            }
        }
        return highIndex;
    }

    //   public static int indexOfBiggestDrop(double[] prices) {
    //      int diffIndex = 0;
    //      for (int i = 0; i < prices.length - 1; i++) {
    //          double diff[i] = (prices[i + 1] - prices[i]) / prices[i] * 100;
    //          if (diff[i] > diff[diffIndex]) {
    //              diffIndex = i;
    //         }
    //      }
    //    return diffIndex;
    //}

    public static final double START_CASH = 10000.0;
    public static final double FEE = 10.0;

    public static double simulate(int[] years, int[] months, double[] prices) {
        double cash = START_CASH;
        int shares = 0;
        double boughtAt = 0.0;

        for (int i = 0; i < prices.length; i++) {
            double[] history = Arrays.copyOf(prices, i + 1);
            String action = decide(history, shares > 0, boughtAt);

            if (action.equals("BUY") && shares == 0) {
                int canBuy = (int) ((cash - FEE) / prices[i]);
                if (canBuy > 0) {
                    shares = canBuy;
                    cash = cash - shares * prices[i] - FEE;
                    boughtAt = prices[i];
                    System.out.printf("%s %d: BUY %d shares at $%.2f%n", monthName(months[i]),
                        years[i], shares, prices[i]);
                }
            } else if (action.equals("SELL") && shares > 0) {
                cash = cash + shares * prices[i] - FEE;
                System.out.printf("%s %d: SELL %d shares at $%.2f%n", monthName(months[i]),
                    years[i], shares, prices[i]);
                shares = 0;
                boughtAt = 0.0;
            }
        }
        return cash + shares * prices[prices.length - 1];
    }
    
    public static String decide(double[] history, boolean holding, double boughtAt) {
    return buyAndHold(history, holding, boughtAt);   // change this line to switch strategies
}

}
