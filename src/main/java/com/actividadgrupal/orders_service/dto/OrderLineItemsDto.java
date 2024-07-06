package com.actividadgrupal.orders_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderLineItemsDto {
    private Long id;
    private Long productId;
    private String orderNumber;
    private Float price;
    private Integer quantity;
}
