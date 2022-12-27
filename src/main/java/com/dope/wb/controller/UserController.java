package com.dope.wb.controller;

import com.dope.wb.dto.JoinRequestDto;
import com.dope.wb.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "${apiPrefix}/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/join")
    public ResponseEntity<Void> join(JoinRequestDto joinRequestDto) {
        userService.joinUser(joinRequestDto);
        return ResponseEntity.ok().build();
    }
}
