package app;

import model.*;
import service.*;

import java.util.*;

public class MainApp {

    static Scanner sc = new Scanner(System.in);

    static ProductService productService = new ProductService();
    static CustomerService customerService = new CustomerService();
    static OrderService orderService = new OrderService();
    static AdminService adminService = new AdminService();

    public static void main(String[] args) {

        while (true) {
            System.out.println("\n1. Admin Menu");
            System.out.println("2. Customer Menu");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");

            int choice = sc.nextInt();

            if (choice == 1) {
                adminMenu();
            } else if (choice == 2) {
                customerMenu();
            } else if (choice == 3) {
                System.out.println("Exiting...");
                break;
            } else {
                System.out.println("Invalid choice!");
            }
        }
    }

    // ================= ADMIN MENU =================
    static void adminMenu() {

        while (true) {
            System.out.println("\nAdmin Menu:");
            System.out.println("1. Add Product");
            System.out.println("2. Remove Product");
            System.out.println("3. View Products");
            System.out.println("4. Create Admin");
            System.out.println("5. View Admins");
            System.out.println("6. Update Order Status");
            System.out.println("7. View Orders");
            System.out.println("8. Return");

            System.out.print("Choose an option: ");
            int ch = sc.nextInt();

            if (ch == 1) {
                System.out.print("Enter Product ID: ");
                int id = sc.nextInt();
                sc.nextLine();

                System.out.print("Enter Product Name: ");
                String name = sc.nextLine();

                System.out.print("Enter Product Price: ");
                double price = sc.nextDouble();

                System.out.print("Enter Stock Quantity: ");
                int qty = sc.nextInt();

                productService.addProduct(new Product(id, name, price, qty));
                System.out.println("Product added successfully!");

            } else if (ch == 2) {
                System.out.print("Enter Product ID to remove: ");
                int id = sc.nextInt();
                productService.removeProduct(id);
                System.out.println("Product removed successfully!");

            } else if (ch == 3) {
                productService.viewProducts();

            } else if (ch == 4) {
                System.out.print("Enter Admin ID: ");
                int id = sc.nextInt();
                sc.nextLine();

                System.out.print("Enter Username: ");
                String name = sc.nextLine();

                System.out.print("Enter Email: ");
                String email = sc.nextLine();

                adminService.createAdmin(new Admin(id, name, email));
                System.out.println("Admin created successfully!");

            } else if (ch == 5) {
                adminService.viewAdmins();

            } else if (ch == 6) {
                System.out.print("Enter Order ID: ");
                int oid = sc.nextInt();
                sc.nextLine();

                System.out.print("Enter new status (Completed/Delivered/Cancelled): ");
                String status = sc.nextLine();

                adminService.updateOrderStatus(oid, status);

            } else if (ch == 7) {
                orderService.viewAllOrders();

            } else if (ch == 8) {
                System.out.println("Exiting Admin...");
                return;
            } else {
                System.out.println("Invalid choice!");
            }
        }
    }

    // ================= CUSTOMER MENU =================
    static void customerMenu() {

        while (true) {
            System.out.println("\nCustomer Menu:");
            System.out.println("1. Create Customer");
            System.out.println("2. View Customers");
            System.out.println("3. Place Order");
            System.out.println("4. View Orders");
            System.out.println("5. View Products");
            System.out.println("6. Return");

            System.out.print("Choose an option: ");
            int ch = sc.nextInt();

            if (ch == 1) {
                System.out.print("Enter User ID: ");
                int id = sc.nextInt();
                sc.nextLine();

                System.out.print("Enter Username: ");
                String name = sc.nextLine();

                System.out.print("Enter Email: ");
                String email = sc.nextLine();

                System.out.print("Enter Address: ");
                String address = sc.nextLine();

                customerService.createCustomer(new Customer(id, name, email, address));
                System.out.println("Customer created successfully!");

            } else if (ch == 2) {
                customerService.viewCustomers();

            } else if (ch == 3) {
                System.out.print("Enter Customer ID: ");
                int cid = sc.nextInt();

                Customer c = customerService.getCustomerById(cid);
                if (c == null) {
                    System.out.println("Customer not found!");
                    continue;
                }

                List<ProductQuantityPair> orderItems = new ArrayList<>();

                while (true) {
                    System.out.print("Enter Product ID (-1 to stop): ");
                    int pid = sc.nextInt();

                    if (pid == -1) {
                        break;
                    }

                    System.out.print("Enter Quantity: ");
                    int qty = sc.nextInt();

                    Product p = productService.getProductById(pid);
                    if (p != null && p.getStockQuantity() >= qty) {
                        p.reduceStock(qty);
                        orderItems.add(new ProductQuantityPair(p, qty));
                    } else {
                        System.out.println("Invalid product or insufficient stock!");
                    }
                }

                if (orderItems.isEmpty()) {
                    System.out.println("No products added. Order cancelled.");
                    continue;
                }

                Order order = new Order(orderService.generateOrderId(), c, orderItems);
                orderService.createOrder(order);
                c.getOrders().add(order);

                System.out.println("Order placed successfully!");

            } else if (ch == 4) {
                System.out.print("Enter Customer ID: ");
                int cid = sc.nextInt();

                Customer c = customerService.getCustomerById(cid);
                if (c != null) {
                    for (Order o : c.getOrders()) {
                        System.out.println("Order ID: " + o.getOrderId()
                                + ", Status: " + o.getStatus());

                        for (ProductQuantityPair pq : o.getProducts()) {
                            System.out.println("  Product: "
                                    + pq.getProduct().getName()
                                    + ", Quantity: " + pq.getQuantity());
                        }
                    }
                } else {
                    System.out.println("Customer not found!");
                }

            } else if (ch == 5) {
                productService.viewProducts();

            } else if (ch == 6) {
                System.out.println("Exiting Customer Menu...");
                return;
            } else {
                System.out.println("Invalid choice!");
            }
        }
    }
}
