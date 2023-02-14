package com.tw.capability.gtb.demopurchasesystem.service;

import com.tw.capability.gtb.demopurchasesystem.domain.Role;
import com.tw.capability.gtb.demopurchasesystem.domain.User;
import com.tw.capability.gtb.demopurchasesystem.repository.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static org.springframework.security.core.userdetails.User.withUsername;

@Service
public class CustomerUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    public CustomerUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userDetail = userRepository.findByUsername(username);
        User user = userDetail.orElseThrow(() -> new UsernameNotFoundException("username does not exist"));

        Role role = user.getRole();
        SimpleGrantedAuthority authority = role.roleMapper(role.getRoleName());

        return withUsername(username)
                .password(user.getPassword())
                .authorities(authority)
                .build();
    }
}
