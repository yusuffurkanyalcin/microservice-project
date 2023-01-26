package com.yfy.inventoryservice.impl;

import com.yfy.inventoryservice.dto.response.InventoryResponse;
import com.yfy.inventoryservice.repository.InventoryRepository;
import com.yfy.inventoryservice.service.InventoryServiceIF;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryServiceIF {
    private final InventoryRepository inventoryRepository;

    @Transactional(readOnly = true)
    @Override
    public List<InventoryResponse> isInStock(List<String> skuCode) {
        return inventoryRepository.findBySkuCodeIn(skuCode)
                .stream()
                .map(inventory ->
                    InventoryResponse.builder()
                            .isInStock(inventory.getQuantity() > 0)
                            .skuCode(inventory.getSkuCode())
                            .build()
                ).toList();
    }
}
