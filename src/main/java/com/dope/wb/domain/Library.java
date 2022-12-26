package com.dope.wb.domain;

import com.dope.wb.specification.ProductCategory;
import jakarta.persistence.*;

@Entity
public class Library {

    @Id
    @GeneratedValue
    @Column(name = "LIBRARY_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="MEMBER_ID")
    private Member writer;

    private String title;

    private String content;

    private ProductCategory productCategory;

    private Long view;
}
