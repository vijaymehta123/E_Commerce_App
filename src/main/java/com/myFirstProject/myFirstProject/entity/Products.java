package com.myFirstProject.myFirstProject.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "products")
public class Products {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    private String product_name;
    private String product_desc;
    private String slug;
    private int product_price;
    private String category;
    private String imgLink;
    private int discount;
}
