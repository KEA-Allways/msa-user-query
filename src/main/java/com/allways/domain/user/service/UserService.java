package com.allways.domain.user.service;

import java.util.ArrayList;
import java.util.List;

import com.allways.domain.user.domain.User;
import com.allways.domain.user.dto.UserDto;
import com.allways.common.feign.user.UserByPostFeignRequest;
import com.allways.common.feign.user.UserByPostFeignResponse;
import com.allways.common.feign.user.UserFeignResponse;
import com.allways.domain.user.exception.UserNotFoundException;
import com.allways.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    public UserDto readUser(Long userSeq){
        User user = userRepository.findUserByUserSeq(userSeq).orElseThrow(UserNotFoundException::new);

        return UserDto.toDto(user);
    }

}
