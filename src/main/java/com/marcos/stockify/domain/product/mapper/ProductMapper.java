package com.marcos.stockify.domain.product.mapper;

import com.marcos.stockify.domain.product.dto.ProductResponseDTO;
import com.marcos.stockify.domain.product.entity.Product;

public class ProductMapper {

    private ProductMapper() {}

    public static ProductResponseDTO toResponse(Product product) {
        return new ProductResponseDTO(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getQuantity(),
                product.getActive()
        );
    }
}
