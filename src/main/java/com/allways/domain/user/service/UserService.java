package com.allways.domain.user.service;

import com.allways.domain.user.domain.User;
import com.allways.domain.user.domain.UserDto;
import com.allways.domain.user.domain.UserReadRequest;
import com.allways.domain.user.exception.MemberNotFoundException;
import com.allways.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    public UserDto readUser(UserReadRequest req){
        User user = userRepository.findUserByUserSeq(req.getUserSeq()).orElseThrow(MemberNotFoundException::new);

        return UserDto.toDto(user);
    }
}
