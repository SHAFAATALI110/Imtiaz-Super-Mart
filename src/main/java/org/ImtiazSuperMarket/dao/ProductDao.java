package org.ImtiazSuperMarket.dao;

import org.ImtiazSuperMarket.Domain.Product;
import org.ImtiazSuperMarket.Mapper.ProductMapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static org.ImtiazSuperMarket.dao.SqlQueryConstant.*;

public class ProductDao extends BaseDao implements ICrud<Product> {
    private final static ProductMapper productMapper = new ProductMapper();
    private final static Product product = new Product();

    public List<Product> getByName(String name) {
        try {
            PreparedStatement stmt = connection.prepareStatement("select * from product where product_name  like '%" + name + "%' ");
//            stmt.setString(1,name);
            ResultSet rs = stmt.executeQuery();
            return productMapper.resultSetToList(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void insert(Product obj) {
        try {
            PreparedStatement ps = connection.prepareStatement(INSERT_INTO_PRODUCT);
            ps.setString(1, obj.getProductCode());
            ps.setString(2, obj.getProductName());
            ps.setFloat(3, obj.getUnitInStock());
            ps.setFloat(4, obj.getUnitPrice());
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public List<Product> getAll() {
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(GET_ALL_PRODUCT);
            return productMapper.resultSetToList(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public Product getById(Integer Id)  {
//        List<Product> productId = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(GET_PRODUCT_ID_BY_NAME);

//            if (rs.next()) {
//                Product.builder()
//                        .productName(rs.getString(name))
//                        .build();
//            }
//            return productId;
//            return productMapper.resultSetToList(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public Product getProductIdByName(String name) {
//        List<Product> productId = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement(GET_PRODUCT_ID_BY_NAME);
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
//            return productMapper.resultSetToObject(rs);
            if (rs.next()) {
                return Product.builder()
                        .id(rs.getInt("product_id"))
//                        .productName(rs.getString(name))
                        .build();
            }
//            return productId;
//            return productMapper.resultSetToList(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  null;
    }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
        @Override
    public void update(Product obj, long id) {
            try {
                PreparedStatement ps =connection.prepareStatement( "update product set unit_in_stock = ? where product_id = ?");
                ps.setFloat(1,obj.getUnitInStock());
                ps.setInt(2, (int) id);

                ps.executeUpdate();

            } catch (SQLException e) {
                throw new RuntimeException(e);
        }


        }


    @Override
    public void delteById(Integer id) {
        try {
            PreparedStatement ps = connection.prepareStatement(DELETE_PRODUCT_BY_ID);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Product> getAllProductName() {
        List<Product> productNames = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(GET_ALL_PRODUCT_NAME);
            while (rs.next()) {
                productNames.add(
                        Product.builder()
                        .productName(rs.getString("product_name"))

                        .build());
            }
            return productNames;
//            return productMapper.resultSetToList(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productNames;
    }
}