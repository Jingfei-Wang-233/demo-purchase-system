package com.tw.capability.gtb.demopurchasesystem;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "products")
public class Product {

    public Product(String title, BigDecimal price, String description, String platform, int tier) {
        this.title = title;
        this.price = price;
        this.description = description;
        this.platform = platform;
        this.tier = tier;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private BigDecimal price;
    private String description;
    private String platform;
    private int tier;

}
