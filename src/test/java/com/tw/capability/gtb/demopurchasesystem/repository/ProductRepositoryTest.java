package com.tw.capability.gtb.demopurchasesystem.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static org.assertj.core.api.Assertions.assertThat;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.jdbc.Sql;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback
class ProductRepositoryTest {
    @Autowired
    private ProductRepository productRepository;
    @Test
    void should_return_empty_product_list() {
        final var products = productRepository.findAll();
        assertThat(products).isEmpty();
    }
    @Test
    @Sql("/sql/insert_3_products.sql")
    void should_return_all_products() {
        final var products = productRepository.findAll();
        assertThat(products).hasSize(3);
    }
}
