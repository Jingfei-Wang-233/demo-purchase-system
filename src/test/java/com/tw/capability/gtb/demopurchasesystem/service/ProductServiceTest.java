package com.tw.capability.gtb.demopurchasesystem.service;

import com.tw.capability.gtb.demopurchasesystem.domain.Product;
import com.tw.capability.gtb.demopurchasesystem.repository.ProductRepository;
import com.tw.capability.gtb.demopurchasesystem.web.dto.response.PageResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {
    private final ProductRepository productRepository = mock(ProductRepository.class);
    private final ProductService productService = new ProductService(productRepository);

    @Test
    void should_return_one_page_products() {
        // Given
        final var firstProduct = new Product(1L, "p1", BigDecimal.ONE, "abc", "win", 0);
        final var secondProduct = new Product(2L, "p1", BigDecimal.ONE, "abcd", "win", 1);
        Pageable pageable = PageRequest.of(1, 2);
        // When
        when(productRepository.findAll(pageable)).thenReturn(new PageImpl<>(List.of(firstProduct, secondProduct),
                PageRequest.of(1, 2), 10));
        // Then
        PageResponse pageResponse = productService.fetchAllInPaging(1, 2);
        assertEquals(1, pageResponse.getNumber());
        assertEquals(2, pageResponse.getSize());
        assertEquals(5, pageResponse.getTotalPages());
        assertEquals(10, pageResponse.getTotalElements());
        assertEquals(2,pageResponse.getProducts().size());
        verify(productRepository).findAll(pageable);
    }
}
