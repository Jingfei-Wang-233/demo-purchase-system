package com.tw.capability.gtb.demopurchasesystem.service;

import com.tw.capability.gtb.demopurchasesystem.domain.Role;
import com.tw.capability.gtb.demopurchasesystem.domain.User;
import com.tw.capability.gtb.demopurchasesystem.repository.UserRepository;
import com.tw.capability.gtb.demopurchasesystem.support.exception.UserDuplicateException;
import com.tw.capability.gtb.demopurchasesystem.web.dto.request.UserRegisterRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    private final UserRepository userRepository = mock(UserRepository.class);

    private final UserService userService = new UserService(userRepository);

    @Test
    void should_register_user_successfully() {
        UserRegisterRequest registerRequest = UserRegisterRequest.builder().username("wangbingbing").password("Wangbingbing@123").build();
        // Given
        Role roleUser = Role.builder().roleName("USER").id(2L).build();
        User newUser = User.builder()
                .username("wangbingbing")
                .password("Wangbingbing@123")
                .role(roleUser)
                .level(0)
                .build();
        User savedUser = new User(1L,"wangbingbing","Wangbingbing@123",0,roleUser);
        // When
        when(userRepository.save(newUser)).thenReturn(savedUser);
        userService.register(registerRequest);
        // Then
        verify(userRepository).save(newUser);
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
