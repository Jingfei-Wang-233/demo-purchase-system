package com.tw.capability.gtb.demopurchasesystem.repository;

import com.tw.capability.gtb.demopurchasesystem.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
