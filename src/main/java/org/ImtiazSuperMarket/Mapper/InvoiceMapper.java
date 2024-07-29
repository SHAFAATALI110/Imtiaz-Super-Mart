package org.ImtiazSuperMarket.Mapper;

import org.ImtiazSuperMarket.Domain.Invoice;
import org.ImtiazSuperMarket.Domain.Product;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InvoiceMapper implements IMapper<Invoice>{
    private final static String INVOICE_ID = "invoice_id";

    private final static String CUSTOMER_ID = "customer_id";
    private final static String USER_ID = "user_id";
    private final static String PRODUCT_NAME = "product_name";
    private final static String QUANTITY = "quantity";
    @Override
    public List<Invoice> resultSetToList(ResultSet rs) throws SQLException {


        List<Invoice> invoiceList = new ArrayList<>();
        while (rs.next()){
            Invoice invoice = Invoice.builder()
                    .invoice_id(rs.getInt(INVOICE_ID))
                    .customer_id(rs.getInt(CUSTOMER_ID))
                    .user_id(rs.getInt(USER_ID))
//                    .productName(rs.getString(PRODUCT_NAME))
//                    .quantity(rs.getInt(QUANTITY))
                    .build();
            invoiceList.add(invoice);
        }
        return invoiceList;
        }

    @Override
    public Invoice resultSetToObject(ResultSet rs) throws SQLException {
        return null;
    }
}
