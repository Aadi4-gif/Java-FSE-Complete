public class Main {
    public static void main(String[] args) {
        System.out.println("=== Testing Builder Pattern Configuration ===\n");

        Computer gamingPC = new Computer.Builder("Intel i9 14900K", "32GB DDR5", "2TB NVMe SSD")
                .setGraphicsCard(true)
                .setWiFi(true)
                .setBluetooth(true)
                .build();
        System.out.println("[Configured: Gaming PC]");
        System.out.println(gamingPC);

        Computer officeServer = new Computer.Builder("AMD Ryzen 5 5600G", "16GB DDR4", "500GB SSD")
                .build();
        System.out.println("[Configured: Office Server]");
        System.out.println(officeServer);
    }
}
