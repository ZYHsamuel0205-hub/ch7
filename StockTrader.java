import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class StockTrader {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File("tiny.txt"));
        String symbol = in.next();
        int n = in.nextInt();

        int[] year = new int[n];
        int[] month = new int[n];
        double[] price = new double[n];

        loadData(in, years, months, prices);
    }

    public static void loadData(
        Scanner in, int[] years, int[] months, double[] prices) { // for loading stuff from the file
        for (int i = 0; i < years.length; i++) {
            year[i] = in.nextInt(); // grab and store the value into year
            month[i] = in.nextInt();
            price[i] = in.nextDouble();
        }

        public static String monthName(int month) {
            String[] monthNames = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
            return monthName[month - 1];
        }

        public static void printData(int[] years, int[] months, double[] prices) {}
    }
}
