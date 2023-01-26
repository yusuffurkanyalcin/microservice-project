package com.yfy.productservice.service;

import com.yfy.productservice.dto.request.ProductRequest;
import com.yfy.productservice.dto.response.ProductResponse;

import java.util.List;

public interface ProductServiceIF {
    void create(ProductRequest productRequest);
    List<ProductResponse> getAll();
}
