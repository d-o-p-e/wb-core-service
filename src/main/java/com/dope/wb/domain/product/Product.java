package com.dope.wb.domain.product;

import com.dope.wb.specification.ProductCategory;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

@Getter
@Entity
@Table(
        indexes = {
                @Index(name="idx_product_serial",columnList = "serial")
        }
)
public class Product {

    @Id
    @GeneratedValue
    @Column(name = "PRODUCT_ID")
    private Long id;

    private String serial;

    private String content;

    private ProductCategory productCategory;

    private Long view;

    public Product() {

    }

    @Builder
    public Product(String serial, String content, ProductCategory productCategory) {
        this.serial = serial;
        this.content = content;
        this.productCategory = productCategory;
        this.view = 0L;
    }
}
