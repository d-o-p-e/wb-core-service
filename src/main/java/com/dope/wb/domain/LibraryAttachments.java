package com.dope.wb.domain;

import jakarta.persistence.*;

@Entity
public class LibraryAttachments {

    @Id
    @GeneratedValue
    @Column(name = "LIBRARY_ATTACHMENTS_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Library library;

    private String location;

    private Long view;
}
