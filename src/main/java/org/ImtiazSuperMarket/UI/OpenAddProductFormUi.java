package org.ImtiazSuperMarket.UI;

import org.ImtiazSuperMarket.Domain.Customer;
import org.ImtiazSuperMarket.Domain.Product;
import org.ImtiazSuperMarket.Domain.User;
import org.ImtiazSuperMarket.Service.AdminService;
import org.ImtiazSuperMarket.dao.CustomerDao;
import org.ImtiazSuperMarket.dao.ProductDao;
import org.ImtiazSuperMarket.dao.UserDoa;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class OpenAddProductFormUi {
    private final ProductDao productDao = new ProductDao();
    private final UserDoa userDao = new UserDoa();

    private  final CustomerDao customerDao = new CustomerDao();
    private DefaultTableModel tableModel1;
    private final AdminService adminService = new AdminService();

    OpenAddProductFormUi(){

        JFrame addProductFrame = new JFrame("Add Product");

        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));


        JLabel customerName = new JLabel("Customer Name");
        JComboBox<String> customerComboBox = new JComboBox<>();
        loadCustomerNames(customerComboBox);

        JLabel userName = new JLabel("User Name");
        JComboBox<String> userComboBox = new JComboBox<>();
        loadUserNames(userComboBox);

        JLabel productLabel = new JLabel("Product Name:");
        JComboBox<String> productComboBox = new JComboBox<>();
        loadProductNames(productComboBox);

        JLabel quantityLabel = new JLabel("Quantity:");
        JTextField getQuantity = new JTextField(20);

        JButton addButton = new JButton("Add");

        panel.add(productLabel);
        panel.add(productComboBox);
        panel.add(customerName);
        panel.add(customerComboBox);
        panel.add(userName);
        panel.add(userComboBox);
        panel.add(quantityLabel);
        panel.add(getQuantity);
        panel.add(new JLabel()); // Empty cell
        panel.add(addButton);

        addButton.addActionListener(e ->{
            String customerCombobox =(String) customerComboBox.getSelectedItem();
            String[] customerId = customerCombobox.split(", ");
            Integer cid = Integer.valueOf(customerId[0]);

            String userCombobox =(String) userComboBox.getSelectedItem();
            String[] userId = userCombobox.split(",");
            Integer uId = Integer.valueOf(customerId[0]);


            adminService.save(cid,uId, (String) productComboBox.getSelectedItem(), Integer.valueOf(getQuantity.getText()));
        } );

        addProductFrame.add(panel);
        addProductFrame.setSize(400, 200);
        addProductFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        addProductFrame.setLocationRelativeTo(null);
        addProductFrame.setVisible(true);
    }

    private void loadUserNames(JComboBox<String> userComboBox) {
        List<User> userNames = userDao.getAllUserName();
        for (User name : userNames) {
            userComboBox.addItem(name.getId()+", "+name.getUsername());
        }
    }

    private void loadProductNames(JComboBox<String> productComboBox) {
        List<Product> productNames = productDao.getAllProductName();
        for (Product name : productNames) {
            productComboBox.addItem(name.getProductName());
        }
    }
        private void loadCustomerNames(JComboBox<String> customerComboBox) {
            List<Customer> customerNames = customerDao.getAllCustomer();
            for (Customer name : customerNames) {
                customerComboBox.addItem(name.getId()+", "+name.getName());
            }
    }
}
