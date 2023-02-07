package com.tw.capability.gtb.demopurchasesystem;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SpringBootTestHelloControllerTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void should_return_hello_world() {
        // Given
        // When
        final var responseEntity = restTemplate.getForEntity("/hello", String.class);
        // Then
        assertThat(responseEntity.getBody()).isEqualTo("hello world");
    }
}
