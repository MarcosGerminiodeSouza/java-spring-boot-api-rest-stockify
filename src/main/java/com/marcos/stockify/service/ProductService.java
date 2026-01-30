package com.marcos.stockify.service;

import com.marcos.stockify.dto.ProductRequestDTO;
import com.marcos.stockify.dto.ProductResponseDTO;
import com.marcos.stockify.entity.Product;
import com.marcos.stockify.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public ProductResponseDTO create(ProductRequestDTO dto) {
        Product productDto = new Product(dto.getName(), dto.getPrice(), dto.getQuantity());

        Product product = repository.save(productDto);

        return new ProductResponseDTO(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getQuantity(),
                product.getActive()
        );
    }

    public List<ProductResponseDTO> findAllActive() {
        return repository.findByActiveTrue()
                .stream()
                .map(p -> new ProductResponseDTO(
                        p.getId(),
                        p.getName(),
                        p.getPrice(),
                        p.getQuantity(),
                        p.getActive()
                ))
                .toList();
    }
}
