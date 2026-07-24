public class Main {
    public static void main(String[] args) {
        System.out.println("=== Warehouse Inventory Management System ===\n");

        Inventory warehouse = new Inventory();

        warehouse.addProduct(new Product("P101", "Laptop", 50, 999.99));
        warehouse.addProduct(new Product("P102", "Smartphone", 120, 499.99));
        warehouse.addProduct(new Product("P103", "Wireless Headphones", 80, 89.99));

        System.out.println("\n--- Current Inventory ---");
        warehouse.displayInventory();

        System.out.println("\n--- Updating Product ---");
        warehouse.updateProduct("P102", 115, 479.99);

        System.out.println("\n--- Deleting Product ---");
        warehouse.deleteProduct("P103");

        System.out.println("\n--- Final Inventory ---");
        warehouse.displayInventory();
    }
}