package org.ImtiazSuperMarket.UI;
import org.ImtiazSuperMarket.Service.AdminService;

import javax.swing.*;
import java.awt.*;

public class EditUserUi {
    private final static AdminService adminService = new AdminService();

    public EditUserUi(long id,String username, String password, String empType) {
        JFrame frame = new JFrame("Edit User Form");
        frame.setLayout(new GridLayout(4, 2, 10, 10));

        JLabel usernamelb = new JLabel("Username:");
        frame.add(usernamelb);

        TextField usernametf = new TextField(20);
        usernametf.setText(username);
        frame.add(usernametf);

        JLabel passlb = new JLabel("Password:");
        frame.add(passlb);

        TextField passtf = new TextField(20);
        passtf.setText(password);
        frame.add(passtf);

        JLabel emptypelb = new JLabel("Employee Type:");
        frame.add(emptypelb);

        TextField emptypetf = new TextField(20);
        emptypetf.setText(empType);
        frame.add(emptypetf);

        JButton saveBtn = new JButton("Save");
        frame.add(saveBtn);

        saveBtn.addActionListener(s -> {
            try {
                adminService.updateUser(id,usernametf.getText(), passtf.getText(), emptypetf.getText());
                frame.dispose();
                new EmployeeRecordUi();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(frame, "Unable to update user.");
            }
        });

        JButton backBtn = new JButton("Back");
        frame.add(backBtn);

        backBtn.addActionListener(b -> {
            frame.dispose();
            new EmployeeRecordUi();
        });

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}