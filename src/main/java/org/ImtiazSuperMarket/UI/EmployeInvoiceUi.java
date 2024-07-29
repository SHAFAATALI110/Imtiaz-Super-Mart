package org.ImtiazSuperMarket.UI;

import com.itextpdf.text.DocumentException;
import org.ImtiazSuperMarket.Domain.Customer;
import org.ImtiazSuperMarket.Domain.User;
import org.ImtiazSuperMarket.Service.AdminService;
import org.ImtiazSuperMarket.Service.EmployeeInvoiceService;
import org.ImtiazSuperMarket.Service.UserSessionService;
import org.ImtiazSuperMarket.dao.CustomerDao;
import org.ImtiazSuperMarket.dao.ProductDao;
import org.ImtiazSuperMarket.dao.UserDoa;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.IOException;
import java.util.List;

public class EmployeInvoiceUi {
     final User user = new User();
     final UserDoa userDoa = new UserDoa();
    private final CustomerDao customerDao = new CustomerDao();
    final ProductDao productDao = new ProductDao();
    final UserDoa userDao = new UserDoa();
    private final AdminService adminService = new AdminService();
    private final EmployeeInvoiceService employeeInvoiceService = new EmployeeInvoiceService();
    private DefaultTableModel tableModel;
    private DefaultTableModel invoicetableModel;

    private DefaultTableModel tableModel1;

    private JTable productTable;
    private JTable invoiceTable;

    private  JTable productInvoiceTbl;
    private JComboBox<String> customerComboBox;
    public void addInvoiceRow(String customerName, String userName, String productName, int quantity, float unitPrice, float totalPrice) {
        String[] rowData = {null, customerName, userName, productName, String.valueOf(quantity), String.valueOf(unitPrice), String.valueOf(totalPrice)};
        invoicetableModel.addRow(rowData);
    }

    public EmployeInvoiceUi() {
        JFrame frame = new JFrame("Employee Invoice Form");
        frame.setLayout(new BorderLayout(10, 10));

        // Left panel for product table
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BorderLayout());
        String[][] data = adminService.getProductforTable();

        String[] columnNames = {"Product Name", "Units in stock", "Unit Price"};
        tableModel = new DefaultTableModel(data, columnNames);

        productTable = new JTable(tableModel);
        productTable.setBounds(30, 40, 200, 300);

        JScrollPane scrollPane = new JScrollPane(productTable);
        leftPanel.add(scrollPane, BorderLayout.NORTH);

        //Invoice Table
        String[][] invoiceData = adminService.getInvoiceDataForTable();

        String[] invoiceColumnNames = {"Invoice id","Customer Name", "User Name","Product Name", "Quantity","Unit Price"};
        invoicetableModel = new DefaultTableModel(invoiceData, invoiceColumnNames);

        invoiceTable = new JTable(invoicetableModel);
        invoiceTable.setBounds(30, 40, 200, 300);

        JScrollPane invoiceScrollPane = new JScrollPane(invoiceTable);
        leftPanel.add(invoiceScrollPane, BorderLayout.WEST);


        // Right panel for buttons and customer selection
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new GridLayout(4, 2, 5, 5));

        JButton addProductButton = new JButton("Add Order");
//        addProductButton.setBounds(10,10,10,100);

        JButton deleteProductButton = new JButton("Delete Product");
        deleteProductButton.addActionListener(e -> {
            try {
                deleteSelectedProduct();

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Unable to delete");
            }
        });

//        JLabel customerLabel = new JLabel("Customer Name:");
//        JComboBox<String> customerComboBox = new JComboBox<>();
//        loadCustomerNames(customerComboBox);

        rightPanel.add(addProductButton);
        rightPanel.add(deleteProductButton);
//        rightPanel.add(customerLabel);
//        rightPanel.add(customerComboBox);

        JButton generateInvoiceButton = new JButton("Generate Invoice");
        generateInvoiceButton.addActionListener(e -> {
            new PdfGeneratorUi();
        });
//        generateInvoiceButton.setBounds(100,10,100,100);
        rightPanel.add(generateInvoiceButton);

        JButton backbtn = new JButton("Log out");
        rightPanel.add(backbtn);
        backbtn.addActionListener(b -> {

            UserSessionService session = UserSessionService.getInstance();
            session.setAuthenticated(false);
            session.setUsername(null);

            frame.dispose();
            new LoginUi();
        });
//        new AddProductForm(this)
        addProductButton.addActionListener(e -> new AddOrderUi());
        // Add action listeners
//        addProductButton.addActionListener(e -> new OpenAddProductFormUi());
//        tableModel = new DefaultTableModel(data, columnNames);
//        productInvoiceTbl = new JTable(tableModel);
//        JScrollPane invoiceScrollPane = new JScrollPane(productInvoiceTbl);
//        leftPanel.add(invoiceScrollPane, BorderLayout.WEST);
        // Add panels to the frame
        frame.add(leftPanel, BorderLayout.CENTER);
        frame.add(rightPanel, BorderLayout.EAST);
//        frame.add(invoiceData,BorderLayout.SOUTH);

        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
}


public String[][] invoiceTableData(String productName,Integer quantity,float unitPrice,float totalPrice){

//    JFrame invoiceDataFrame = new JFrame();

//    JPanel panel = new JPanel(new GridLayout(1, 1, 10, 10));
    String[] rowData = {productName, String.valueOf(quantity), String.valueOf(unitPrice), String.valueOf(totalPrice)};

    // Convert rowData into a 2D array for DefaultTableModel
    String[][] data = {rowData};
//    String[] columnNames = {"Product Name", "Quantity", "Unit Price", "Total Price"};
//
//    tableModel = new DefaultTableModel(data, columnNames);
//        productInvoiceTbl = new JTable(tableModel);
//        JScrollPane invoiceScrollPane = new JScrollPane(productInvoiceTbl);
//        panel.add(invoiceScrollPane, BorderLayout.WEST);
//        panel.add(productInvoiceTbl);
//        invoiceDataFrame.add(panel);
return data;
}

    public void loadCustomerNames(JComboBox<String> customerComboBox) {
        List<Customer> customerNames = customerDao.getAllCustomer();
        for (Customer name : customerNames) {
            customerComboBox.addItem(name.getName());
        }
    }


    private void deleteSelectedProduct() {
        int selectedRow = productTable.getSelectedRow();
        if (selectedRow >= 0) {
            tableModel.removeRow(selectedRow);
        }
    }

    private void generateInvoice(JFrame frame) {
        int customerId = customerComboBox.getSelectedIndex() + 1;
        int userId = 1; // Example user ID
        float subTotal = calculateSubTotal();
        String paymentType = "Example Payment Type";

        String fileName = "Invoice_" + customerId + ".pdf";
        try {
            PdfGeneratorUi.generateInvoice(fileName, customerId, userId, subTotal, paymentType);
            employeeInvoiceService.generateInvoice(customerId, userId, subTotal, paymentType);
            JOptionPane.showMessageDialog(frame, "Invoice generated successfully!");
        } catch (DocumentException | IOException ex) {
            JOptionPane.showMessageDialog(frame, "Error generating invoice: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    private float calculateSubTotal() {
        float subTotal = 0;
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            int quantity = Integer.parseInt((String) tableModel.getValueAt(i, 1));
            // Assuming price is 10 for all products for simplicity, replace with actual price retrieval
            subTotal += quantity * 10;
        }
        return subTotal;
    }
}


