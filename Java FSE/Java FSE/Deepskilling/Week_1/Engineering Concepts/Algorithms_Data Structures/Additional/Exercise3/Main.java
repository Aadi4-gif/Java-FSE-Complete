import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== E-Commerce Order Priority Sorting ===\n");

        Order[] ordersForBubble = {
            new Order("O101", "Alice", 250.50),
            new Order("O102", "Bob", 89.99),
            new Order("O103", "Charlie", 1200.00),
            new Order("O104", "David", 45.00)
        };

        Order[] ordersForQuick = Arrays.copyOf(ordersForBubble, ordersForBubble.length);

        System.out.println("[Running Bubble Sort...]");
        SortingAlgorithms.bubbleSort(ordersForBubble);
        for (Order o : ordersForBubble) {
            System.out.println("  " + o);
        }

        System.out.println("\n------------------------------------------------\n");

        System.out.println("[Running Quick Sort...]");
        SortingAlgorithms.quickSort(ordersForQuick, 0, ordersForQuick.length - 1);
        for (Order o : ordersForQuick) {
            System.out.println("  " + o);
        }
    }
}