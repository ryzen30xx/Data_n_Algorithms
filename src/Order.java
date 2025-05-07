public class Order {
    private static int nextId = 1;
    public int orderId;
    public String customerName;
    public String address;
    public Book book;
    public int quantity;

    public Order(String customerName, String address, Book book, int quantity) {
        this.orderId = nextId++;
        this.customerName = customerName;
        this.address = address;
        this.book = book;
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Order #" + orderId + ": " + quantity + " x " + book.title +
                " for " + customerName + " (" + address + ")";
    }
}
