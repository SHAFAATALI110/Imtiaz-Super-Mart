package org.ImtiazSuperMarket.dao;

import org.ImtiazSuperMarket.Domain.Customer;
import org.ImtiazSuperMarket.Mapper.CustomerMapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static org.ImtiazSuperMarket.dao.SqlQueryConstant.*;

public class CustomerDao extends BaseDao implements ICrud<Customer> {
   private final CustomerMapper customerMapper = new CustomerMapper();
    @Override
    public void insert(Customer obj) {
        try {
            PreparedStatement ps = connection.prepareStatement(INSERT_INTO_CUSTOMER);
            ps.setString(1, obj.getName());
            ps.setString(2, obj.getAddress());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<Customer> getAll() {
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(GET_ALL_CUSTOMER);
            return customerMapper.resultSetToList(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Customer getById(Integer id) {
        try{
            PreparedStatement stmt = connection.prepareStatement(GET_BY_ID);
            stmt.setInt(1,id.intValue());
            ResultSet rs = stmt.executeQuery();
            return customerMapper.resultSetToObject(rs);
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void update(Customer obj, long id) {
        try {
            PreparedStatement ps = connection.prepareStatement(UPDATE_BY_ID);
            ps.setString(1, obj.getName());
            ps.setString(2, obj.getAddress());
            ps.setInt(3, (int) id);
            ps.executeUpdate();
            if(ps.executeUpdate() > 0) {
                System.out.println("OK");
            } else{
                System.out.println("NOT OK");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delteById(Integer id) {
        try {
            PreparedStatement ps = connection.prepareStatement(DELETE_BY_ID);
            ps.setInt(1, id.intValue());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public List<Customer> getAllCustomer() {
        List<Customer> customerNames = new ArrayList<>();
        try (Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery("select * from customer")) {
            while (rs.next()) {
                customerNames.add(Customer.builder()
                        .id(rs.getInt("customer_id"))
                        .name(rs.getString("name"))
                        .build());
            }
            return customerNames;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return customerNames;
    }
}
