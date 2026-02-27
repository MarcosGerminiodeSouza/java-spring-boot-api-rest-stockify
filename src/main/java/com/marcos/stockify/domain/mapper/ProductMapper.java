package com.marcos.stockify.domain.mapper;

import com.marcos.stockify.dto.ProductResponseDTO;
import com.marcos.stockify.entity.Product;

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
