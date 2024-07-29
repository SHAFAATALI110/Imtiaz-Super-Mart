package org.ImtiazSuperMarket.UI;

import org.ImtiazSuperMarket.Service.AdminService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class EmployeeRecordUi {
   private final AdminService adminService = new AdminService();
//EmployeeRecordUi employeeRecordUi = new EmployeeRecordUi();
    public EmployeeRecordUi(){

        JFrame frame = new JFrame("Employee Detail Form");
        JPanel tblAndSearchPanel = new JPanel();
        tblAndSearchPanel.setBackground(Color.gray);
        tblAndSearchPanel.setLayout(new BorderLayout(10, 10));
//        tblAndSearchPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10,10));
        JTextField seachTf = new JTextField(30);

        tblAndSearchPanel.add(seachTf, BorderLayout.NORTH);


        String[][] data = adminService.getAllUserforTable();
        String[] column = {"ID","User Name","Password","Employee Type"};


        DefaultTableModel dtm = new DefaultTableModel(data,column);
        JTable jTable = new JTable(dtm);
        jTable.setBounds(30,40,200,300);

        JScrollPane sp = new JScrollPane(jTable);
//        sp.setSize(12,3);
//        sp.setVisible(true);
        tblAndSearchPanel.add(sp,BorderLayout.CENTER);

        JPanel actionButtonsPanals = new JPanel();
        actionButtonsPanals.setLayout(new FlowLayout(FlowLayout.LEFT ,10,10));

        JButton add = new JButton("Add");
        add.addActionListener(ad->{
            frame.dispose();
                new AddUserUi();
                });

        JButton edit = new JButton("Edit");
        edit.addActionListener(e -> {
            int selectedRow = jTable.getSelectedRow();
            if (selectedRow != -1) {
                long userId = Integer.parseInt((String) jTable.getValueAt(selectedRow, 0));
                String username = (String) jTable.getValueAt(selectedRow, 1);
                String password = (String) jTable.getValueAt(selectedRow, 2);
                String empType = (String) jTable.getValueAt(selectedRow, 3);

                frame.dispose();
                new EditUserUi(userId, username, password, empType);
            } else {
                JOptionPane.showMessageDialog(frame, "Please select a user to edit.");
            }
        });


        JButton delete = new JButton("Delete");
        delete.addActionListener(e->{
            int selectedRow = jTable.getSelectedRow();
            if (selectedRow != -1) {
                int userId = Integer.parseInt((String) jTable.getValueAt(selectedRow, 0));
                int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this user?", "Delete Confirmation", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    adminService.deleteUser(userId);
                    dtm.removeRow(selectedRow);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Please select a user to delete.");
            }
        });

        JButton back = new JButton("Back");
        back.addActionListener(b->{
        frame.dispose();
        new AdminFromUi();
//            LoginUi loginUi = new LoginUi();
//            loginUi.openAdminForm();
//            frame.dispose();
        });
        actionButtonsPanals.add(add);
        actionButtonsPanals.add(delete);
        actionButtonsPanals.add(edit);
        actionButtonsPanals.add(back);

        seachTf.addKeyListener(new KeyListener() {
            //            StringBuilder sb = new StringBuilder();
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
//            sb.append(seachTf.getText());
            }

            @Override
            public void keyReleased(KeyEvent e) {
//                System.out.println(seachTf.getText());
                String[][] data = adminService.searchUserByName(seachTf.getText());
                DefaultTableModel dtm = new DefaultTableModel(data,column);
                jTable.setModel(dtm);
            }
        });


        frame.setLayout(new GridLayout(1,2,150,5));
        frame.add(tblAndSearchPanel);

        frame.add(actionButtonsPanals);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setSize(1000, 300);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    }
//    private void deleteUser(ActionEvent e) {
//        int selectedRow =
//        if (selectedRow != -1) {
//            int userId = Integer.parseInt((String) jTable.getValueAt(selectedRow, 0));
//            int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this user?", "Delete Confirmation", JOptionPane.YES_NO_OPTION);
//            if (confirm == JOptionPane.YES_OPTION) {
//                adminService.deleteUser(userId);
//                dtm.removeRow(selectedRow);
//            }
//        } else {
//            JOptionPane.showMessageDialog(null, "Please select a user to delete.");
//        }
//    }

}
