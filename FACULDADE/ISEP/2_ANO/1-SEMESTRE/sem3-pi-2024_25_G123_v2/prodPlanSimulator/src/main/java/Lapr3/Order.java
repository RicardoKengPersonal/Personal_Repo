package Lapr3;

public class Order {

    private int orderId;
    private int productId;
    private String priority;
    private int quantity;

    // Constructor to initialize Order object
    public Order(int orderId, int productId, String priority, int quantity) {
        this.orderId = orderId;
        this.productId = productId;
        this.priority = priority;
        this.quantity = quantity;
    }

    // Getters and setters for each field
    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    // Override toString method to print Order details
    @Override
    public String toString() {
        return "Order{id=" + orderId + ", productId=" + productId + ", priority=" + priority + ", quantity=" + quantity + "}";
    }
}