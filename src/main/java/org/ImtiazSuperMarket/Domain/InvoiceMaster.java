package org.ImtiazSuperMarket.Domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class InvoiceMaster {
    Integer invoiceMasterId;
    Integer userId;
    Integer customerId;
}
