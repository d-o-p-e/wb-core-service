package com.dope.wb.domain;

import com.dope.wb.specification.ProductAttachmentType;
import jakarta.persistence.*;

@Entity
public class ProductAttachments {

    @Id
    @GeneratedValue
    @Column(name = "PRODUCT_ATTACHMENTS_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PRODUCT_ID")
    private Product product;

    private ProductAttachmentType type;

    private String location;

    private Long view;
}
