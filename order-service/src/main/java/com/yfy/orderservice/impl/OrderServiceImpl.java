package com.yfy.orderservice.impl;

import com.yfy.orderservice.dto.request.OrderRequest;
import com.yfy.orderservice.dto.response.InventoryResponse;
import com.yfy.orderservice.model.Order;
import com.yfy.orderservice.model.OrderLineItems;
import com.yfy.orderservice.repository.OrderRepository;
import com.yfy.orderservice.service.OrderServiceIF;
import com.yfy.orderservice.utility.convertion.OrderConvertorImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderServiceImpl implements OrderServiceIF {
    private final OrderRepository orderRepository;
    private final WebClient.Builder webClient;
    @Override
    public void placeOrder(OrderRequest orderRequest) {
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());

        List<OrderLineItems> orderLineItems = orderRequest.getOrderLineItemsDtoList()
                .stream()
                .map(orderLineItemsDto -> OrderConvertorImpl.getInstance().convertDTOToEntity(orderLineItemsDto))
                .toList();

        order.setOrderLineItems(orderLineItems);

        List<String> skuCodes = order.getOrderLineItems()
                .stream()
                .map(OrderLineItems::getSkuCode)
                .toList();

        InventoryResponse[] inventoryResponsesArray = webClient.build().get()
                .uri("http://inventory-service/inventories",
                        uriBuilder -> uriBuilder.queryParam("skuCode", skuCodes).build())
                        .retrieve()
                                .bodyToMono(InventoryResponse[].class)
                                        .block();

        boolean allProductsInStock = Arrays.stream(inventoryResponsesArray)
                .allMatch(InventoryResponse::isInStock);

        if (allProductsInStock){
            orderRepository.save(order);
        }else {
            throw new IllegalArgumentException("Product is not in stock, please try again later");
        }

    }
}
