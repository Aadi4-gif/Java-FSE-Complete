import java.util.HashMap;
import java.util.Map;

class Product {
    private String productId;
    private String productName;
    private int quantity;
    private double price;

    public Product(String productId, String productName, int quantity, double price) {
        this.productId = productId;
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
    }

    public String getProductId() { return productId; }
    public String getProductName() { return productName; }
    
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    @Override
    public String toString() {
        return "ID: " + productId + " | Name: " + productName + " | Stock: " + quantity + " | Price: $" + price;
    }
}

class Inventory {
    private Map<String, Product> productMap = new HashMap<>();

    public void addProduct(Product product) {
        if (productMap.containsKey(product.getProductId())) {
            System.out.println("Error: Product ID " + product.getProductId() + " already exists.");
        } else {
            productMap.put(product.getProductId(), product);
            System.out.println("Added: " + product.getProductName());
        }
    }

    public void updateProduct(String productId, int newQuantity, double newPrice) {
        Product product = productMap.get(productId);
        if (product != null) {
            product.setQuantity(newQuantity);
            product.setPrice(newPrice);
            System.out.println("Updated ID " + productId + ": " + product.getProductName());
        } else {
            System.out.println("Error: Product ID " + productId + " not found.");
        }
    }

    public void deleteProduct(String productId) {
        if (productMap.containsKey(productId)) {
            Product removed = productMap.remove(productId);
            System.out.println("Deleted: " + removed.getProductName());
        } else {
            System.out.println("Error: Product ID " + productId + " not found.");
        }
    }

    public void displayInventory() {
        if (productMap.isEmpty()) {
            System.out.println("Inventory is empty.");
            return;
        }
        for (Product p : productMap.values()) {
            System.out.println(p);
        }
    }
}