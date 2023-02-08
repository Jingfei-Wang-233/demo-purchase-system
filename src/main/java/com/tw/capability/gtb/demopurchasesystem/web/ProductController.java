package com.tw.capability.gtb.demopurchasesystem.web;

import com.tw.capability.gtb.demopurchasesystem.domain.Product;
import com.tw.capability.gtb.demopurchasesystem.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {


    private final ProductService productService;

    @GetMapping
    public List<Product> fetchProducts() {
        return productService.findTasks();
    }

}
