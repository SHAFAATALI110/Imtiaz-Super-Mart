package org.ImtiazSuperMarket.Domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Sales {
    private Integer salesId;
    private Integer productId;
    private Integer quantity;
    private float unitPrice;
}
