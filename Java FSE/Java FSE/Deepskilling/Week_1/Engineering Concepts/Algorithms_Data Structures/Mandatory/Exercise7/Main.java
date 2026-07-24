public class Main {
    public static void main(String[] args) {
        System.out.println("=== Financial Forecasting Tool ===");

        double principal = 1000.00;
        double annualRate = 0.05;
        int forecastPeriod = 10;

        System.out.println("Initial Investment: $" + principal);
        System.out.println("Assumed Growth Rate: " + (annualRate * 100) + "%");
        System.out.println("Forecasting Window: " + forecastPeriod + " years\n");

        double projectedValue = FinancialForecaster.predictFutureValue(principal, annualRate, forecastPeriod);

        System.out.printf("Projected Value after %d years: $%.2f%n", forecastPeriod, projectedValue);
    }
}