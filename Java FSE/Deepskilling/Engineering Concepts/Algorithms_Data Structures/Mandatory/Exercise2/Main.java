import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== E-Commerce Search Platform Optimization ===\n");

        Product[] inventory = {
            new Product("P004", "Wireless Mouse", "Electronics"),
            new Product("P001", "Mechanical Keyboard", "Electronics"),
            new Product("P003", "Gaming Monitor", "Electronics"),
            new Product("P002", "Leather Office Chair", "Furniture")
        };

        String targetSearch = "Gaming Monitor";

        System.out.println("[Executing Linear Search...]");
        Product linearResult = SearchAlgorithms.linearSearch(inventory, targetSearch);
        System.out.println("Result: " + (linearResult != null ? linearResult : "Not Found"));

        System.out.println("\n------------------------------------------------\n");

        System.out.println("[Sorting Inventory for Binary Search Alignment...]");
        Arrays.sort(inventory);
        for (Product p : inventory) {
            System.out.println("  " + p);
        }

        System.out.println("\n[Executing Binary Search...]");
        Product binaryResult = SearchAlgorithms.binarySearch(inventory, targetSearch);
        System.out.println("Result: " + (binaryResult != null ? binaryResult : "Not Found"));
    }
}