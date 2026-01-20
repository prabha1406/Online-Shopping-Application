package service;

import model.Order;
import java.util.ArrayList;
import java.util.List;

public class OrderService {
    public static List<Order> orders = new ArrayList<>();
    private static int orderCounter = 1;

    public Order createOrder(Order order) {
        orders.add(order);
        return order;
    }

    public int generateOrderId() {
        return orderCounter++;
    }

    public void viewAllOrders() {
        for (Order o : orders) {
            System.out.println("Order ID: " + o.getOrderId() +
                    ", Customer: " + o.getCustomer().getUsername() +
                    ", Status: " + o.getStatus());
            o.getProducts().forEach(p ->
                    System.out.println("  Product: " + p.getProduct().getName() +
                            ", Quantity: " + p.getQuantity()));
        }
    }
}
