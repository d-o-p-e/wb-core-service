package com.dope.wb.domain.product;

import jakarta.persistence.*;

@Entity
public class ProductImage {

    @Id
    @GeneratedValue
    @Column(name = "PRODUCT_IMAGE_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PRODUCT_ID")
    private Product product;

    private Long view;
}
