package org.ImtiazSuperMarket.UI;

import org.ImtiazSuperMarket.Service.AdminService;

import javax.swing.*;
import java.awt.*;

public class AddProducUi {
    private final static AdminService adminService = new AdminService();

    AddProducUi(){
        JFrame frame = new JFrame("Add Product From");

        frame.setLayout(new GridLayout( 5,2,10,10));

        JLabel productNamelb = new JLabel("Produce Name");
//       usernamelb.setBounds(40, 50, 100, 50);
        frame.add(productNamelb);

        TextField productNametf = new TextField(20);
        frame.add(productNametf);

        JLabel productCodelb = new JLabel("Product Code");
        frame.add(productCodelb);

        TextField productCodetf = new TextField(20);
        frame.add(productCodetf);



        JLabel unitInStocklb = new JLabel("Unit In Stock");
//       emptypelb.setBounds(40, 250, 100, 50);
        frame.add(unitInStocklb);

        TextField unitInStocktf = new TextField(20);
        frame.add(unitInStocktf);

        JLabel unitPricelb = new JLabel("Unit Price");
//       emptypelb.setBounds(40, 250, 100, 50);
        frame.add(unitPricelb);

        TextField unitPricetf = new TextField(20);
        frame.add(unitPricetf);
        JButton saveBtn = new JButton("Save");
        frame.add(saveBtn);

        saveBtn.addActionListener(s->{
            try {
                adminService.save(productNametf.getText(),
                        productCodetf.getText(),
                        unitInStocktf.getText(),
                        unitPricetf.getText());
                frame.dispose();
                new ProductRecordUi();
            }
            catch (Exception e){
                JOptionPane.showMessageDialog(frame,"Unable to save");
            }
        });
        JButton backBtn = new JButton("Back");
        frame.add(backBtn);

        backBtn.addActionListener(b->{
            frame.dispose();
            new ProductRecordUi();
        });
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
//       frame.setLayout(null); // Setting layout to null to use absolute positioning
        frame.setLocationRelativeTo(null); // Center the frame
        frame.setVisible(true); // Make the frame visible

    }


}
