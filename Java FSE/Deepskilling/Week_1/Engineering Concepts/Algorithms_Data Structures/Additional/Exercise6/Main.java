import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Library Management Search System ===\n");

        Book[] catalog = {
            new Book("B003", "The Great Gatsby", "F. Scott Fitzgerald"),
            new Book("B001", "To Kill a Mockingbird", "Harper Lee"),
            new Book("B004", "1984", "George Orwell"),
            new Book("B002", "Moby Dick", "Herman Melville")
        };

        String searchTitle = "1984";

        System.out.println("[Running Linear Search...]");
        Book linearResult = LibrarySearch.linearSearchByTitle(catalog, searchTitle);
        System.out.println("Result: " + (linearResult != null ? linearResult : "Book Not Found"));

        System.out.println("\n------------------------------------------------\n");

        System.out.println("[Sorting Catalog for Binary Search Alignment...]");
        Arrays.sort(catalog);
        for (Book b : catalog) {
            System.out.println("  " + b);
        }

        System.out.println("\n[Running Binary Search...]");
        Book binaryResult = LibrarySearch.binarySearchByTitle(catalog, searchTitle);
        System.out.println("Result: " + (binaryResult != null ? binaryResult : "Book Not Found"));
    }
}