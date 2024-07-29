package org.ImtiazSuperMarket.Service;

import org.ImtiazSuperMarket.Domain.Invoice;
import org.ImtiazSuperMarket.dao.InvoiceDao;

import java.util.Date;

public class EmployeeInvoiceService {

    private final InvoiceDao invoiceDao = new InvoiceDao();

    public void generateInvoice(int customerId, int userId, float subTotal, String paymentType) {
        Invoice invoice = Invoice.builder()
                .customer_id(customerId)
                .user_id(userId)
//                .sub_total(subTotal)
//                .payment_type(paymentType)
//                .date_recorded(new Date(System.currentTimeMillis()))
                .build();
        invoiceDao.insert(invoice);
    }
}
