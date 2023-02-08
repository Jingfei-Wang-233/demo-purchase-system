package com.tw.capability.gtb.demopurchasesystem.service;

import com.tw.capability.gtb.demopurchasesystem.domain.Product;
import com.tw.capability.gtb.demopurchasesystem.repository.ProductRepository;
import com.tw.capability.gtb.demopurchasesystem.web.dto.response.PageResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;

    public PageResponse fetchAllInPaging(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Product> onePage = productRepository.findAll(pageable);
        List<Product> products = onePage.getContent();
        return PageResponse.builder().number(onePage.getNumber()).size(onePage.getSize())
                .totalPages(onePage.getTotalPages()).totalElements(onePage.getTotalElements())
                .products(products).build();
    }

}
