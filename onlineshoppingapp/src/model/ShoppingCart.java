package model;

import java.util.HashMap;
import java.util.Map;

public class ShoppingCart {
    private Customer customer;
    private Map<Product, Integer> items = new HashMap<>();

    public ShoppingCart(Customer customer) {
        this.customer = customer;
    }

    public void addProduct(Product product, int qty) {
        items.put(product, items.getOrDefault(product, 0) + qty);
    }

    public Map<Product, Integer> getItems() {
        return items;
    }

    public void clear() {
        items.clear();
    }
}
