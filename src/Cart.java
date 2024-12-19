public class Cart {
    private OrderItem[] items;
    private int size;

    public Cart(int capacity) {
        items = new OrderItem[capacity];
        size = 0;
    }

    public void addItem(OrderItem item) {
        if (size < items.length) {
            items[size++] = item;
        } else {

        }
    }

    public OrderItem[] getItems() {
        return items;
    }
}