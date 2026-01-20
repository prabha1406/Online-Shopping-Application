package service;

import model.*;

import java.util.ArrayList;
import java.util.List;

public class CustomerService {
    public static List<Customer> customers = new ArrayList<>();

    public void createCustomer(Customer customer) {
        customers.add(customer);
    }

    public Customer getCustomerById(int id) {
        for (Customer c : customers) {
            if (c.getUserId() == id)
                return c;
        }
        return null;
    }

    public void viewCustomers() {
        for (Customer c : customers) {
            System.out.println("User ID: " + c.getUserId() +
                    ", Username: " + c.getUsername() +
                    ", Email: " + c.getEmail() +
                    ", Address: " + c.getAddress());
        }
    }
}
