package com.dope.wb.service;

import com.dope.wb.domain.user.User;
import com.dope.wb.dto.JoinRequestDto;
import com.dope.wb.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public void joinUser(JoinRequestDto joinRequestDto) {
        User user = User.createNewUser(joinRequestDto);
        userRepository.save(user);
    }
}
