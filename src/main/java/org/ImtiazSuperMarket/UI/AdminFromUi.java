package org.ImtiazSuperMarket.UI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminFromUi {
    AdminFromUi() {
        JFrame adminFrame = new JFrame("Admin Form");
        adminFrame.setSize(600, 400);
        adminFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JButton employeeRecordBtn = new JButton("Employee Record");
        employeeRecordBtn.setBounds(50, 50, 150, 50);
        adminFrame.add(employeeRecordBtn);

        employeeRecordBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adminFrame.dispose();

                new EmployeeRecordUi();
            }
        });

        JButton productDetailBtn = new JButton("Product Records");
        productDetailBtn.setBounds(50, 120, 150, 50);
        productDetailBtn.addActionListener(p -> {
        adminFrame.dispose();
        new ProductRecordUi();
        });
        adminFrame.add(productDetailBtn);
        productDetailBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adminFrame.dispose();
//                new ProductRecordUi();
            }
        });

        JButton supplierDetailBtn = new JButton("Supplier Detail");
        supplierDetailBtn.setBounds(50, 190, 150, 50);
        adminFrame.add(supplierDetailBtn);
        JButton logoutBtn = new JButton("Log out");
        logoutBtn.setBounds(50, 260, 150, 50);
        logoutBtn.addActionListener(e -> {
            adminFrame.dispose();
            new LoginUi();
        });
        adminFrame.add(logoutBtn);


        adminFrame.add(new JLabel());
        adminFrame.setLocationRelativeTo(null);
        adminFrame.setVisible(true);
    }
}
