package com.allways.domain.user.service;

import java.util.ArrayList;
import java.util.List;

import com.allways.common.exception.UserNotFoundException;
import com.allways.domain.user.domain.User;
import com.allways.domain.user.domain.UserDto;
import com.allways.domain.user.domain.UserReadRequest;
import com.allways.domain.user.dto.UserByPostRequest;
import com.allways.domain.user.dto.UserByPostResponse;
import com.allways.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public UserDto readUser(UserReadRequest req){
        User user = userRepository.findUserByUserSeq(req.getUserSeq());

        if (user == null) {
            // 유저가 존재하지 않는 경우
            throw new UserNotFoundException("유저가 존재하지 않습니다.");
        }

        return UserDto.toDto(user);
    }



    @Transactional
    public List<UserByPostResponse> queryUser(List<UserByPostRequest> userByPostRequestList){

        List<Long> userSeqList = new ArrayList<>();

        for (UserByPostRequest userByPostRequest : userByPostRequestList) {
            userSeqList.add(userByPostRequest.getUserSeq());
        }

        List<User> userList = userRepository.findUserByUserSeqIn(userSeqList);

        List<UserByPostResponse> userByPostResponseList = new ArrayList<>();

        for (UserByPostRequest userByPostRequest : userByPostRequestList) {
            for (User user : userList) {
                if(userByPostRequest.getUserSeq() == user.getUserSeq()) {
                    userByPostResponseList.add(new UserByPostResponse(userByPostRequest.getPostSeq(),
                        userByPostRequest.getUserSeq(),
                        user.getUserId(), user.getNickname()));
                }
            }
        }


        return userByPostResponseList;
    }
}
