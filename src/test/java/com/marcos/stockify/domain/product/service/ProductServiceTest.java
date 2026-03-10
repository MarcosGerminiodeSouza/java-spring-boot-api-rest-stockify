package com.marcos.stockify.domain.product.service;

import com.marcos.stockify.domain.product.dto.ProductCreateRequestDTO;
import com.marcos.stockify.domain.product.dto.ProductUpdateRequestDTO;
import com.marcos.stockify.domain.product.entity.Product;
import com.marcos.stockify.domain.product.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository repository;

    @InjectMocks
    private ProductService service;

    private Product product;

    @BeforeEach
    void setup() {
        product = new Product(
                "Caderno",
                new BigDecimal("18.90"),
                50
        );
    }

    @Test
    void shouldCreateProduct() {
        var request = new ProductCreateRequestDTO(
                "Caderno",
                new BigDecimal("18.90"),
                50
        );

        when(repository.save(any(Product.class))).thenReturn(product);

        var response = service.create(request);

        assertThat(response).isNotNull();
        assertThat(response.name()).isEqualTo("Caderno");
        assertThat(response.price()).isEqualByComparingTo("18.90");

        verify(repository, times(1)).save(any(Product.class));
    }

    @Test
    void shouldUpdateProduct() {
        var request = new ProductUpdateRequestDTO(
                "Caderno Atualizado",
                new BigDecimal("22.90"),
                40
        );

        when(repository.findById(1L)).thenReturn(Optional.of(product));
        when(repository.save(any(Product.class))).thenReturn(product);

        var response = service.update(1L, request);

        assertThat(response.name()).isEqualTo("Caderno Atualizado");
        assertThat(response.price()).isEqualByComparingTo("22.90");

        verify(repository).findById(1L);
        verify(repository).save(product);
    }

    @Test
    void shouldDeactivateProduct() {
        when(repository.findById(1L)).thenReturn(Optional.of(product));

        service.delete(1L);

        assertThat(product.getActive()).isFalse();
        verify(repository).save(product);
    }

    @Test
    void shouldListActiveProducts() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Product> page = new PageImpl<>(List.of(product));

        when(repository.findByActiveTrue(pageable)).thenReturn(page);

        var result = service.listActive(pageable);

        assertThat(result.getContent()).hasSize(1);
        verify(repository).findByActiveTrue(pageable);
    }

    @Test
    void shouldSearchProductsByName() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Product> page = new PageImpl<>(List.of(product));

        when(repository.findByActiveTrueAndNameContainingIgnoreCase("cad", pageable))
                .thenReturn(page);

        var result = service.searchByName("cad", pageable);

        assertThat(result.getContent()).hasSize(1);
        verify(repository)
                .findByActiveTrueAndNameContainingIgnoreCase("cad", pageable);
    }
}