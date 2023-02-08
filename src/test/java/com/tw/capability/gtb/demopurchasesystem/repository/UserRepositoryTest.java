package com.tw.capability.gtb.demopurchasesystem.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback
class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    void should_return_false_when_username_not_exists() {
        assertFalse(userRepository.existsByUsername("USER"));
    }

    @Test
    @Sql("/sql/insert_a_user.sql")
    void should_return_true_when_username_exists() {
        assertTrue(userRepository.existsByUsername("luoyong123"));
    }
}
