package com.yfy.productservice.impl;

import com.yfy.productservice.dto.request.ProductRequest;
import com.yfy.productservice.dto.response.ProductResponse;
import com.yfy.productservice.helper.convertion.ProductConvertorImpl;
import com.yfy.productservice.model.Product;
import com.yfy.productservice.repository.ProductRepository;
import com.yfy.productservice.service.ProductServiceIF;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductServiceIF {
    private final ProductRepository productRepository;

    @Override
    public void create(ProductRequest productRequest) {
        Product product = ProductConvertorImpl.getInstance().convertDTOToEntity(productRequest);
        productRepository.save(product);
        log.info("Product is saved: "+ product.getId());
    }

    @Override
    public List<ProductResponse> getAll() {
        List<Product> products = productRepository.findAll();
        return products.stream()
                .map(product -> ProductConvertorImpl.getInstance().convertEntityToDTO(product))
                .toList();
    }
}

