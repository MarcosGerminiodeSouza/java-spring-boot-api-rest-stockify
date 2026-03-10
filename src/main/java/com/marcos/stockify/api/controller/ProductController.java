package com.marcos.stockify.api.controller;

import com.marcos.stockify.domain.product.dto.ProductCreateRequestDTO;
import com.marcos.stockify.domain.product.dto.ProductRequestDTO;
import com.marcos.stockify.domain.product.dto.ProductResponseDTO;
import com.marcos.stockify.domain.product.dto.ProductUpdateRequestDTO;
import com.marcos.stockify.domain.product.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductResponseDTO create(@RequestBody @Valid ProductCreateRequestDTO dto) {
        return service.create(dto);
    }

    @GetMapping
    public Page<ProductResponseDTO> list(Pageable pageable) {
        return service.listActive(pageable);
    }

    @GetMapping("/search")
    public Page<ProductResponseDTO> search(
            @RequestParam String name,
            Pageable pageable
    ) {
        return service.searchByName(name, pageable);
    }

    @PutMapping("/{id}")
    public ProductResponseDTO update(
            @PathVariable Long id,
            @RequestBody @Valid ProductUpdateRequestDTO dto
    ) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
