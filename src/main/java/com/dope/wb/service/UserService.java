package com.dope.wb.service;

import com.dope.wb.domain.user.User;
import com.dope.wb.dto.JoinRequestDto;
import com.dope.wb.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public void joinUser(JoinRequestDto joinRequestDto) {
        joinRequestDto.setPassword(bCryptPasswordEncoder.encode(joinRequestDto.getPassword()));
        User user = User.createNewUser(joinRequestDto);
        userRepository.save(user);
    }
}
