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

        // print TINY
        System.out.printf("TINY lowest: $%.2f%n in %s %d", prices[lowIndex],
            monthName(months[lowIndex]), years[lowIndex]);
        System.out.printf("TINY highest: $%.2f%n in %s %d", prices[highIndex],
            monthName(months[highIndex]), years[highIndex]);
        System.out.printf("TINY biggest drop: $%.2f%n from %s %d to %s %d",
           //make a method for difference
            prices[diffIndex + 1] - prices[diffIndex], months[diffIndex], years[diffIndex],
            months[diffIndex + 1], years[diffIndex + 1]);
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

    public static int indexOfBiggestDrop(double[] prices) {
        int diffIndex = 0;
        for (int i = 0; i < prices.length - 1; i++) {
            double diff[i] = (prices[i + 1] - prices[i]) / prices[i] * 100;
            if (diff[i] > diff[diffIndex]) {
                diffIndex = i;
            }
        }
        return diffIndex;
    }
}
