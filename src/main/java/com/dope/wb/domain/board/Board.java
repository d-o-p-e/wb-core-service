package com.dope.wb.domain.board;

import lombok.Getter;

import javax.persistence.*;

@Getter
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Board {

    @Id
    private Long id;

    protected String content;

    protected Long view;

    public Board() {

    }

    public void increaseView() {
        this.view++;
    }
}
