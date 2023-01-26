package com.yfy.inventoryservice.service;

import com.yfy.inventoryservice.dto.response.InventoryResponse;

import java.util.List;

public interface InventoryServiceIF {

    List<InventoryResponse> isInStock(List<String> skuCode);
}
