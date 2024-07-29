package org.ImtiazSuperMarket.Mapper;

import org.ImtiazSuperMarket.Domain.Product;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductMapper implements IMapper<Product> {
    private final static String ID = "product_id";
    private final static String PRODUCT_CODE = "product_code";
    private final static String PRODUCT_NAME = "product_name";
    private final static String UNIT_IN_STOCK = "unit_in_stock";
    private final static String UNIT_PRICE = "unit_price";



    @Override
    public List<Product> resultSetToList(ResultSet rs) throws SQLException {
        List<Product> productList = new ArrayList<>();
        while (rs.next()){
            Product product = Product.builder()
                    .id(rs.getInt(ID))
                    .productCode(rs.getString(PRODUCT_CODE))
                    .productName(rs.getString(PRODUCT_NAME))
                    .unitInStock(Float.parseFloat(rs.getString(UNIT_IN_STOCK)))
                    .unitPrice(Float.parseFloat(rs.getString(UNIT_PRICE)))
                    .build();
            productList.add(product);
        }

        return productList;
    }

    @Override
    public Product resultSetToObject(ResultSet rs) throws SQLException {
        if (rs.next()) {
            return Product.builder()
                    .id(rs.getInt(ID))
                    .productCode(rs.getString(PRODUCT_CODE))
                    .productName(rs.getString(PRODUCT_NAME))
                    .unitInStock(Float.parseFloat(rs.getString(UNIT_IN_STOCK)))
                    .unitPrice(Float.parseFloat(rs.getString(UNIT_PRICE)))
                    .build();

        }
        return null;
    }
}
