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
public class Invoice {
    private Integer invoice_id;
    private Integer customer_id;
    private Integer user_id;
    private String productName;
    private Integer quantity;
//    private Date date_recorded;

}
