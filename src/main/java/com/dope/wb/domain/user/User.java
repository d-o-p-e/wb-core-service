package com.dope.wb.domain.user;

import com.dope.wb.dto.JoinRequestDto;
import com.dope.wb.specification.Role;
import javax.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(indexes = @Index(name = "email", columnList = "username", unique = true))
public class User {

    @Id
    @GeneratedValue
    @Column(name="USER_ID")
    private Long id;

    private String email;

    private String password;

    private String username;

    private Role role;

    protected User() {

    }

    private User(String email, String password, String username, Role role) {
        this.email = email;
        this.password = password;
        this.username = username;
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
