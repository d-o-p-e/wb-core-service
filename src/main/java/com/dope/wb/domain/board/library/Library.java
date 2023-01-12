package com.dope.wb.domain.board.library;

import com.dope.wb.domain.board.Board;
import com.dope.wb.domain.user.User;
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

    public Library() {

    }

    @Builder
    public Library(Long id, User writer, String title, String content) {
        this.id = id;
        this.writer = writer;
        this.title = title;
        this.content = content;
        this.view = 0L;
    }

}
