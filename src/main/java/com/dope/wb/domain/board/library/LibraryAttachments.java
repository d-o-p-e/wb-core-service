package com.dope.wb.domain.board.library;

import javax.persistence.*;

@Entity
public class LibraryAttachments {

    @Id
    @GeneratedValue
    @Column(name = "LIBRARY_ATTACHMENTS_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "LIBRARY_ID")
    private Library library;

    private String location;

    private Long view;
}
