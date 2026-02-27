package com.marcos.stockify.dto;

import java.math.BigDecimal;

public record ProductResponseDTO(
        Long id,
        String name,
        BigDecimal price,
        Integer quantity,
        Boolean active
) {}