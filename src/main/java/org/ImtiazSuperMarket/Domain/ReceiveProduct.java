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
public class ReceiveProduct {
    private Integer receiveProductId;
    private Integer productId;
    private Integer quantity;
    private Date receiveDate;
}
