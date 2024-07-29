package org.ImtiazSuperMarket.Domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    private Integer id;
    private String productCode;
    private String productName;
    private float unitInStock;
    private float unitPrice;
}
