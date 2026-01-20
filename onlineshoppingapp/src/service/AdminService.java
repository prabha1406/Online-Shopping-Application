package service;

import model.Admin;
import model.Order;

import java.util.ArrayList;
import java.util.List;

public class AdminService {
    public static List<Admin> admins = new ArrayList<>();

    public void createAdmin(Admin admin) {
        admins.add(admin);
    }

    public void viewAdmins() {
        admins.forEach(a ->
                System.out.println("Admin: " + a.getUsername()));
    }

    public void updateOrderStatus(int orderId, String status) {
        for (Order o : OrderService.orders) {
            if (o.getOrderId() == orderId) {
                o.setStatus(status);
                System.out.println("Order status updated!");
            }
        }
    }
}
