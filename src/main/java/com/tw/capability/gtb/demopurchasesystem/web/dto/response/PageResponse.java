package com.tw.capability.gtb.demopurchasesystem.web.dto.response;

import com.tw.capability.gtb.demopurchasesystem.domain.Product;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
@EqualsAndHashCode
public class PageResponse {

    private int number;
    private int size;
    private int totalPages;
    private long totalElements;

    private List<Product> products;

}

