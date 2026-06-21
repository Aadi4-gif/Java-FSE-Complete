public class Computer {
    private final String CPU;
    private final String RAM;
    private final String storage;
    private final boolean hasGraphicsCard;
    private final boolean hasWiFi;
    private final boolean hasBluetooth;

    private Computer(Builder builder) {
        this.CPU = builder.CPU;
        this.RAM = builder.RAM;
        this.storage = builder.storage;
        this.hasGraphicsCard = builder.hasGraphicsCard;
        this.hasWiFi = builder.hasWiFi;
        this.hasBluetooth = builder.hasBluetooth;
    }

    @Override
    public String toString() {
        return "Computer Configuration:\n" +
               "  - CPU: " + CPU + "\n" +
               "  - RAM: " + RAM + "\n" +
               "  - Storage: " + storage + "\n" +
               "  - Dedicated Graphics: " + (hasGraphicsCard ? "Yes" : "No") + "\n" +
               "  - Wi-Fi Module: " + (hasWiFi ? "Yes" : "No") + "\n" +
               "  - Bluetooth Module: " + (hasBluetooth ? "Yes" : "No") + "\n";
    }

    public static class Builder {
        private final String CPU;
        private final String RAM;
        private final String storage;
        private boolean hasGraphicsCard = false;
        private boolean hasWiFi = false;
        private boolean hasBluetooth = false;

        public Builder(String CPU, String RAM, String storage) {
            this.CPU = CPU;
            this.RAM = RAM;
            this.storage = storage;
        }

        public Builder setGraphicsCard(boolean hasGraphicsCard) {
            this.hasGraphicsCard = hasGraphicsCard;
            return this;
        }

        public Builder setWiFi(boolean hasWiFi) {
            this.hasWiFi = hasWiFi;
            return this;
        }

        public Builder setBluetooth(boolean hasBluetooth) {
            this.hasBluetooth = hasBluetooth;
            return this;
        }

        public Computer build() {
            return new Computer(this);
        }
    }
}