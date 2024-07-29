package org.ImtiazSuperMarket.dao;

import org.ImtiazSuperMarket.Domain.Invoice;
import org.ImtiazSuperMarket.Mapper.InvoiceMapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static org.ImtiazSuperMarket.dao.SqlQueryConstant.*;

public class InvoiceDao extends BaseDao implements ICrud<Invoice> {
    private final static InvoiceMapper invoiceMapper = new InvoiceMapper();

//    @Override
//    public void insert(Invoice obj) {
//        try {
//            PreparedStatement ps = connection.prepareStatement("INSERT INTO invoice (customer_id, user_id, product_name,quantity) VALUES (?, ?, ?, ?)");
//            ps.setInt(1, obj.getCustomer_id());
//            ps.setInt(2, obj.getUser_id());
//            ps.setString(3, obj.getProductName());
//            ps.setInt(4, obj.getQuantity());
////            ps.setDate(5, new java.sql.Date(obj.getDate_recorded().getTime()));
//            ps.executeUpdate();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }


    @Override
    public List<Invoice> getAll() {
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(GET_ALL_INVOICE_MASTER_DATA);
            return invoiceMapper.resultSetToList(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void insert(Invoice obj) {

    }

//    @Override
//    public List<Invoice> getAll() {
//        return null;
//    }

    @Override
    public Invoice getById(Integer id) {
        return null;
    }

    @Override
    public void update(Invoice obj, long id) {
    }

    @Override
    public void delteById(Integer id) {
    }
}
