package com.tw.capability.gtb.demopurchasesystem;

import com.tw.capability.gtb.demopurchasesystem.domain.Product;
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
import org.springframework.test.context.jdbc.Sql;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureJsonTesters
class SpringBootProductControllerTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private JacksonTester<List<Product>> productsJson;
    @Autowired
    private ProductController productController;

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
    @Sql("/sql/insert_3_products.sql")
    void should_return_all_products() throws IOException {
        // Given
        // When
        final var responseEntity = restTemplate.getForEntity("/products", String.class);
        // Then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getHeaders().getContentType()).isEqualTo(MediaType.APPLICATION_JSON);
        final var fetchedProducts = responseEntity.getBody();
        assertThat(productsJson.parseObject(fetchedProducts)).hasSize(3);
        assertEquals(0, BigDecimal.valueOf(100).compareTo(productsJson.parseObject(fetchedProducts).get(0).getPrice()));
    }
}
