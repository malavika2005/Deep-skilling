import java.util.Scanner;

public class Forecast {

    
    public static double futureValueRecursive(double presentValue, double rate, int years) {
        if (years == 0) {
            return presentValue;
        }
        return futureValueRecursive(presentValue, rate, years - 1) * (1 + rate);
    }

   
    public static double futureValueMemo(double presentValue, double rate, int years, Double[] memo) {
        if (years == 0) return presentValue;
        if (memo[years] != null) return memo[years];
        memo[years] = futureValueMemo(presentValue, rate, years - 1, memo) * (1 + rate);
        return memo[years];
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter present value (Rs.): ");
        double presentValue = scanner.nextDouble();

        System.out.print("Enter annual growth rate (in %, e.g., 5): ");
        double ratePercent = scanner.nextDouble();
        double rate = ratePercent / 100;

        System.out.print("Enter number of years to forecast: ");
        int years = scanner.nextInt();

        double futureVal = futureValueRecursive(presentValue, rate, years);
        System.out.printf("\n Future Value (Recursive): Rs. %.2f\n", futureVal);

        // Optional: Using memoized version
        Double[] memo = new Double[years + 1];
        double optimizedVal = futureValueMemo(presentValue, rate, years, memo);
        System.out.printf("\n Future Value (Memoized): Rs. %.2f\n", optimizedVal);

        scanner.close();
    }
}
