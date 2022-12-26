package com.dope.wb.domain.product;

import com.dope.wb.specification.ProductCategory;
import jakarta.persistence.*;

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
}
