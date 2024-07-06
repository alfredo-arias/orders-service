package com.actividadgrupal.orders_service.mapper;

import com.actividadgrupal.orders_service.dto.OrderLineItemsDto;
import com.actividadgrupal.orders_service.entity.OrderLineItems;

public class OrderLineItemsMapper {
    public static OrderLineItems mapToDto(OrderLineItemsDto orderLineItemsDto) {
        OrderLineItems orderLineItem = new OrderLineItems();
        orderLineItem.setId(orderLineItemsDto.getId());
        orderLineItem.setProductId(orderLineItemsDto.getProductId());
        orderLineItem.setQuantity(orderLineItemsDto.getQuantity());
        orderLineItem.setPrice(orderLineItemsDto.getPrice());

        return orderLineItem;
    }
}
