package com.tw.capability.gtb.demopurchasesystem.web;

import com.tw.capability.gtb.demopurchasesystem.service.ProductService;
import com.tw.capability.gtb.demopurchasesystem.web.dto.response.PageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {


    public static final String DEFAULT_PAGE_NUMBER = "1";
    public static final String DEFAULT_PAGE_SIZE = "10";
    private final ProductService productService;

    @GetMapping
    public ResponseEntity<PageResponse> fetchAllInPaging(@RequestParam(required = false, defaultValue = DEFAULT_PAGE_NUMBER) int pageNumber,
                                                         @RequestParam(required = false, defaultValue = DEFAULT_PAGE_SIZE) int pageSize) {
        PageResponse pageResponse = productService.fetchAllInPaging(pageNumber - 1, pageSize);
        pageResponse.setNumber(pageResponse.getNumber() + 1);
        return ResponseEntity.ok().body(pageResponse);
    }
}
