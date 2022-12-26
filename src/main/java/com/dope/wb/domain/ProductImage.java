package com.dope.wb.domain;

import jakarta.persistence.*;

@Entity
public class ProductImage {

    @Id
    @GeneratedValue
    @Column(name = "PRODUCT_IMAGE_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;

    private Long view;
}
