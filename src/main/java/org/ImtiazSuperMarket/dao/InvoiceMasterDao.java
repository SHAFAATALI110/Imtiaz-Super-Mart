package org.ImtiazSuperMarket.dao;

import org.ImtiazSuperMarket.Domain.InvoiceMaster;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import static org.ImtiazSuperMarket.dao.SqlQueryConstant.INSERT_INTO_CUSTOMER;
import static org.ImtiazSuperMarket.dao.SqlQueryConstant.INSERT_INTO_INVOICE_MASTER;

public class InvoiceMasterDao extends   BaseDao implements ICrud<InvoiceMaster>{
    @Override
    public void insert(InvoiceMaster obj) {
        try {
            PreparedStatement ps = connection.prepareStatement(INSERT_INTO_INVOICE_MASTER);
            ps.setInt(1, obj.getCustomerId());
            ps.setInt(2, obj.getUserId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<InvoiceMaster> getAll() {
        return null;
    }

    @Override
    public InvoiceMaster getById(Integer id) {
        return null;
    }

    @Override
    public void update(InvoiceMaster obj, long id) {

    }

    @Override
    public void delteById(Integer id) {

    }
}
