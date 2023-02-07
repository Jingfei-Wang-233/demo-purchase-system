package com.tw.capability.gtb.demopurchasesystem;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private List<Product> products = Collections.emptyList();

    @GetMapping
    public List<Product> fetchProducts() {
        return products;
    }

    public void save(List<Product> products) {
        this.products = products;
    }

    public void deleteAll() {
        products = Collections.emptyList();
    }
}
