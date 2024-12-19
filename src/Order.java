public class Order {

    private Customer customer;
    private OrderItem[] items;
    private double totalAmount;

    public Order(Customer customer, OrderItem[] items, double totalAmount) {
        this.customer = customer;
        this.items = items;
        this.totalAmount = totalAmount;
    }

    public Customer getCustomer() {
        return customer;
    }

    public OrderItem[] getItems() {
        return items;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setItems(OrderItem[] items) {
        this.items = items;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

}