package com.allways.domain.user.service;

import com.allways.common.exception.UserNotFoundException;
import com.allways.domain.user.domain.User;
import com.allways.domain.user.domain.UserDto;
import com.allways.domain.user.domain.UserReadRequest;
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
        User user = userRepository.findUserByUserSeq(req.getUserSeq());

        if (user == null) {
            // 유저가 존재하지 않는 경우
            throw new UserNotFoundException("유저가 존재하지 않습니다.");
        }

        return UserDto.toDto(user);
    }
}
