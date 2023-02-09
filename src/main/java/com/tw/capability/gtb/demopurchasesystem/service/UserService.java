package com.tw.capability.gtb.demopurchasesystem.service;

import com.tw.capability.gtb.demopurchasesystem.domain.Role;
import com.tw.capability.gtb.demopurchasesystem.domain.User;
import com.tw.capability.gtb.demopurchasesystem.repository.UserRepository;
import com.tw.capability.gtb.demopurchasesystem.support.exception.UserDuplicateException;
import com.tw.capability.gtb.demopurchasesystem.web.dto.request.UserRegisterRequest;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {
    @Autowired
    private UserRepository userRepository;
    public void register(UserRegisterRequest request) {
        validateForRegister(request);
        Role roleUser = Role.builder().id(2L).roleName("USER").build();
        User user = User.builder()
                .username(request.getUsername())
                .password(request.getPassword())
                .role(roleUser)
                .level(0).build();
        userRepository.save(user);
    }

    private void validateForRegister(UserRegisterRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new UserDuplicateException(request.getUsername());
        }
    }
}
