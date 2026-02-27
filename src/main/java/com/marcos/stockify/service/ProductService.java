package com.marcos.stockify.service;

import com.marcos.stockify.domain.mapper.ProductMapper;
import com.marcos.stockify.dto.ProductRequestDTO;
import com.marcos.stockify.dto.ProductResponseDTO;
import com.marcos.stockify.entity.Product;
import com.marcos.stockify.exception.ResourceNotFoundException;
import com.marcos.stockify.domain.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public ProductResponseDTO create(ProductRequestDTO dto) {
        Product product = new Product(dto.name(), dto.price(), dto.quantity());

        return ProductMapper.toResponse(repository.save(product));
    }

    public ProductResponseDTO update(Long id, ProductRequestDTO dto) {
        Product product = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));

        product.update(dto.name(), dto.price(), dto.quantity());
        return ProductMapper.toResponse(repository.save(product));
    }

    public void deactivate(Long id) {
        Product product = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));

        product.deactivate();
        repository.save(product);
    }

    public Page<ProductResponseDTO> listActive(Pageable pageable) {
        return repository.findByActiveTrue(pageable).map(ProductMapper::toResponse);
    }

    public Page<ProductResponseDTO> searchByName(String name, Pageable pageable) {
        return repository.findByActiveTrueAndNameContainingIgnoreCase(name, pageable)
                .map(ProductMapper::toResponse);
    }
}
