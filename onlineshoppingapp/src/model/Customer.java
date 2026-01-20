package model;

import java.util.ArrayList;
import java.util.List;

public class Customer extends user {
    private String address;
    private ShoppingCart cart;
    private List<Order> orders;

    public Customer(int userId, String username, String email, String address) {
        super(userId, username, email);
        this.address = address;
        this.cart = new ShoppingCart(this);
        this.orders = new ArrayList<>();
    }

    public ShoppingCart getCart() {
        return cart;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public String getAddress() {
        return address;
    }
}
