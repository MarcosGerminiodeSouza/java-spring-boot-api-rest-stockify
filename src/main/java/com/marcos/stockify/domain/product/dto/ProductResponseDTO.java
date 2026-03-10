package com.marcos.stockify.domain.product.dto;

import java.math.BigDecimal;

public record ProductResponseDTO(
        Long id,
        String name,
        BigDecimal price,
        Integer quantity,
        Boolean active
) {}