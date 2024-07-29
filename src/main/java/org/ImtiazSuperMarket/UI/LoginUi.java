package org.ImtiazSuperMarket.UI;

import com.mysql.cj.Session;
import org.ImtiazSuperMarket.Service.AuthenticationService;
import org.ImtiazSuperMarket.Service.UserSessionService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginUi {

public final AuthenticationService authenticationService = new AuthenticationService();

    private UserSessionService userSession;
    public LoginUi() {
        JFrame frame = new JFrame("Login UI");
        this.userSession = UserSessionService.getInstance();
        // Create and set bounds for components
        JLabel usernamelb = new JLabel("Username:");
        usernamelb.setBounds(40, 50, 100, 50);
        frame.add(usernamelb);

        TextField usernametf = new TextField();
        usernametf.setBounds(150, 50, 300, 50);
        frame.add(usernametf);

        JLabel passlb = new JLabel("Password:");
        passlb.setBounds(40, 150, 100, 50);
        frame.add(passlb);

        TextField passtf = new TextField();
        passtf.setBounds(150, 150, 300, 50);
        frame.add(passtf);

        JLabel rolelb = new JLabel("Role:");
        rolelb.setBounds(40, 250, 100, 50);
        frame.add(rolelb);

        JComboBox<String> comboBox = new JComboBox<>();
        comboBox.setBounds(150, 250, 300, 50);
        comboBox.addItem("admin");
        comboBox.addItem("employeeInvoice");
        comboBox.addItem("employeeProduct");
        frame.add(comboBox);

        JButton loginBtn = new JButton("Login");
        loginBtn.setBounds(200, 350, 100, 50);
        frame.add(loginBtn);

        loginBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                String selectedRole = (String) comboBox.getSelectedItem();
                if ("admin".equals(selectedRole) && authenticationService.checkLogin(usernametf.getText(),passtf.getText())) {
                     new AdminFromUi();
                } else if ("employeeInvoice".equals(selectedRole) && authenticationService.checkLogin(usernametf.getText(),passtf.getText())) {
                    new EmployeInvoiceUi();
                } else if ("employeeProduct".equals(selectedRole) && authenticationService.checkLogin(usernametf.getText(),passtf.getText())) {
                    openEmployeeProductForm();
                }
                userSession.setUsername(usernametf.getText());
                userSession.setAuthenticated(true);
            }
        });

        // basic Properties
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setLayout(null); // Setting layout to null to use absolute positioning
            frame.setLocationRelativeTo(null); // Center the frame
        frame.setVisible(true); // Make the frame visible
    }

//    public void openAdminForm()

//    private void openEmployeeInvoiceForm() {
//        JFrame invoiceFrame = new JFrame("Employee Invoice Form");
//        invoiceFrame.setSize(600, 400);
//        invoiceFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//        invoiceFrame.add(new JLabel("Employee Invoice Form"));
//        invoiceFrame.setLocationRelativeTo(null);
//        invoiceFrame.setVisible(true);
//    }

    private void openEmployeeProductForm() {
        JFrame productFrame = new JFrame("Employee Product Form");
        productFrame.setSize(400, 300);
        productFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        productFrame.add(new JLabel("Employee Product Form"));
        productFrame.setLocationRelativeTo(null);
        productFrame.setVisible(true);
    }
}
