package org.ImtiazSuperMarket.UI;
import org.ImtiazSuperMarket.Service.AdminService;

import javax.swing.*;
import java.awt.*;

public class EditProductUi {
    private final static AdminService adminService = new AdminService();
//    public EditProductUi(){ // First Constructor
//
//        this(0,"","","","");
//    }
    public EditProductUi(Integer productId, String productName,
                         String productCode, String unitInStock, String unitPrice) { //Second
        JFrame frame = new JFrame("Edit Product Form");
        frame.setLayout(new GridLayout(5, 2, 10, 10));

        // Product Name
        JLabel productNamelb = new JLabel("Product Name:");
        frame.add(productNamelb);

        TextField productNametf = new TextField(20);
        productNametf.setText(productName);
        frame.add(productNametf);

        // Product Code
        JLabel productCodelb = new JLabel("Product Code:");
        frame.add(productCodelb);

        TextField productCodetf = new TextField(20);
        productCodetf.setText(productCode);
        frame.add(productCodetf);

        // Unit In Stock
        JLabel unitInStocklb = new JLabel("Unit In Stock:");
        frame.add(unitInStocklb);

        TextField unitInStocktf = new TextField(20);
        unitInStocktf.setText(unitInStock);
        frame.add(unitInStocktf);

        // Unit Price
        JLabel unitPricelb = new JLabel("Unit Price:");
        frame.add(unitPricelb);

        TextField unitPricetf = new TextField(20);
        unitPricetf.setText(unitPrice);
        frame.add(unitPricetf);

        // Save Button
        JButton saveBtn = new JButton("Save");
        frame.add(saveBtn);
        saveBtn.addActionListener(s -> {
            try {
                adminService.updateProduct(productId, productNametf.getText(), productCodetf.getText(), unitInStocktf.getText(), unitPricetf.getText());
                frame.dispose();
                new ProductRecordUi();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(frame, "Unable to update product.");
            }
        });

        // Back Button
        JButton backBtn = new JButton("Back");
        frame.add(backBtn);
        backBtn.addActionListener(b -> {
            frame.dispose();
            new ProductRecordUi();
        });

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
