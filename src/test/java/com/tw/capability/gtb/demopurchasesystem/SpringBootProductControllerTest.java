package com.tw.capability.gtb.demopurchasesystem;

import com.tw.capability.gtb.demopurchasesystem.repository.ProductRepository;
import com.tw.capability.gtb.demopurchasesystem.web.dto.response.PageResponse;
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

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureJsonTesters
class SpringBootProductControllerTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private JacksonTester<PageResponse> productsJson;
    @Autowired
    private ProductRepository productRepository;

    @AfterEach
    void tearDown() {
        productRepository.deleteAll();
    }

    @Test
    void should_return_empty_products() throws IOException {
        // Given
        // When
        final var responseEntity = restTemplate.getForEntity("/products", String.class);
        PageResponse response = productsJson.parseObject(responseEntity.getBody());
        // Then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getHeaders().getContentType()).isEqualTo(MediaType.APPLICATION_JSON);
        assertThat(response.getTotalPages()).isZero();
        assertThat(response.getTotalElements()).isZero();
        assertThat(response.getProducts()).isEmpty();

    }

    @Test
    @Sql("/sql/insert_3_products.sql")
    void should_return_all_products_in_paging() throws IOException {
        // Given
        // When
        final var responseEntity = restTemplate.getForEntity("/products", String.class);
        PageResponse response = productsJson.parseObject(responseEntity.getBody());
        // Then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getHeaders().getContentType()).isEqualTo(MediaType.APPLICATION_JSON);
        assertThat(response.getTotalPages()).isEqualTo(1);
        assertThat(response.getTotalElements()).isEqualTo(3);

        assertThat(response.getProducts().get(2).getId()).isEqualTo(3L);
        assertThat(response.getProducts().get(2).getTier()).isEqualTo(3);
        BigDecimal firstPrice = response.getProducts().get(2).getPrice();
        assertEquals(0, BigDecimal.valueOf(300.00).compareTo(firstPrice));
    }
}
