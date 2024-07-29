package org.ImtiazSuperMarket.Service;

import org.ImtiazSuperMarket.Domain.Invoice;
import org.ImtiazSuperMarket.Domain.Product;
import org.ImtiazSuperMarket.Domain.User;
import org.ImtiazSuperMarket.dao.ICrud;
import org.ImtiazSuperMarket.dao.InvoiceDao;
import org.ImtiazSuperMarket.dao.ProductDao;
import org.ImtiazSuperMarket.dao.UserDoa;

import java.util.List;

public class AdminService {
    ICrud<User> dao = new UserDoa();
    ProductDao productDao = new ProductDao();
    UserDoa userDao = new UserDoa();
    InvoiceDao invoiceDao = new InvoiceDao();
    public String[][] searchUserByName(String name){
        List<User> userList = userDao.getByName(name);

        return this.transformUserToJtable(userList,4);
    }
    public String[][] searchProductByName(String name){
        List<Product> productList = productDao.getByName(name);

        return this.transformProductToJtable(productList,5);
    }
    public String [][] getAllUserforTable() {
        List<User> userList = dao.getAll();
        String[][] data = new String[userList.size()][4];
        for (int i = 0; i < userList.size(); i++) {
            data[i][0] = String.valueOf(userList.get(i).getId());
            data[i][1] = userList.get(i).getUsername();
            data[i][2] = userList.get(i).getPassword();
            data[i][3] = userList.get(i).getEmpType();
        }
//        System.out.println(data);
        return data;
    }
        public String [][] getAllProductforTable() {
            List<Product> productList = productDao.getAll();
            String[][] data = new String[productList.size()][5];
            for (int i = 0; i < productList.size(); i++) {
                data[i][0] = String.valueOf(productList.get(i).getId());
                data[i][2] = productList.get(i).getProductCode();
                data[i][1] = productList.get(i).getProductName();
                data[i][3] = String.valueOf(productList.get(i).getUnitInStock());
                data[i][4] = String.valueOf(productList.get(i).getUnitPrice());
            }
//        System.out.println(data);
            return data;
    }
    public String [][] getProductforTable() {
        List<Product> productList = productDao.getAll();
        String[][] data = new String[productList.size()][5];
        for (int i = 0; i < productList.size(); i++) {
//            data[i][0] = String.valueOf(productList.get(i).getId());
//            data[i][2] = productList.get(i).getProductCode();
            data[i][0] = productList.get(i).getProductName();
            data[i][1] = String.valueOf(productList.get(i).getUnitInStock());
            data[i][2] = String.valueOf(productList.get(i).getUnitPrice());
        }
//        System.out.println(data);
        return data;
    }
    public String [][] getInvoiceDataForTable() {
        List<Invoice> invoiceList = invoiceDao.getAll();
//        List<Product> productList = productDao.getAll();
        String[][] invoiceData = new String[invoiceList.size()][3];

        for (int i = 0; i < invoiceList.size(); i++) {
            invoiceData[i][0] = String.valueOf(invoiceList.get(i).getInvoice_id());
            invoiceData[i][1] = String.valueOf(invoiceList.get(i).getCustomer_id());
            invoiceData[i][2] = String.valueOf(invoiceList.get(i).getUser_id());
//            invoiceData[i][3] = String.valueOf(invoiceList.get(i).getProductName());
//            invoiceData[i][4] = String.valueOf(invoiceList.get(i).getQuantity());

        }
//        System.out.println(data);
        return invoiceData;
    }

    public String [][] getUnitPriceOfProductForTable() {
        List<Product> productList = productDao.getAll();
        String[][] data = new String[productList.size()][5];
        for (int i = 0; i < productList.size(); i++) {
//            data[i][0] = String.valueOf(productList.get(i).getId());
//            data[i][1] = productList.get(i).getProductCode();
//            data[i][2] = productList.get(i).getProductName();
//            data[i][3] = String.valueOf(productList.get(i).getUnitInStock());
            data[i][3] = String.valueOf(productList.get(i).getUnitPrice());
        }
//        System.out.println(data);
        return data;

    }


    public String[][] transformUserToJtable(List<User> userList, int columnSize){
        String[][]  data =  new String[userList.size()][columnSize];
        for (int i = 0; i < userList.size(); i++) {
            data[i][0] = String.valueOf(userList.get(i).getId());
            data[i][1] = userList.get(i).getUsername();
            data[i][2] = userList.get(i).getPassword();
            data[i][3] = userList.get(i).getEmpType();
        }
        return data;
    }
    public String[][] transformProductToJtable(List<Product> productList, int columnSize){
        String[][]  data =  new String[productList.size()][columnSize];
        for (int i = 0; i < productList.size(); i++) {
            data[i][0] = String.valueOf(productList.get(i).getId());
            data[i][1] = productList.get(i).getProductName();
            data[i][2] = productList.get(i).getProductCode();
            data[i][3] = String.valueOf(productList.get(i).getUnitInStock());
            data[i][4] = String.valueOf(productList.get(i).getUnitPrice());
        }
        return data;
    }

    public void save(String userName, String password, String employeeType) {
        User user = User.builder()
                .username(userName)
                .password(password)
                .empType(employeeType)
                .build();
        userDao.insert(user);
    }
    public void save(Integer customerId, Integer userId, String productName, Integer quantity){
        Invoice invoice = Invoice.builder()
                .customer_id(customerId)
                .user_id(userId)
                .productName(productName)
                .quantity(quantity)
                .build();
        invoiceDao.insert(invoice);

    }
    public void save(String productName, String productCode, String unitInStock,String unitPrice) {
        Product product  = Product.builder()
                .productName(productName)
                .productCode(productCode)
                .unitInStock(Float.parseFloat(unitInStock))
                .unitPrice(Float.parseFloat(unitPrice))
                .build();
        productDao.insert(product);
    }

    public void updateUser(long id, String userName, String password, String employeeType) {
        User user = User.builder()
                .username(userName)
                .password(password)
                .empType(employeeType)
                .build();
        userDao.update(user,id);
    }

    // Other methods...
    public void deleteUser(int userId) {
        userDao.delteById(userId);
    }

    public void updateProduct(Integer productId, String productName, String productCode, String unitInStock, String unitPrice) {
        Product product = Product.builder()
//                .id(productId)
                .productName(productName)
                .productCode(productCode)
                .unitInStock(Float.parseFloat(unitInStock))
                .unitPrice(Float.parseFloat(unitPrice))
                .build();
        productDao.update(product,productId);
    }
    public void deleteProduct(int userId) {
        productDao.delteById(userId);
    }

}
