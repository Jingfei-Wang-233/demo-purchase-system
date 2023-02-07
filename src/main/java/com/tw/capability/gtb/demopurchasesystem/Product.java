package com.tw.capability.gtb.demopurchasesystem;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Product {
    private String title;
    private BigDecimal price;
    private String description;
    private String platform;
    private int tier;

}
