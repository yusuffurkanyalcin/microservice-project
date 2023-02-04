package com.yfy.orderservice.service;

import com.yfy.orderservice.dto.request.OrderRequest;

public interface OrderServiceIF {
    String placeOrder(OrderRequest orderRequest);
}
