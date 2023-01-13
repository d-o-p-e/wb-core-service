package com.dope.wb.domain.board.library;

import com.dope.wb.domain.board.Board;
import com.dope.wb.domain.user.User;
import com.dope.wb.specification.LibraryCategory;
import lombok.Builder;

import javax.persistence.*;

@Entity
public class Library extends Board {

    @Id
    @GeneratedValue
    @Column(name = "LIBRARY_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="USER_ID")
    private User writer;

    private String title;

    private LibraryCategory category;

    public Library() {

    }

    @Builder
    public Library(Long id, User writer, String title, String content, LibraryCategory category) {
        this.id = id;
        this.writer = writer;
        this.title = title;
        this.content = content;
        this.category = category;
        this.view = 0L;
    }

}
