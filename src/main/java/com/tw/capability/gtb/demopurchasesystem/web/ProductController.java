package com.tw.capability.gtb.demopurchasesystem.web;

import com.tw.capability.gtb.demopurchasesystem.service.ProductService;
import com.tw.capability.gtb.demopurchasesystem.web.dto.response.PageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {


    private final ProductService productService;

    @GetMapping
    public ResponseEntity<PageResponse> fetchAllInPaging(@RequestParam(required = false, defaultValue = "1") int pageNumber,
                                                         @RequestParam(required = false, defaultValue = "10") int pageSize) {
        PageResponse pageResponse = productService.fetchAllInPaging(pageNumber - 1, pageSize);
        pageResponse.setNumber(pageResponse.getNumber() + 1);
        return ResponseEntity.ok().body(pageResponse);
    }
}
