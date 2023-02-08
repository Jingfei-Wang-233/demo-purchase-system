package com.tw.capability.gtb.demopurchasesystem.repository;

import com.tw.capability.gtb.demopurchasesystem.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByUsername(String username);
}
