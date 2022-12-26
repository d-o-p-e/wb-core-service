package com.dope.wb.domain.library;

import com.dope.wb.domain.user.User;
import com.dope.wb.specification.ProductCategory;
import jakarta.persistence.*;

@Entity
public class Library {

    @Id
    @GeneratedValue
    @Column(name = "LIBRARY_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="USER_ID")
    private User writer;

    private String title;

    private String content;

    private ProductCategory productCategory;

    private Long view;
}
