package org.ImtiazSuperMarket.Domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Supplier {
    private Integer supplier;
    private String supplierName;
    private String contact;


}
