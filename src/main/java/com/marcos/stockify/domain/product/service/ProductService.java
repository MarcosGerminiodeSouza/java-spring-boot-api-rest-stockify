package com.marcos.stockify.domain.product.service;

import com.marcos.stockify.domain.product.dto.ProductCreateRequestDTO;
import com.marcos.stockify.domain.product.dto.ProductUpdateRequestDTO;
import com.marcos.stockify.domain.product.mapper.ProductMapper;
import com.marcos.stockify.domain.product.dto.ProductResponseDTO;
import com.marcos.stockify.domain.product.entity.Product;
import com.marcos.stockify.domain.product.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public ProductResponseDTO create(ProductCreateRequestDTO dto) {
        Product product = new Product(
                dto.name(),
                dto.price(),
                dto.quantity()
        );

        return ProductMapper.toResponse(repository.save(product));
    }

    public ProductResponseDTO update(Long id, ProductUpdateRequestDTO dto) {
        Product product = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Produto não encontrado"));

        product.update(dto.name(), dto.price(), dto.quantity());

        return ProductMapper.toResponse(repository.save(product));
    }

    public void delete(Long id) {
        Product product = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Produto não encontrado"));

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
