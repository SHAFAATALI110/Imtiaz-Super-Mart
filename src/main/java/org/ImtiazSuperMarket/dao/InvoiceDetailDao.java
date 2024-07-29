package org.ImtiazSuperMarket.dao;

import org.ImtiazSuperMarket.Domain.InvoiceDetail;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import static org.ImtiazSuperMarket.dao.SqlQueryConstant.INSERT_INTO_INVOICE_DETAIL;
import static org.ImtiazSuperMarket.dao.SqlQueryConstant.INSERT_INTO_INVOICE_MASTER;

public class InvoiceDetailDao extends BaseDao implements ICrud<InvoiceDetail>{
    @Override
    public void insert(InvoiceDetail obj) {
        try {
            PreparedStatement ps = connection.prepareStatement(INSERT_INTO_INVOICE_DETAIL);
            ps.setString(1, obj.getProductName());
            ps.setInt(2, obj.getQuantity());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<InvoiceDetail> getAll() {
        return null;
    }

    @Override
    public InvoiceDetail getById(Integer id) {
        return null;
    }

    @Override
    public void update(InvoiceDetail obj, long id) {

    }

    @Override
    public void delteById(Integer id) {

    }
}
