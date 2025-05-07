import java.util.*;

public class Main {
    private static final List<Book> catalog = new ArrayList<>();
    private static final Queue<Order> orderQueue = new Queue<>();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        catalog.add(new Book("Java Basics", "Alice", 15.99));
        catalog.add(new Book("Algorithms", "Bob", 25.99));
        catalog.add(new Book("Data Structures", "Charlie", 19.99));

        while (true) {
            showMenu();
            System.out.print("Choose an option: ");
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1 -> addOrder();
                case 2 -> processOrder();
                case 3 -> viewAllOrders();
                case 4 -> viewOrdersLIFO();
                case 5 -> sortBooks();
                case 6 -> searchBook();
                case 7 -> addBook();
                case 8 -> {
                    for (int i = 0; i <= 3; i++) {
                        String dots = ".".repeat(i);
                        System.out.printf("\rExiting program%s", dots);
                        try {
                            Thread.sleep(300);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                    System.out.println();
                    return;
                }
                default -> System.out.println("Invalid option!");
            }
        }
    }

    private static void showMenu() {
        System.out.println("\n===== BOOKSTORE ORDER SYSTEM =====");
        System.out.println("1. Add Order (FIFO)");
        System.out.println("2. Process Order (FIFO - Queue)");
        System.out.println("3. View All Orders");
        System.out.println("4. View Orders in LIFO Order (Stack)");
        System.out.println("5. Sort Books by Title");
        System.out.println("6. Search Book by Title");
        System.out.println("7. Add Book to Catalog");
        System.out.println("8. Exit");
    }

    private static void addOrder() {
        System.out.println("\n== Add New Order ==");

        System.out.print("Customer Name: ");
        String name = scanner.nextLine();

        System.out.print("Address: ");
        String address = scanner.nextLine();

        System.out.print("Book Title: ");
        String bookTitle = scanner.nextLine();

        Book book = SearchUtil.findBookByTitle(catalog, bookTitle);
        if (book == null) {
            System.out.println("Book not found!");
            return;
        }

        System.out.print("Quantity: ");
        int quantity = Integer.parseInt(scanner.nextLine());

        Order newOrder = new Order(name, address, book, quantity);
        orderQueue.enqueue(newOrder);
        System.out.println("Order added!");
    }

    private static void processOrder() {
        System.out.println("\n== Process Order (FIFO) ==");
        Order order = orderQueue.dequeue();
        if (order != null) {
            System.out.println("Processed: " + order);
        } else {
            System.out.println("No orders to process!");
        }
    }

    private static void viewAllOrders() {
        System.out.println("\n== All Orders in Queue ==");
        if (orderQueue.isEmpty()) {
            System.out.println("No orders in the queue.");
            return;
        }

        Queue<Order> tempQueue = new Queue<>();
        while (!orderQueue.isEmpty()) {
            Order order = orderQueue.dequeue();
            System.out.println(order);
            tempQueue.enqueue(order);
        }

        while (!tempQueue.isEmpty()) {
            orderQueue.enqueue(tempQueue.dequeue());
        }
    }

    private static void viewOrdersLIFO() {
        System.out.println("\n== Orders in LIFO Order ==");
        Stack<Order> stack = new Stack<>();

        while (!orderQueue.isEmpty()) {
            stack.push(orderQueue.dequeue());
        }

        while (!stack.isEmpty()) {
            Order order = stack.pop();
            System.out.println(order);
            orderQueue.enqueue(order); // put back
        }
    }

    private static void sortBooks() {
        System.out.println("\n== Sorted Book Catalog ==");
        SortUtil.sortBooksByTitle(catalog);
        for (Book book : catalog) {
            System.out.println(book);
        }
    }

    private static void searchBook() {
        System.out.println("\n== Search Book ==");
        System.out.print("Enter book title: ");
        String title = scanner.nextLine();

        Book book = SearchUtil.findBookByTitle(catalog, title);
        if (book != null) {
            System.out.println("Found: " + book);
        } else {
            System.out.println("Book not found.");
        }
    }

    private static void addBook() {
        System.out.println("\n== Add Book to Catalog ==");

        System.out.print("Title: ");
        String title = scanner.nextLine();

        System.out.print("Author: ");
        String author = scanner.nextLine();

        System.out.print("Price: ");
        double price = Double.parseDouble(scanner.nextLine());

        catalog.add(new Book(title, author, price));
        System.out.println("Book added successfully.");
    }
}
