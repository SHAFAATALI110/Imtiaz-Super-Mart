package org.ImtiazSuperMarket.UI;

import org.ImtiazSuperMarket.Domain.Customer;
import org.ImtiazSuperMarket.Domain.InvoiceMaster;
import org.ImtiazSuperMarket.Domain.Product;
import org.ImtiazSuperMarket.Domain.User;
import org.ImtiazSuperMarket.Service.InvoiceMasterServices;
import org.ImtiazSuperMarket.Service.UserSessionService;
import org.ImtiazSuperMarket.dao.CustomerDao;
import org.ImtiazSuperMarket.dao.InvoiceMasterDao;
import org.ImtiazSuperMarket.dao.ProductDao;
import org.ImtiazSuperMarket.dao.UserDoa;

import javax.swing.*;
import javax.swing.event.AncestorListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;


public class AddOrderUi {
    CustomerDao customerDao = new CustomerDao();
    ProductDao productDao = new ProductDao();
    private JFrame frame;
    private JTable invoiceDetailTbl;
    private DefaultTableModel tableModel;
    private JComboBox<String> productComboBox;
    private JComboBox<String> customerComboBox;
    private JComboBox<String> userComboBox;
    private JTextField quantityField;
    JScrollPane scrollPane;

    private final InvoiceMasterServices invoiceMasterServices = new InvoiceMasterServices();
    private final UserDoa userDoa = new UserDoa();
    public AddOrderUi() {
        frame = new JFrame("Add Product");
        frame.setLayout(new GridLayout(4,2,5,5));
        JPanel inputPanel = new JPanel(new GridLayout(2, 2, 5, 5));
        productComboBox = new JComboBox<>();
        loadProductNames(productComboBox);

        customerComboBox = new JComboBox<>();
        loadCustomerNames(customerComboBox);

        userComboBox = new JComboBox<>();
        loaduserNames(userComboBox);


        quantityField = new JTextField();

        JButton addBtn = new JButton("Add");


        inputPanel.add(new JLabel("Product:"));
        inputPanel.add(productComboBox);
        inputPanel.add(new JLabel("Customer:"));
        inputPanel.add(customerComboBox);
        inputPanel.add(new JLabel("User:"));
        inputPanel.add(new JLabel("Quantity:"));
        inputPanel.add(userComboBox);
        inputPanel.add(quantityField);
        inputPanel.add(new JLabel(""));
        inputPanel.add(addBtn);

//        frame.add(addBtn, BorderLayout.CENTER);

        String[] columnNames = {"Product Name", "Customer Name", "Quantity"};
        tableModel = new DefaultTableModel(columnNames, 0);
        invoiceDetailTbl = new JTable(tableModel);
        scrollPane = new JScrollPane(invoiceDetailTbl);
        frame.add(inputPanel, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.SOUTH);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton saveBtn = new JButton("Save");
        buttonPanel.add(saveBtn);

        frame.add(buttonPanel, BorderLayout.SOUTH);

        addBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addProductDetail();
            }
        });

        saveBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String product = (String) productComboBox.getSelectedItem();
                String userName = (String) userComboBox.getSelectedItem();
                String customerName = (String) customerComboBox.getSelectedItem();
                String[] parts = customerName.split(" ", 2);
                Integer customerId = Integer.parseInt(parts[0]);
                String[] partsUser = userName.split(" ", 2);
                Integer userId = Integer.parseInt(partsUser[0]);
                String[] partsProduct = product.split(" ", 2);
                String productName = partsProduct[1];
                int quantity = Integer.parseInt(quantityField.getText());
                UserSessionService session = UserSessionService.getInstance();
                if (session.isAuthenticated()) {
                    userName = session.getUsername();
                } else {
                    userName = "";
                }

                invoiceMasterServices.save(customerId,userId);
                invoiceMasterServices.saveInvoiceDetail(productName,quantity);

                frame.dispose();
            }
        });

        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }


    private void loaduserNames(JComboBox<String> userComboBox) {
        List<User> userNames = userDoa.getAll();
        for (User name : userNames) {
            userComboBox.addItem(name.getId()+" "+name.getUsername());
        }
    }

    public void addProductDetail() {
        String userName = ""; //(String) userComboBox.getSelectedItem();

        UserSessionService session = UserSessionService.getInstance();
        if (session.isAuthenticated()) {
            userName = session.getUsername();
        } else {
            userName = "";
        }
        String productName = (String) productComboBox.getSelectedItem();
        String customerName = (String) customerComboBox.getSelectedItem();
        String quantity = quantityField.getText();

        if (productName != null && customerName != null && !quantity.isEmpty()) {
            String[] data = {productName, customerName, quantity};
            tableModel.addRow(data);
            quantityField.setText("");
        } else {
            JOptionPane.showMessageDialog(frame, "Please fill in all fields", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void loadCustomerNames(JComboBox<String> customerComboBox) {
        List<Customer> customerNames = customerDao.getAllCustomer();
        for (Customer name : customerNames) {
            customerComboBox.addItem(name.getId()+" "+name.getName());
        }
    }

    public void loadProductNames(JComboBox<String> productComboBox) {
        List<Product> productNames = productDao.getAll();
        for (Product name : productNames) {
            productComboBox.addItem(name.getId()+" "+name.getProductName());
        }
    }
}