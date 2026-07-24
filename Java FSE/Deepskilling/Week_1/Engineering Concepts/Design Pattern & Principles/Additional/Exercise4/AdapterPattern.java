interface PaymentProcessor {
    void processPayment(double amount);
}

class XPayGateway {
    public void makePayment(double amountInDollars) {
        System.out.println("Processing payment of $" + amountInDollars + " via XPay Gateway.");
    }
}

class YPayGateway {
    public void authorizeAndPay(double amount, String securityToken) {
        System.out.println("Authorizing token [" + securityToken + "] and charging $" + amount + " via YPay Gateway.");
    }
}

class XPayAdapter implements PaymentProcessor {
    private XPayGateway xPayGateway;

    public XPayAdapter(XPayGateway xPayGateway) {
        this.xPayGateway = xPayGateway;
    }

    @Override
    public void processPayment(double amount) {
        xPayGateway.makePayment(amount);
    }
}

class YPayAdapter implements PaymentProcessor {
    private YPayGateway yPayGateway;

    public YPayAdapter(YPayGateway yPayGateway) {
        this.yPayGateway = yPayGateway;
    }

    @Override
    public void processPayment(double amount) {
        yPayGateway.authorizeAndPay(amount, "YPAY-SECURE-2026");
    }
}