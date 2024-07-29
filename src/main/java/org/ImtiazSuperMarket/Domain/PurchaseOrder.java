package org.ImtiazSuperMarket.Domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseOrder {
    private Integer purchaseOrderId;
    private Integer ProductId;
    private Integer SupplierId;
    private Integer user_id;
    private Integer quantity;
    private float unitPrice;
    private Date orderDate;
}
