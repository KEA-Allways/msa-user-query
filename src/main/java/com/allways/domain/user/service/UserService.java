package com.allways.domain.user.service;

import com.allways.domain.user.entity.User;
import com.allways.domain.user.entity.UserResponse;
import com.allways.domain.user.exception.UserNotFoundException;
import com.allways.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    public UserResponse readUser(Long userSeq){
        User user = userRepository.findUserByUserSeq(userSeq).orElseThrow(UserNotFoundException::new);

        return UserResponse.toDto(user);
    }

}
