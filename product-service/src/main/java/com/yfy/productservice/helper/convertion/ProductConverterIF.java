package com.yfy.productservice.helper.convertion;

import com.yfy.productservice.dto.request.ProductRequest;
import com.yfy.productservice.dto.response.ProductResponse;
import com.yfy.productservice.model.Product;

public interface ProductConverterIF extends BeanConvertorIF{
    Product convertDTOToEntity(ProductRequest from);
    ProductResponse convertEntityToDTO(Product from);
}
