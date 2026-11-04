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
        printData (years, months, prices);
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
		System.out.println (monthName(months[i]) + " " + years[i] + ": $" + prices[i]);
		}
	}
	
public static int indexOfLowest(double[] prices) {
	 
	}
	//public static int indexOfHighest(double[] prices) {}
}
