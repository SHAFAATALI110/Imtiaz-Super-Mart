package org.ImtiazSuperMarket.Service;

import org.ImtiazSuperMarket.Domain.InvoiceDetail;
import org.ImtiazSuperMarket.Domain.InvoiceMaster;
import org.ImtiazSuperMarket.Domain.User;
import org.ImtiazSuperMarket.dao.InvoiceDetailDao;
import org.ImtiazSuperMarket.dao.InvoiceMasterDao;

public class InvoiceMasterServices {
    private  final InvoiceMasterDao invoiceMasterDao = new InvoiceMasterDao();
    private  final InvoiceDetailDao invoiceDetailDao = new InvoiceDetailDao();



    public void save(Integer customerId, Integer userId) {
        InvoiceMaster invoiceMaster = InvoiceMaster.builder()
                .customerId(customerId)
                .userId(userId)
                .build();
        invoiceMasterDao.insert(invoiceMaster);

    }
    public void saveInvoiceDetail(String productName,Integer quantity) {
        InvoiceDetail invoiceDetail = InvoiceDetail.builder()
                .ProductName(productName)
                .Quantity(quantity)
                .build();
        invoiceDetailDao.insert(invoiceDetail);

    }
}
