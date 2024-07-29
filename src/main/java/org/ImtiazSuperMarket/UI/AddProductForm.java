package org.ImtiazSuperMarket.UI;

//import org.ImtiazSuperMarket.Domain.Product;
//import org.ImtiazSuperMarket.Domain.User;
//import org.ImtiazSuperMarket.Domain.Customer;

import org.ImtiazSuperMarket.Domain.Product;
import org.ImtiazSuperMarket.Domain.User;
import org.ImtiazSuperMarket.Service.UserSessionService;
import org.ImtiazSuperMarket.dao.ProductDao;

import java.util.List;

import javax.swing.*;
import java.awt.*;

public class AddProductForm {
    private JFrame frame;
    private JComboBox<String> productComboBox;
//    private JComboBox<String> userComboBox;
    private JComboBox<String> customerComboBox;
    private JTextField quantityField;
    private JButton addButton;
    private EmployeInvoiceUi parent;

    public AddProductForm(EmployeInvoiceUi parent) {
        this.parent = parent;
        frame = new JFrame("Add Product");
        frame.setLayout(new GridLayout(5, 2, 5, 5));

        productComboBox = new JComboBox<>();
//        userComboBox = new JComboBox<>();
        customerComboBox = new JComboBox<>();
        quantityField = new JTextField();


        addButton = new JButton("Add");

        // Load the combo boxes with data
        loadProductNames();
//        loadUserNames();
        parent.loadCustomerNames(customerComboBox);

        frame.add(new JLabel("Product Name:"));
        frame.add(productComboBox);
//        frame.add(new JLabel("User Name:"));
//        frame.add(userComboBox);
        frame.add(new JLabel("Customer Name:"));
        frame.add(customerComboBox);
        frame.add(new JLabel("Quantity:"));
        frame.add(quantityField);
        frame.add(new JLabel());
        frame.add(addButton);

        addButton.addActionListener(e -> addProduct());

        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

//    private final ProductDao productDao = new ProductDao();
    private void loadProductNames() {
        List<Product> products = parent.productDao.getAllProductName();
        for (Product product : products) {
            productComboBox.addItem(product.getProductName());
        }
    }

//    private void loadUserNames() {
//        List<User> users = parent.userDao.getAll();
//
//        for (User user : users) {
//            userComboBox.addItem(user.getUsername());
//        }
//    }

    private void addProduct() {
        String productName = (String) productComboBox.getSelectedItem();
        String userName = "";//(String) userComboBox.getSelectedItem();
        String customerName = (String) customerComboBox.getSelectedItem();
        int quantity = Integer.parseInt(quantityField.getText());
        float unitPrice = 100; // Replace with actual logic to get the unit price
        float totalPrice = unitPrice * quantity;
        UserSessionService session = UserSessionService.getInstance();
        if (session.isAuthenticated()) {
            userName = session.getUsername();
        } else {
            userName = "";
        }
        parent.addInvoiceRow(customerName, userName,
                productName, quantity, unitPrice, totalPrice);
        frame.dispose();
    }
}

