package com.dope.wb.domain.product;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

@Getter
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

    private String path;

    public ProductImage() {
    }

    @Builder
    public ProductImage(Product product) {
        this.product = product;
        this.view = 0L;
    }

    public void setNewFilePath(String path) {
        this.path = path;
    }
}
