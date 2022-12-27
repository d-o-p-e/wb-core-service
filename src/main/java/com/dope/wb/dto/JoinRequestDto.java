package com.dope.wb.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class JoinRequestDto {

    private String username;

    private String email;

    private String password;
}
