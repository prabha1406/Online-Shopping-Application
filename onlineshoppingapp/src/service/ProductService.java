package service;

import model.Product;
import java.util.ArrayList;
import java.util.List;

public class ProductService {
    public static List<Product> products = new ArrayList<>();

    public void addProduct(Product product) {
        products.add(product);
    }

    public void removeProduct(int id) {
        products.removeIf(p -> p.getProductId() == id);
    }

    public Product getProductById(int id) {
        for (Product p : products) {
            if (p.getProductId() == id)
                return p;
        }
        return null;
    }

    public void viewProducts() {
        for (Product p : products) {
            System.out.println(p);
        }
    }
}
