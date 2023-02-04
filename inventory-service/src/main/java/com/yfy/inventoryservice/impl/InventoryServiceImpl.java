package com.yfy.inventoryservice.impl;

import com.yfy.inventoryservice.dto.response.InventoryResponse;
import com.yfy.inventoryservice.repository.InventoryRepository;
import com.yfy.inventoryservice.service.InventoryServiceIF;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
@Slf4j
public class InventoryServiceImpl implements InventoryServiceIF {
    private final InventoryRepository inventoryRepository;

    @Transactional(readOnly = true)
    @SneakyThrows
    @Override
    public List<InventoryResponse> isInStock(List<String> skuCode) {
        log.info("Wait Started");
        Thread.sleep(10000);
        log.info("Wait Ended");
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
