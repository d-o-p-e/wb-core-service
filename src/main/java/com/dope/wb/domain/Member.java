package com.dope.wb.domain;

import com.dope.wb.specification.Role;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Member {

    @Id
    @GeneratedValue
    @Column(name="MEMBER_ID")
    private Long id;

    private String email;

    private String password;

    private String name;

    private Role role;
}
