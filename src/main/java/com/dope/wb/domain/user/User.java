package com.dope.wb.domain.user;

import com.dope.wb.dto.JoinRequestDto;
import com.dope.wb.specification.Role;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class User {

    @Id
    @GeneratedValue
    @Column(name="USER_ID")
    private Long id;

    private String email;

    private String password;

    private String name;

    private Role role;

    protected User() {

    }

    private User(String email, String password, String name, Role role) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.role = role;
    }

    public static User createNewUser(JoinRequestDto joinRequestDto) {
        return new User(
            joinRequestDto.getEmail(),
            joinRequestDto.getPassword(),
            joinRequestDto.getUsername(),
            Role.MASTER
        );
    }
}
