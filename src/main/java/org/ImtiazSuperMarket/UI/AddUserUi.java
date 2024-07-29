package org.ImtiazSuperMarket.UI;

import org.ImtiazSuperMarket.Service.AdminService;

import javax.swing.*;
import java.awt.*;

public class AddUserUi {
 private final static AdminService adminService = new AdminService();
   public AddUserUi(){
       JFrame frame = new JFrame("Add User From");
       frame.setLayout(new GridLayout( 5,2,10,10));


       JLabel usernamelb = new JLabel("Username:");
//       usernamelb.setBounds(40, 50, 100, 50);
       frame.add(usernamelb);

       TextField usernametf = new TextField(20);
       frame.add(usernametf);

       JLabel passlb = new JLabel("Password:");
       frame.add(passlb);

       TextField passtf = new TextField(20);
       frame.add(passtf);

       JLabel emptypelb = new JLabel("Employee Type");
//       emptypelb.setBounds(40, 250, 100, 50);
       frame.add(emptypelb);

       JComboBox<String> empType = new JComboBox<>();
//       empType.setBounds(150, 250, 300, 50);
       empType.addItem("admin");
       empType.addItem("employeeInvoice");
       empType.addItem("employeeProduct");
       frame.add(empType);



       TextField emptypetf = new TextField(20);
//       frame.add(emptypetf);


       JButton saveBtn = new JButton("Save");
       frame.add(saveBtn);

       saveBtn.addActionListener(s->{
           try {
               String selectedRole = (String) empType.getSelectedItem();

               adminService.save(usernametf.getText(),
                       passtf.getText(), selectedRole);
               frame.dispose();
               new EmployeeRecordUi();
           }
           catch (Exception e){
               JOptionPane.showMessageDialog(frame,"Unable to save");
           }
       });

       JButton backBtn = new JButton("Back");
       frame.add(backBtn);

       backBtn.addActionListener(b->{
           frame.dispose();
           new EmployeeRecordUi();
       });


       frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
       frame.setSize(500, 500);
//       frame.setLayout(null); // Setting layout to null to use absolute positioning
       frame.setLocationRelativeTo(null); // Center the frame
       frame.setVisible(true); // Make the frame visible
    }
}
