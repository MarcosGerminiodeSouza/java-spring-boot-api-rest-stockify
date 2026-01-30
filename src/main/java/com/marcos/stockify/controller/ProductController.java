package com.marcos.stockify.controller;

import com.marcos.stockify.dto.ProductRequestDTO;
import com.marcos.stockify.dto.ProductResponseDTO;
import com.marcos.stockify.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductResponseDTO create(@RequestBody @Valid ProductRequestDTO dto) {
        return service.create(dto);
    }

    @GetMapping
    public List<ProductResponseDTO> listActive() {
        return service.findAllActive();
    }
}
