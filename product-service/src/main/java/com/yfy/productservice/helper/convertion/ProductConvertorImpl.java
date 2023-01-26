package com.yfy.productservice.helper.convertion;

import com.yfy.productservice.dto.request.ProductRequest;
import com.yfy.productservice.dto.response.ProductResponse;
import com.yfy.productservice.model.Product;
import org.springframework.stereotype.Service;

@Service
public class ProductConvertorImpl implements ProductConverterIF{

    private ProductConvertorImpl(){}

    private final static ProductConvertorImpl instance = new ProductConvertorImpl();

    public static ProductConvertorImpl getInstance(){
        return instance;
    }



    @Override
    public Product convertDTOToEntity(ProductRequest from) {

        Product toBean = Product.builder()
                .name(from.getName())
                .description(from.getDescription())
                .price(from.getPrice())
                .build();
        return toBean;
    }

    @Override
    public ProductResponse convertEntityToDTO(Product from) {

        ProductResponse toBean  = ProductResponse.builder()
                .id(from.getId())
                .name(from.getName())
                .description(from.getDescription())
                .price(from.getPrice())
                .build();
        return toBean;
    }
}
