package com.tw.capability.gtb.demopurchasesystem.service;

import com.tw.capability.gtb.demopurchasesystem.domain.Role;
import com.tw.capability.gtb.demopurchasesystem.domain.User;
import com.tw.capability.gtb.demopurchasesystem.repository.UserRepository;
import com.tw.capability.gtb.demopurchasesystem.support.exception.UserDuplicateException;
import com.tw.capability.gtb.demopurchasesystem.web.dto.request.UserRegisterRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    private final UserRepository userRepository = mock(UserRepository.class);
    private final PasswordEncoder passwordEncoder = mock(PasswordEncoder.class);

    private final UserService userService = new UserService(userRepository,passwordEncoder);

    @Test
    void should_register_user_successfully() {
        String username = "wangbingbing";
        String password = "Wangbingbing@123";
        String encryptedPassword = "encryptedPassword";
        UserRegisterRequest registerRequest = UserRegisterRequest.builder().username(username).password(password).build();
        User user = new User();
        user.setUsername(username);
        user.setRole(Role.builder().roleName("USER").id(2L).build());
        user.setPassword(encryptedPassword);

        when(userRepository.existsByUsername(username)).thenReturn(Boolean.FALSE);
        when(passwordEncoder.encode(password)).thenReturn(encryptedPassword);
        userService.register(registerRequest);

        verify(userRepository,times(1)).save(user);
    }

    @Test
    void should_throw_exception_when_username_exists() {
        // Given
        UserRegisterRequest registerRequest = UserRegisterRequest.builder().username("wangbingbing").password("Wangbingbing@123").build();
        // When
        when(userRepository.existsByUsername(registerRequest.getUsername())).thenReturn(Boolean.TRUE);
        // Then
        assertThrows(UserDuplicateException.class,() -> userService.register(registerRequest));
        verify(userRepository, times(0)).save(any());
    }
}
