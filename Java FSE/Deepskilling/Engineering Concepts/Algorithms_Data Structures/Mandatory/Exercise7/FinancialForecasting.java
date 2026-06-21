class FinancialForecaster {

    public static double predictFutureValue(double presentValue, double growthRate, int years) {
        if (years <= 0) {
            return presentValue;
        }
        return predictFutureValue(presentValue, growthRate, years - 1) * (1 + growthRate);
    }
}