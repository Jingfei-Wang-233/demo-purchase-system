package com.tw.capability.gtb.demopurchasesystem;

import com.tw.capability.gtb.demopurchasesystem.domain.Product;
import com.tw.capability.gtb.demopurchasesystem.repository.ProductRepository;
import com.tw.capability.gtb.demopurchasesystem.web.ProductController;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureJsonTesters
class SpringBootProductControllerTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private JacksonTester<List<Product>> productsJson;
    @Autowired
    private ProductController productController;
    @Autowired
    private ProductRepository productRepository;

    @AfterEach
    void tearDown() {
        productController.deleteAll();
    }

    @Test
    void should_return_empty_products() {
        // Given
        // When
        final var responseEntity = restTemplate.getForEntity("/products", List.class);
        // Then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getHeaders().getContentType()).isEqualTo(MediaType.APPLICATION_JSON);
        assertThat(responseEntity.getBody()).isEmpty();
    }

    @Test
    void should_return_all_products() throws IOException {
        // Given
        final var products = List.of(
                new Product("product 01", BigDecimal.valueOf(100.00), "basic management software", "Windows", 1),
                new Product("product 02", BigDecimal.valueOf(200.00), "advanced management software", "Windows", 2),
                new Product("product 03", BigDecimal.valueOf(300.00), "ultimate management software", "Windows", 3));
        productRepository.saveAll(products);
        // When
        final var responseEntity = restTemplate.getForEntity("/products", String.class);
        // Then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getHeaders().getContentType()).isEqualTo(MediaType.APPLICATION_JSON);
        final var fetchedProducts = responseEntity.getBody();
        assertThat(productsJson.parseObject(fetchedProducts)).hasSize(3);
    }
}
