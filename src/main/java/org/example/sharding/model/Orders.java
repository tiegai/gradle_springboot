package org.example.sharding.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Orders {
    private Integer id;
    private Integer orderType;
    private Integer customerId;
    private Double amount;
}
