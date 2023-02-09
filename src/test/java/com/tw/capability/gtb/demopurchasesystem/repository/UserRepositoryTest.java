package com.tw.capability.gtb.demopurchasesystem.repository;

import com.tw.capability.gtb.demopurchasesystem.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.jdbc.Sql;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.assertj.core.api.Assertions.assertThat;

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

    @Test
    void should_return_null_when_find_by_id_not_exist() {
        Optional<User> result = userRepository.findById(1L);
        assertFalse(result.isPresent());
    }

    @Test
    @Sql("/sql/insert_a_user.sql")
    void should_return_user_when_find_by_id() {
        Optional<User> user = userRepository.findById(1L);
        assertThat(user).isPresent();
        assertThat(user.get().getId()).isEqualTo(1L);
        assertThat(user.get().getUsername()).isEqualTo("luoyong123");
        assertThat(user.get().getRole().getRoleName()).isEqualTo("USER");
    }

}
