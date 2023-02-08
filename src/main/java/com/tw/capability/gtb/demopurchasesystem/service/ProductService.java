package com.tw.capability.gtb.demopurchasesystem.service;

import com.tw.capability.gtb.demopurchasesystem.domain.Product;
import com.tw.capability.gtb.demopurchasesystem.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;

    public List<Product> findTasks() {
        return productRepository.findAll();
    }

}
