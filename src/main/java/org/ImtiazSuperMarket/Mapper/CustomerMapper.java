package org.ImtiazSuperMarket.Mapper;

import org.ImtiazSuperMarket.Domain.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerMapper implements IMapper<Customer>{
    private final static String ID = "customer_id";

    private final static String NAME = "name";
    private final static String ADDRESS = "contact_address";


    @Override
    public List<Customer> resultSetToList(ResultSet rs) throws SQLException {
        List<Customer> customerList = new ArrayList<>();
        while (rs.next()){
            Customer customer = Customer.builder()
                    .id(rs.getInt(ID))
                    .name(rs.getString(NAME))
                    .address(rs.getString(ADDRESS))
                    .build();
            customerList.add(customer);
        }

        return customerList;
    }

    @Override
    public Customer resultSetToObject(ResultSet rs) throws SQLException {
        if(rs.next()){
            return Customer.builder()
                    .id(rs.getInt(ID))
                    .name(rs.getString(NAME))
                    .address(rs.getString(ADDRESS))
                    .build();
        }
        return null;
    }
}
