public class Main {
    public static void main(String[] args) {
        System.out.println("=== Testing Adapter Pattern Payment Processing ===\n");

        XPayGateway xPayEngine = new XPayGateway();
        PaymentProcessor xPayProcessor = new XPayAdapter(xPayEngine);
        System.out.println("[Client using XPay Adapter]");
        xPayProcessor.processPayment(150.00);

        System.out.println("\n------------------------------------------------\n");

        YPayGateway yPayEngine = new YPayGateway();
        PaymentProcessor yPayProcessor = new YPayAdapter(yPayEngine);
        System.out.println("[Client using YPay Adapter]");
        yPayProcessor.processPayment(275.50);
    }
}