package com.yfy.orderservice.utility.convertion;

import com.yfy.orderservice.dto.OrderLineItemsDto;
import com.yfy.orderservice.model.OrderLineItems;

public interface OrderConvertorIF extends BeanConvertorIF{
    OrderLineItems convertDTOToEntity(OrderLineItemsDto orderLineItemsDto);

}
