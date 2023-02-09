package com.tw.capability.gtb.demopurchasesystem.web;

import com.tw.capability.gtb.demopurchasesystem.domain.Product;
import com.tw.capability.gtb.demopurchasesystem.service.ProductService;
import com.tw.capability.gtb.demopurchasesystem.web.dto.response.PageResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
@AutoConfigureMockMvc
class ProductControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ProductService productService;

    @Test
    void should_return_products_in_one_page() throws Exception {
        // Given
        final var firstProduct = new Product(1L, "p1", BigDecimal.ONE, "abc", "win", 0);
        final var secondProduct = new Product(2L, "p1", BigDecimal.ONE, "def", "win", 1);
        PageResponse pageResponse = PageResponse.builder().number(0).size(2).products(List.of(firstProduct, secondProduct)).build();
        // When
        when(productService.fetchAllInPaging(0,2)).thenReturn(pageResponse);
        // Then
        mockMvc.perform(MockMvcRequestBuilders.get("/products/?pageNumber=1&pageSize=2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.number").value(1))
                        .andExpect(jsonPath("$.size").value(2))
                .andExpect(jsonPath("$.products").isArray())
                .andExpect(jsonPath("$.products.length()").value(2));
    }
}
