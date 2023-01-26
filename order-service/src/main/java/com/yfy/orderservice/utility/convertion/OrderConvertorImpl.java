package com.yfy.orderservice.utility.convertion;

import com.yfy.orderservice.dto.OrderLineItemsDto;
import com.yfy.orderservice.model.OrderLineItems;

public class OrderConvertorImpl implements OrderConvertorIF{
    private OrderConvertorImpl(){}
    private final static OrderConvertorImpl instance = new OrderConvertorImpl();
    public static OrderConvertorImpl getInstance(){
        return instance;
    }
    @Override
    public OrderLineItems convertDTOToEntity(OrderLineItemsDto orderLineItemsDto) {
        OrderLineItems orderLineItems = new OrderLineItems();
        orderLineItems.setPrice(orderLineItemsDto.getPrice());
        orderLineItems.setSkuCode(orderLineItemsDto.getSkuCode());
        orderLineItems.setQuantity(orderLineItemsDto.getQuantity());
        return orderLineItems;
    }
}
